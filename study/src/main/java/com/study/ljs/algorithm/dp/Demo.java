package com.study.ljs.algorithm.dp;

import java.util.Random;

/**
 * @author lijiasheng
 * @version 1.0
 * @date 2020/9/3 15:10
 */
public class Demo {
    public static void main(String[] args) {

        int[][] w = new int[10][10];
        int[][] dp = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                w[i][j] = (int) (Math.random() * 10);
                System.out.print(w[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------");
        dp[0][0] = w[0][0];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int min = Integer.MAX_VALUE;
//                从上面下来
                if (i - 1 >= 0) {
                    min = Math.min(min, dp[i - 1][j] + w[i][j]);
                }
//                从左边过来
                if (j - 1 >= 0) {
                    min = Math.min(min, dp[i][j - 1] + w[i][j]);
                }
                dp[i][j] = min;
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }


    }
}
