class Solution {

    private List<List<Integer>> tree;
    private int[] present;
    private int[] future;
    private int budget;
    private HashMap<String, Pair> memo;
    private static final int NEG_INF = Integer.MIN_VALUE / 2; // Use a large negative number

    public int maxProfit(
            int n,
            int[] present,
            int[] future,
            int[][] hierarchy,
            int budget) {
        this.present = present;
        this.future = future;
        this.budget = budget;
        this.tree = new ArrayList<>(n);
        this.memo = new HashMap<>();

        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int[] edge : hierarchy) {
            int u = edge[0];
            int v = edge[1];
            tree.get(u - 1).add(v - 1);
        }

        Pair result = dp(0, -1);
        int maxProfit = 0;
        for (int profit : result.noDiscount) {
            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }

    private Pair dp(int u, int parent) {
        String key = u + "_" + parent;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int[] noDiscount = new int[budget + 1];
        int[] withDiscount = new int[budget + 1];
        Arrays.fill(noDiscount, 0);
        Arrays.fill(withDiscount, 0);

        for (int v : tree.get(u)) {
            if (v == parent) {
                continue;
            }
            Pair childResult = dp(v, u);
            noDiscount = merge(noDiscount, childResult.noDiscount);
            withDiscount = merge(withDiscount, childResult.withDiscount);
        }

        int[] newDp0 = Arrays.copyOf(noDiscount, budget + 1);
        int[] newDp1 = Arrays.copyOf(noDiscount, budget + 1);

        int fullCost = present[u];
        for (int b = fullCost; b <= budget; b++) {
            if (withDiscount[b - fullCost] > NEG_INF) {
                int profit = future[u] - fullCost;
                newDp0[b] = Math.max(newDp0[b], withDiscount[b - fullCost] + profit);
            }
        }

        int halfCost = present[u] / 2;
        for (int b = halfCost; b <= budget; b++) {
            if (withDiscount[b - halfCost] > NEG_INF) {
                int profit = future[u] - halfCost;
                newDp1[b] = Math.max(newDp1[b], withDiscount[b - halfCost] + profit);
            }
        }

        Pair result = new Pair(newDp0, newDp1);
        memo.put(key, result);
        return result;
    }

    private int[] merge(int[] dpA, int[] dpB) {
        int[] merged = new int[budget + 1];
        Arrays.fill(merged, NEG_INF);
        for (int i = 0; i <= budget; i++) {
            for (int j = 0; i + j <= budget; j++) {
                if (dpA[i] > NEG_INF && dpB[j] > NEG_INF) {
                    merged[i + j] = Math.max(merged[i + j], dpA[i] + dpB[j]);
                }
            }
        }
        return merged;
    }

    private static class Pair {
        int[] noDiscount;
        int[] withDiscount;

        Pair(int[] noDiscount, int[] withDiscount) {
            this.noDiscount = noDiscount;
            this.withDiscount = withDiscount;
        }
    }
}