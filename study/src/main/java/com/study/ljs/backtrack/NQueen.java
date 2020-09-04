package com.study.ljs.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lijiasheng
 * @version 1.0
 * @date 2020/9/3 17:47
 */
public class NQueen {
    static List<List<Integer>> res = new LinkedList<>();

    public static void main(String[] args) {
        nQueen(8);
    }

    private static void nQueen(int N) {
        if (N < 2) {
            System.out.println("N必须大于等于2");
            return;
        }
//        0:null  1:queen
        int[][] bord = new int[N][N];
        //0 1 2 3 4 5 6 7
        int row = 0;
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(track, bord, 0);
        System.out.println("res:    size:" + res.size());
        for (int i = 0; i < res.size(); i++) {
            res.get(i).forEach(val -> {
                System.out.print(val + "\t");
            });
            System.out.println();
        }

    }

    private static void backtrack(LinkedList<Integer> track, int[][] bord, int row) {
        if (track.size() == bord.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int j = 0; j < bord[row].length; j++) {
            if (isV(bord, row, j)) {
                track.add(j);
                bord[row][j] = 1;
                backtrack(track, bord, row + 1);
                bord[row][j] = 0;
                track.removeLast();
            } else {
                continue;
            }
        }
    }

    private static boolean isV(int[][] bord, int i, int j) {
//        检查行
        for (int k = 0; k < bord.length; k++) {
            if (k == j) {
                continue;
            }
            if (bord[i][k] == 1) {
                return false;
            }
        }

//        检查列
        for (int k = 0; k < bord.length; k++) {
            if (k == i) {
                continue;
            }
            if (bord[k][j] == 1) {
                return false;
            }
        }

//        检查左上斜因为皇后是从上往下一行一行放置的，所以下方一定没有皇后
        for (int k1 = i - 1, k2 = j - 1; k1 >= 0 && k2 >= 0; k1--, k2--) {
            if (bord[k1][k2] == 1) {
                return false;
            }
        }

//        检查右上斜:因为皇后是从上往下一行一行放置的，所以下方一定没有皇后
        for (int k1 = i - 1, k2 = j + 1; k1 >= 0 && k2 < bord.length; k1--, k2++) {
            if (bord[k1][k2] == 1) {
                return false;
            }
        }
        return true;
    }


}
