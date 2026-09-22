class Solution {
    /**
     * Given integer n, return the num of prime numbers < n.
     * 
     * @param n exclusive maximum boundary for prime testing
     * @return number of prime numbers < n
     */
    public int countPrimes(int n) {
        if (n <= 2)
            return 0;

        final boolean[] isComposite = new boolean[n];

        // 0, 1 are not primes
        for (int d = 2; d*d < n; d++) {
            if (!isComposite[d]) {
                for (int i = d * d; i < n; i += d) {
                    isComposite[i] = true;
                }
            }
        }

        // count stage - O(n) better than adding branch
        // to O(n log(log n)) section
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!isComposite[i])
                count++;
        }

        return count;
    }
}