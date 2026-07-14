import java.util.HashMap;

public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.isAnagram("a", "ab"));
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

        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> sMap = constructHashMap(s);
        return compareHashMaps(sMap, t);
    }

    private HashMap<Character, Integer> constructHashMap(String s) {
        HashMap<Character, Integer> hMap = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            Integer curFreq = hMap.get(currentChar);
            if (curFreq == null) {
                hMap.put(currentChar, 1);
            } else {
                hMap.put(currentChar, curFreq + 1);
            }
        }
        return hMap;
    }

    private boolean compareHashMaps(HashMap<Character, Integer> sMap, String t) {
        HashMap<Character, Integer> tMap = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            char currentChar = t.charAt(i);
            Integer curFreq = tMap.get(currentChar);
            if (curFreq == null) {
                tMap.put(currentChar, 1);
            } else {
                tMap.put(currentChar, curFreq + 1);
            }

            // Check if > is a strong enough condition to not need to check anything else
            // after constructing the full sMap
            // Think that null + > is strong enough, since if there are more characters in
            // sMap, then some in hMap will need to have a higher count
            if (sMap.get(currentChar) == null) {
                return false;
            } else if (tMap.get(currentChar) > sMap.get(currentChar)) {
                return false;
            }
        }

        // final check for sMap[c] >hMap
        for (Character currentChar : sMap.keySet()) {
            if (tMap.get(currentChar) == null) {
                return false;
            } else if (sMap.get(currentChar) > tMap.get(currentChar)) {
                return false;
            }
        }

        return true;
    }
}