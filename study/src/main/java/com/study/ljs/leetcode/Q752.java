package com.study.ljs.leetcode;

import java.util.*;

/**
 * @author lijiasheng
 * @version 1.0
 * @date 2020/9/4 14:39
 */
public class Q752 {

    public static void main(String[] args) {
        Q752 q = new Q752();
        System.out.println(q.openLock(new String[]{"1111", "1234", "7654", "9876"}, "1100"));
    }

    public int openLock(String[] deadends, String target){
        Set<String> deads = new HashSet<>();
        for (int i = 0; i <deadends.length ; i++) {
            deads.add(deadends[i]);
        }
        Set<String> visited = new HashSet<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");
        int step = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i <size ; i++) {
                String cur = queue.poll();

                if (deads.contains(cur)){
                    continue;
                }
                if (cur.equals(target)){
                    return step;
                }
                for (int j = 0; j < 4; j++) {
                    String up = upOne(cur,j);
                    if (!visited.contains(up)){
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down = downOne(cur,j);
                    if (!visited.contains(down)){
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String upOne(String str,int i){
        char[] chars = str.toCharArray();
        if (chars[i]=='9'){
            chars[i]='0';
        }else {
            chars[i]++;
        }
        return new String(chars);
    }
    private String downOne(String str,int i){
        if (i>=str.length()) System.out.println("i:"+i+"  str:"+str);
        char[] chars = str.toCharArray();
        if (chars[i]=='0'){
            chars[i]='9';
        }else {
            chars[i]--;
        }
        return new String(chars);
    }
}