class Solution {
    public int search(int[] nums, int target) {
        // decrease by a constatnt factor and conquer
        // to achieve O(log n)

        // since we know nums is strictly ascending (with
        // possible left-rotation), we can perform a
        // binary search for rotation point, k

        int l = 0;
        int r = nums.length - 1;
        int mid = (l + r) / 2; // int division

        int rotator = findRotator(nums, l, r);
        if (rotator != -1) {
            // k is position of rotator such that if
            // orig: [0][1][2]...[k-1][k]...[nums.len-1], then
            // new: [k][k+1]...[nums.len-1][0][1]...[k-1]
            // so (idx + k) % nums.len forms a modulo class allowing us to construct a 1-1
            // bijective mapping
            binarySearch(nums, l, r, rotator, target);
        } else {
            // normal binary search
            binarySearch(nums, l, r, 0, target);
        }

        // normal base case
        // if (nums[mid] == target) {
        // return mid;
        // }

    }

    private int findRotator(int[] nums, int l, int r) {
        // int l = 0;
        // int r = nums.length - 1;
        int mid = (l + r) / 2; // int division

        // BC: found rotator --- smallest element
        if (l == r) {
            return l;
        }

        if (nums[mid] > nums[r]) {
            // pivot is to the right of mid
            return findRotator(nums, mid + 1, r);
        } else {
            return findRotator(nums, l, mid);
        }

    }

    private int binarySearch(int[] nums, int l, int r, int k, int target) {
        int mid = (l + r) / 2;

        int k_l = (l + k) % nums.length;
        int k_r = (r + k) % nums.length;
        int k_m = k_l + k_r / 2;

        // BC1: target found
        if (nums[mid] == target) {
            return mid;
        }
        // BC2: target not found
        if (r - l <= 1) {
            return -1;
        }

        // recursive section

    }
}