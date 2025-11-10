class Solution {
    private List<Integer> result = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        performPostorderDFS(root);
        return result;
    }
    private void performPostorderDFS(TreeNode node) {
        if (node == null) {
            return;
        }
      
        performPostorderDFS(node.left);
        performPostorderDFS(node.right);
        result.add(node.val);
    }
}