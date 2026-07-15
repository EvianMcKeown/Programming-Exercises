// high overhead: Array of Structures (DOD anti-pattern)
// import java.util.HashMap;

public class Solution {

    public static void main(String[] args) {
        char test = 'z';
        byte testVal = (byte) (test - 97);
        System.out.println(testVal);

        Solution sol = new Solution();
        System.out.println(sol.isAnagram("ba", "ab"));
    }

    public boolean isAnagram(String s, String t) {
        /*
         * Given two strings s and t, return true if t is an anagram of s, and false
         * otherwise.
         * 
         * Input: s = "anagram", t = "nagaram"
         * Output: true
         */

        /*
         * Use HashMap: [char] -> count
         * i) check len(s) == len(t), else return false
         * ii) construct hashmap for s
         * iii) begin constructing hashmap for t
         * -> if for current char c in t: hashmapT[c] > hashmapS[c]
         * -> return false
         * -> elif c not in hashmapS:
         * -> return false
         * iv) go through hashmapS again and check for differences (hashmapS[c] >
         * hashmapT[c] || c not in hashmapT => return false)
         * v) finally, return true.
         */

        /*
         * Better solution:
         * Instead of two hashmaps,
         * Make hMapS, then run through t and subtract values for each char.
         * -> if val < 0, return false
         * finally, run through hMapS and if non-zero value found, return false.
         * else, return true.
         */

        if (s.length() != t.length()) {
            return false;
        }

        var sMap = new DirectAddressTable();
        // create sMap
        for (char c : s.toCharArray()) {
            sMap.increment(c);
        }

        for (char c : t.toCharArray()) {
            sMap.decrement(c);
            if (sMap.get(c) < 0)
                return false;
        }

        for (int i : sMap.vals) {
            if (i != 0)
                return false;
        }

        return true;
    }

    class DirectAddressTable {
        private final int[] vals = new int[26]; // vals need to store arbitrary frequencies up to 5x10^4

        public void increment(char keyChar) {
            this.vals[index(keyChar)]++;
        }

        public void decrement(char keyChar) {
            this.vals[index(keyChar)]--;
        }

        public int get(char keyChar) {
            return this.vals[index(keyChar)];
        }

        public void put(char key, int val) {
            this.vals[index(key)] = val;
        }

        private int index(char c) {
            return (c - 'a');
        }
    }
}