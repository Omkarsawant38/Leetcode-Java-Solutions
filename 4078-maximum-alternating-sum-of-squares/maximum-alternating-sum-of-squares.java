class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] sq = new long[n];

        // Step 1: square all elements
        for (int i = 0; i < n; i++) {
            sq[i] = (long) nums[i] * nums[i];
        }

        // Step 2: sort squares
        Arrays.sort(sq);

        int positiveCount = (n + 1) / 2; // number of + positions
        long score = 0;

        // Step 3: add largest squares
        for (int i = n - 1; i >= n - positiveCount; i--) {
            score += sq[i];
        }

        // Step 4: subtract remaining squares
        for (int i = 0; i < n - positiveCount; i++) {
            score -= sq[i];
        }

        return score;
    }
}