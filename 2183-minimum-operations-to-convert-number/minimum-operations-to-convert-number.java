class Solution {
    public int minimumOperations(int[] nums, int start, int goal) {

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        boolean[] visited = new boolean[1001];
        visited[start] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;

            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                for (int num : nums) {
                    int[] nextValues = {
                        current + num,
                        current - num,
                        current ^ num
                    };

                    for (int next : nextValues) {
                        if (next == goal)
                            return steps;

                        if (next < 0 || next > 1000 || visited[next])
                            continue;

                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }
        }

        return -1;
    }
}
