package medium;

import java.util.HashMap;
import java.util.Map;

public class CoinChange322 {

    /**
     * Approach: Memoization
     * - Pick a coin and try to find remaining target.
     * - If memorized result has the value then use it.
     * - Keep track of the min count
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        Map<Integer, Integer> memory = new HashMap<>();
        if (amount <= 0) {
            return 0;
        }
        for (int coin : coins) {
            memory.put(coin, 1);
        }
        coinChangeRecursive(coins, amount, memory);
        return  memory.getOrDefault(amount, -1);
    }

    private int coinChangeRecursive(int[] coins, int targetAmount, Map<Integer, Integer> memory) {
        if (targetAmount < 0) {
            return -1;
        }
        if (targetAmount == 0) {
            return 0;
        }
        if (memory.containsKey(targetAmount)) {
            return memory.get(targetAmount);
        }
        // We didn't encounter targetAmount so far so have to compute it.
        int localMin = -1;
        for (int coin : coins) {
            int coinsCount = coinChangeRecursive(coins, targetAmount - coin, memory);
            if (coinsCount >= 0) {
                localMin = localMin == -1 ? coinsCount + 1 : Math.min(localMin, coinsCount + 1);
                memory.put(targetAmount, localMin);
            }
        }

        return localMin;
    }

    public static void main(String[] args) {
        CoinChange322 coinChange322 = new CoinChange322();
        int[] coins = {1,2,5}; int amount = 11;
        System.out.println("Coins count : " + coinChange322.coinChange(coins, amount));
        int[] coins1 = {2}; int amount1 = 3;
        System.out.println("Coins count : " + coinChange322.coinChange(coins1, amount1));
        int[] coins2 = {1}; int amount2 = 0;
        System.out.println("Coins count : " + coinChange322.coinChange(coins2, amount2));

    }
}


