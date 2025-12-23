class Solution {
    int m, n;
    int[][] grid;
    boolean[][] visited;
    int[][] dir = {{1,0},{0,1}}; // down, right

    public boolean isPossibleToCutPath(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        // Step 1: check if path exists
        visited = new boolean[m][n];
        if (!dfs(0, 0)) return true;

        // Step 2: remove the found path (except start & end)
        visited = new boolean[m][n];
        removePath(0, 0);

        // Step 3: check again
        visited = new boolean[m][n];
        return !dfs(0, 0);
    }

    // DFS to check path existence
    boolean dfs(int r, int c) {
        if (r >= m || c >= n || grid[r][c] == 0 || visited[r][c])
            return false;
        if (r == m - 1 && c == n - 1)
            return true;

        visited[r][c] = true;
        for (int[] d : dir) {
            if (dfs(r + d[0], c + d[1]))
                return true;
        }
        return false;
    }

    // Remove one path
    boolean removePath(int r, int c) {
        if (r >= m || c >= n || grid[r][c] == 0 || visited[r][c])
            return false;
        if (r == m - 1 && c == n - 1)
            return true;

        visited[r][c] = true;
        for (int[] d : dir) {
            if (removePath(r + d[0], c + d[1])) {
                if (!(r == 0 && c == 0))
                    grid[r][c] = 0; // remove path cell
                return true;
            }
        }
        return false;
    }
}
