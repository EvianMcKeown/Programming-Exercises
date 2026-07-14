class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        """
        Can't discard entire substring when duplicate is found.
        Need a sliding window, ie Two Pointer approach.

        Keep dictionary of seen chars with last pos, if duplicate found,
        jump to dict[suplicate_char] -> {pos} + 1 and keep track of length
        using left + right pointers.
        """

        left: int = 0
        seen: dict = {}
        max_len: int = 0
        
        for right, char in enumerate(s):
            if char in seen and seen[char] >= left:
                # exclude duplicate in O(1)
                left = seen[char] + 1
                
            # update last seen dict
            seen[char] =  right
            # update max
            max_len = max(max_len, right - left + 1)
         
        return max_len