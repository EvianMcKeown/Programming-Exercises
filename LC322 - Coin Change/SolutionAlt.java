public class SolutionAlt {
    public static void main(String[] args) {
        // TODO
    }

    /***
     * Returns the minimum number of coins needed to make up the specified amount.
     * 
     * @param coins
     *               Valid denominations of coins
     *               - Assume that coins are in non-decreasing order
     * @param amount
     *               Target amount
     * @return
     *         -1 if not possible, otherwise minimum amount of coins to reach target
     */
    public int coinChange(int[] coins, int amount) {
        // ASSUME INSTEAD YOU CAN ONLY USE EACH COIN 0/1 TIMES
        // ---------------------------------------------------
        /*
         * Keep running sum, and see if amount - coins[i] >= 0
         * If < 0 for all coins in round, then we have to back...
         */

        int sentinel = amount + 1;
        int[] subTotal = new int[amount + 1];

        // set sentinel values
        for (int i = 0; i <= amount; i++) {
            subTotal[i] = sentinel;
        }
        // special case
        subTotal[0] = 0;

        // outer loop: 0 <= i <= amount
        // inner loop: j in coins
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    subTotal[i] = Math.min(subTotal[i], subTotal[i - coin] + 1);
                }
            }
        }

        return (subTotal[amount] == sentinel) ? -1 : subTotal[amount];
    }
}