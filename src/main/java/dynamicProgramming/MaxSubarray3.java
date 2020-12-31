package dynamicProgramming;

/***
 * @Description: Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.
 * The number in each subarray should be contiguous.
 * Return the largest sum.
 */
public class MaxSubarray3 {
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(int[] nums, int k) {
        if (nums == null || nums.length < k){
            return 0;
        }

        int n = nums.length;
        int[][] local = new int[k + 1][n + 1];
        int[][] global = new int[k + 1][n + 1];

        for (int i = 1; i <= k; i++) {
            local[i][i - 1] = Integer.MIN_VALUE;
            for (int j = i; j <= n; j++) {
                local[i][j] = Math.max(local[i][j - 1],global[i - 1][j - 1] + nums[j - 1]);
                if (j == i){
                    global[i][j] = local[i][j];
                }else {
                    global[i][j] = Math.max(global[i][j - 1],local[i][j]);
                }
            }
        }
        return global[k][n];
    }
}
