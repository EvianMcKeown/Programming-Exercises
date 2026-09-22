import java.util.HashMap;

class Solution3 {
    public int[] twoSum(int[] nums, int target) {
        // HashMap: val -> index, then lookup complement
        HashMap<Integer, Integer> hMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hMap.containsKey(target - nums[i])) {
                return new int[] { i, hMap.get(target - nums[i]) };
            }
            hMap.put(nums[i], i);
        }
        return new int[] { 0 };
    }
}