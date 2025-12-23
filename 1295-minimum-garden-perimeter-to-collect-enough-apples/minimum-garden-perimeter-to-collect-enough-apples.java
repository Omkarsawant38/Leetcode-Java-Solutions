class Solution {
    public long minimumPerimeter(long neededApples) {
        long left = 0, right = 1_000_000; // safe bound
        
        while (left < right) {
            long mid = (left + right) / 2;
            long apples = 2 * mid * (mid + 1) * (2 * mid + 1);
            
            if (apples >= neededApples) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return 8 * left;
    }
}