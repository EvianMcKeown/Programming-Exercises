class Solution:
    @staticmethod
    def included(window: dict[str, int], required: dict[str, int]) -> bool:
        for char, count in required.items():
            if window.get(char, 0) < count:
                return False
        return True

    def minWindow(self, s: str, t: str) -> str:
        """
        Two pointer approach
        Every char in t must be in window.

        Naive:
        ->  expand right pointer until all chars in t are covered
            move left pointer until final char still covering all in t
                -> expand right & remove final left char until new substr is found
            ...
            return smallest substr

        """

        left: int = 0
        right: int = 0
        # default: one bigger than biggest possible match
        best_len: int = len(s) + 1
        best_left: int = left
        best_right: int = right
        # count occurances as val
        matched: dict[str, int] = {}
        
        valid_window: bool = False

        # t -> t_dict
        t_dict: dict[str, int] = {}
        for c in t:
            if c in t_dict:
                t_dict[c] += 1
            else:
                t_dict[c] = 1
                
        while right < len(s):
            
            while not self.included(matched, t_dict):
                if right >= len(s):
                    valid_window = False
                    break
                
                # not all keys included
                char: str = s[right]
                if char in t_dict:
                    # update record and count
                    matched[char] = matched.get(char, 0) + 1
                right += 1

            # bring in left bound
            if valid_window:
                while self.included(matched, t_dict):
                    char: str = s[left]
                    if char in t_dict:
                        # update record and count
                        if matched[char] > t_dict[char]:
                            matched[char] = matched[char] - 1
                        else:
                            break
                    left += 1

                # calculate window size; keep if best so far
                if right - left < best_len:
                    best_len = right - left
                    best_left = left
                    best_right = right

                # find next window
                if matched[s[left]] > 1:
                    matched[s[left]] -= 1
                else:
                    matched.pop(s[left])
                left += 1
        
        return s[best_left:best_right] if best_len != len(s) + 1 else ""
        
