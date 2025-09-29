class Solution {
    public int thirdMax(int[] nums) {

        int firstlarge = Integer.MIN_VALUE;
        int secondlarge = Integer.MIN_VALUE;
        int thirdlarge = Integer.MIN_VALUE;

        boolean foundFirst = false, foundSecond = false, foundThird = false;

        for (int i = 0; i <= nums.length - 1; i++) {
            if (!foundFirst || nums[i] > firstlarge) {
                firstlarge = nums[i];
                foundFirst = true;
            }
        }

        for (int i = 0; i <= nums.length - 1; i++) {
            if ((!foundSecond || nums[i] > secondlarge) && nums[i] != firstlarge) {
                secondlarge = nums[i];
                foundSecond = true;
            }
        }

        for (int j = 0; j <= nums.length - 1; j++) {
            if ((!foundThird || nums[j] > thirdlarge) && nums[j] != firstlarge && nums[j] != secondlarge) {
                thirdlarge = nums[j];
                foundThird = true;
            }
        }

        if (!foundThird) {
            return firstlarge;
        }

        return thirdlarge;
    }
}