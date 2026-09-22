import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // naive solution
        /*for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++){
                if (nums[i] + nums[j] == target){
                    return new int[] {i, j}; 
                }
            }
        }
        return new int[] {};*/

        // Hash Map
        Map<Integer, Integer> map = new HashMap<>();
        // want to store mapping from value to index, then we can see if the complement of a value exists as a key, returning its value (index) with int i.
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}
