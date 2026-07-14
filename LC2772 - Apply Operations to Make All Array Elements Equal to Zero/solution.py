class Solution:
    @staticmethod
    def checkArray(nums: list[int], k: int) -> bool:
        """Greedy Solution:
        start from left most window pos
        perform no. of ops to reduce left most item in widows to zero, incr window pos
        if lft most itm in wndw < 0, return false - else do step above
        if at last window position, check remaining vals > 0 - return true
        """

        """ Prefix-sum + Difference Array Solution:
                VARS:
                    cur_diff = track difference for pos i, so true_val = nums[i] - cur_diff
                    expire[] = track when difference expires
                        -> if we decrement n times at pos i, expire[i+k] = n
                        
                start from left most window pos
        """

        cur_diff: int = 0
        # expire: list[int] = [0 for _ in range(k + 1)]
        expire: list[int] = [0] * (len(nums) + 1)
        n: int = len(nums)

        i: int = 0
        true_val: int = 0

        for i in range(n):
            cur_diff = cur_diff - expire[i]
            true_val = nums[i] - cur_diff

            if true_val < 0:
                return False

            if true_val > 0:
                if i <= n - k:
                    cur_diff += true_val
                    expire[i + k] = true_val
                else:
                    return False

        return True


nums: list[int] = [
    60,
    72,
    87,
    89,
    63,
    52,
    64,
    62,
    31,
    37,
    57,
    83,
    98,
    94,
    92,
    77,
    94,
    91,
    87,
    100,
    91,
    91,
    50,
    26,
]
result = Solution.checkArray(nums=nums, k=4)
print(result)