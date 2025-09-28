class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
        // recursive approach
        // if (n == 1) return true;
        // if (n <= 0 || n % 2 != 0) return false;
        // return isPowerOfTwo(n / 2);
    }
}