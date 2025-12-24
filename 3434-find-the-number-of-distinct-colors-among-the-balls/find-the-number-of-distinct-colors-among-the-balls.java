class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        Map<Integer, Integer> ballColor = new HashMap<>();
        Map<Integer, Integer> colorCount = new HashMap<>();
        
        int n = queries.length;
        int[] result = new int[n];
        int distinct = 0;

        for (int i = 0; i < n; i++) {
            int ball = queries[i][0];
            int color = queries[i][1];

            // If ball already had a color
            if (ballColor.containsKey(ball)) {
                int oldColor = ballColor.get(ball);
                int cnt = colorCount.get(oldColor) - 1;
                colorCount.put(oldColor, cnt);

                if (cnt == 0) {
                    colorCount.remove(oldColor);
                    distinct--;
                }
            }

            // Assign new color
            ballColor.put(ball, color);
            colorCount.put(color, colorCount.getOrDefault(color, 0) + 1);

            if (colorCount.get(color) == 1) {
                distinct++;
            }

            result[i] = distinct;
        }

        return result;
    }
}
