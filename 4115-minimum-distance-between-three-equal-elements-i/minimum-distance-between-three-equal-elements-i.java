class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            
            indexMap.putIfAbsent(currentNum, new ArrayList<>());
            List<Integer> indices = indexMap.get(currentNum);
            
            indices.add(i);
            
            if (indices.size() >= 3) {
                int i_prime = indices.get(indices.size() - 3);
                int k_prime = i;
                
                int currentDistance = 2 * (k_prime - i_prime);
                
                minDistance = Math.min(minDistance, currentDistance);
            }
        }
        
        return (minDistance == Integer.MAX_VALUE) ? -1 : minDistance;
    }
}