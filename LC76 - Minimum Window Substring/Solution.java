import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        var sol = new Solution();
        System.out.println(sol.minWindow(s, t));
    }

    public String minWindow(String s, String t) {
        // Given two strings s and t of lengths m and n respectively, return the minimum
        // window substring of s such that every character in t (including duplicates)
        // is included in the window. If there is no such substring, return the empty
        // string "".
        // The testcases will be generated such that each answer is unique.
        // 1 <= m, n <= 10^5
        // s, t ∈ uppercase & lowercase letters.

        int m = s.length();
        //int n = t.length();

        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();

        // HashMap will be used to check if valid window
        var need = new HashMap<Character, Integer>();
        var window = new HashMap<Character, Integer>();
        // populate with string t data
        for (char c : charT) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // no of unique chars needed
        int required = need.size();
        int formed = 0; // no of chars currently satisfied

        // Two pointer technique + Sliding Window
        // 1) init left, right at 0
        // 2) move right pointer until window valid
        // 3) shrink left pointer to find minimal window
        // 4) repeat from step 2, until right reaches end, then perform final left
        // minimization

        // step 1 --- init
        int left = 0, right = 0;
        int leftMin = 0, rightMin = m + 1; // value we can test for later

        while (right < m) {
            // step 2 --- expanding window
            while (formed != required && right < m) {
                char cRight = charS[right];
                window.put(cRight, window.getOrDefault(cRight, 0) + 1);
                if (need.containsKey(cRight) && window.get(cRight).intValue() == need.get(cRight).intValue()) {
                    formed++;
                }
                right++;
            }

            // step 3 --- shrinking window
            while (formed == required && left <= right) {
                // check best sol
                if (right - left < rightMin - leftMin) {
                    rightMin = right;
                    leftMin = left;
                }
                char cLeft = charS[left];
                window.put(cLeft, window.get(cLeft) - 1);
                if (need.containsKey(cLeft) && window.get(cLeft) < need.get(cLeft)) {
                    formed--;
                }
                left++;
            }
        }
        return (rightMin == m + 1) ? "" : s.substring(leftMin, rightMin);
    }
}