// In this problem, we are using queue(fifo), and adding left and right value in queue, and depending in the size, we are doing poll operation
// and adding to the list.

// Time Complexity : O(n)
// Space Complexity : O(n)
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

// DFS
// In this approach, we are maintaining level for each node, checking if level
// == size of resultlist, add a new inner list and adding the node value
// to it. Else, if level!=size, that means inner list already present, so simply
// add the node to it. Go left and right recursively.

// Time Complexity : O(n)
// Space Complexity : O(h)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    List<List<Integer>> result;

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        result = new ArrayList<>();
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level == result.size()) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}