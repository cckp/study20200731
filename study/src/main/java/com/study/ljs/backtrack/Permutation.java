package com.study.ljs.backtrack;

import java.util.*;

/**
 * @author lijiasheng
 * @version 1.0
 * @date 2020/9/3 17:13
 */
public class Permutation {

    /**
     * 全排列问题
     */

    static List<List<Integer>> res = new LinkedList<>();

    public static void main(String[] args) {
        int[] source = {1, 2, 3};
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(track, source);
        System.out.println(res.size());
        for (int i = 0; i < res.size(); i++) {
            List<Integer> tmp = res.get(i);
            tmp.stream().forEach(va -> {
                System.out.print(va + "->");
            });
            System.out.println();
        }

    }

    /**
     * 回溯：  路径，选择列表
     */
    private static void backtrack(LinkedList<Integer> track, int[] nums) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            backtrack(track, nums);
            track.removeLast();
        }

    }


}
