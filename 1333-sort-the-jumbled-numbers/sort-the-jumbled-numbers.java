class Solution {
    // Global mapping array to store digit transformations
    private int[] digitMapping;
    public int[] sortJumbled(int[] mapping, int[] nums) {
        this.digitMapping = mapping;
        int length = nums.length;
      
        // Create a 2D array to store [mappedValue, originalIndex] pairs
        int[][] mappedPairs = new int[length][2];
      
        // Calculate mapped value for each number and store with its original index
        for (int i = 0; i < length; i++) {
            mappedPairs[i] = new int[] {getMappedValue(nums[i]), i};
        }
      
        // Sort by mapped value first, then by original index to maintain stability
        Arrays.sort(mappedPairs, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];  // If mapped values are equal, maintain original order
            }
            return a[0] - b[0];  // Otherwise sort by mapped value
        });
      
        // Build the result array using the sorted indices
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = nums[mappedPairs[i][1]];
        }
      
        return result;
    }

    private int getMappedValue(int number) {
        // Special case: if the number is 0, return its mapped value directly
        if (number == 0) {
            return digitMapping[0];
        }
      
        int mappedValue = 0;
        int placeValue = 1;  // Represents 1, 10, 100, 1000, etc.
      
        // Process each digit from right to left
        while (number > 0) {
            int digit = number % 10;  // Extract the rightmost digit
            int mappedDigit = digitMapping[digit];  // Get the mapped value for this digit
            mappedValue = mappedValue + (placeValue * mappedDigit);  // Add to result
            placeValue *= 10;  // Move to next place value
            number /= 10;  // Remove the processed digit
        }
      
        return mappedValue;
    }
}