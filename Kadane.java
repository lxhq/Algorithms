//Kadane's algorithm for finding the maxmium subarray in array
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int runningSum = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            runningSum = nums[i] + (runningSum > 0 ? runningSum : 0);
            res = Math.max(res, runningSum);
        }
        return res;
    }
}
