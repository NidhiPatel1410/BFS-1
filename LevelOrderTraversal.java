// In this problem, we are using queue(fifo), and adding left and right value in queue, and depending in the size, we are doing poll operation
// and adding to the list.

// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // Base case
        if (root == null) {
            return new ArrayList<>();
        }
        // Result list
        List<List<Integer>> result = new ArrayList<>();
        // Declare queue
        Queue<TreeNode> q = new LinkedList<>();
        // Add root to the queue
        q.add(root);
        // Loop till queue is empty
        while (!q.isEmpty()) {
            // Calc size of inner list(one level at a time)
            int size = q.size();
            // Inner list
            List<Integer> rows = new ArrayList<>();
            // Loop till size
            for (int i = 0; i < size; i++) {
                // Poll from queue
                TreeNode value = q.poll();
                // Add it to rows
                rows.add(value.val);
                // Check if that node has left and right value
                if (value.left != null) {
                    // Add it to queue
                    q.add(value.left);
                }
                if (value.right != null) {
                    // Add it to queue
                    q.add(value.right);
                }
            }
            // Add the row to the result
            result.add(rows);
        }
        // Return result
        return result;
    }
}