import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // track last seen pos of chars: char -> last index
        HashMap<Character, Integer> seen = new HashMap<>();
        // dual pointers for sliding window
        int left = 0, maxLength = 0;
        
        for (int r=0; r < s.length(); r++){
            int lastPos = seen.getOrDefault(s.charAt(r),-1);
            if (lastPos >= left){
                // duplicate in window, move left ptr past duplicate
                left = lastPos + 1;
            }
            seen.put(s.charAt(r), r);
            int len = r - left + 1;
            maxLength = Math.max(len, maxLength);
        }
        return maxLength;
    }
}