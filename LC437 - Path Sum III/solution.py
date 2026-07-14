# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


from typing import Optional


class Solution:
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> int:
        # base case: sum of 0 'seen once'
        prefix_sums: dict[int, int] = {0: 1}

        return self.dfs(root, 0, targetSum, prefix_sums)

    def dfs(
        self,
        node: Optional[TreeNode],
        current_sum: int,
        target_sum: int,
        prefix_sums: dict[int, int],
    ) -> int:
        if node is None:
            return 0

        # update running sum
        current_sum += node.val
        
        # look up matching prefix -> (current_sum - target_sum)
        num_ending_here = prefix_sums.get(current_sum - target_sum, 0)
        
        # store running sum for child nodes
        prefix_sums[current_sum] = prefix_sums.get(current_sum)
