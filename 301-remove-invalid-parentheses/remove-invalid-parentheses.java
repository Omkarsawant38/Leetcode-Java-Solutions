class Solution {
    // Instance variables to avoid passing multiple parameters
    private String inputString;
    private int stringLength;
    private Set<String> validResults = new HashSet<>();


    public List<String> removeInvalidParentheses(String s) {
        this.inputString = s;
        this.stringLength = s.length();
      
        // Calculate minimum number of left and right parentheses to remove
        int leftToRemove = 0;
        int rightToRemove = 0;
      
        // First pass: count unmatched parentheses
        for (char character : s.toCharArray()) {
            if (character == '(') {
                leftToRemove++;
            } else if (character == ')') {
                if (leftToRemove > 0) {
                    // Found a matching left parenthesis
                    leftToRemove--;
                } else {
                    // No matching left parenthesis, need to remove this right one
                    rightToRemove++;
                }
            }
        }
      
        // Start DFS to find all valid combinations
        dfs(0, leftToRemove, rightToRemove, 0, 0, "");
      
        return new ArrayList<>(validResults);
    }

    private void dfs(int index, int leftToRemove, int rightToRemove, 
                     int leftCount, int rightCount, String currentString) {
        // Base case: reached end of string
        if (index == stringLength) {
            // Check if we've removed all required parentheses
            if (leftToRemove == 0 && rightToRemove == 0) {
                validResults.add(currentString);
            }
            return;
        }
      
        // Pruning conditions:
        // 1. Not enough characters left to remove required parentheses
        // 2. Right parentheses count exceeds left (invalid state)
        if (stringLength - index < leftToRemove + rightToRemove || leftCount < rightCount) {
            return;
        }
      
        char currentChar = inputString.charAt(index);
      
        // Option 1: Remove current character if it's a parenthesis we need to remove
        if (currentChar == '(' && leftToRemove > 0) {
            // Remove this left parenthesis
            dfs(index + 1, leftToRemove - 1, rightToRemove, leftCount, rightCount, currentString);
        }
        if (currentChar == ')' && rightToRemove > 0) {
            // Remove this right parenthesis
            dfs(index + 1, leftToRemove, rightToRemove - 1, leftCount, rightCount, currentString);
        }
      
        int leftIncrement = (currentChar == '(') ? 1 : 0;
        int rightIncrement = (currentChar == ')') ? 1 : 0;
      
        dfs(index + 1, leftToRemove, rightToRemove, 
            leftCount + leftIncrement, rightCount + rightIncrement, 
            currentString + currentChar);
    }
}