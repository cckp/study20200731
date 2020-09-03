package com.study.ljs.algorithm.dp;

/**
 * @author lijiasheng
 * @version 1.0
 * @date 2020/9/3 14:05
 */
public class Coins {
    public static void main(String[] args) {
        int[] coins = {1, 3, 5};
        int target = 50;
        System.out.println(coinChange(coins, target));
    }

    public static int coinChange(int[] coins, int target) {
        int[] dp = new int[target + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] > 0) {
                    min = Math.min(min,dp[i-coins[j]]+1);
                }else if (i-coins[j]==0){
                    min = 1;
                    break;
                }else {
                    continue;
                }
            }
            dp[i] = min;
        }
        return dp[target];
    }


}
