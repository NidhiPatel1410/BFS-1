// Since there is a dependancy and there are connections in courses, we can take the hint that it is a tree problem.
// In this approach we are trying to find out the indegree out of each course and creating a indegree array of it. And then creating a hashmap, 
// with courses as the key and there values is a list of courses that are dependant on it. So, we traverse through indegree array and whatever index
// is having value 0, that means no incoming node on it, so does not require any prereq, so add that to the queue. Now, notice how we are doing 
// bfs but in the order that we want (i.e. course having 0 incoming nodes should be taken first), so it is called topological sort. And then,
// run a loop till queue is empty, poll the element, and check this course is the key in map, no then continue, yes than bring that list, and for
// each element in that list, indegree will become -- because we completed it's one of the prereq course. So go to that index in indegree and do 
// indegree--. Then check if the indegree is zero, add it to queue. So in the end, if all the courses have indegree 0, that means all courses 
// taken. Notice courses having indegree 0 are going in queue, so just keep count and increment it. At last if count == numCourses, return true.

// Time Complexity : O(V+E)  -- Vertices + Edges
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // If no courses, nothing to take, so true
        if (numCourses == 0) {
            return true;
        }
        // Declare indegree array of numCourses size
        int[] indegree = new int[numCourses];
        // Declare map for storing the dependancy list
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        // Queue for BFS
        Queue<Integer> q = new LinkedList<>();
        // Count for result
        int count = 0;
        // Now fill the indegree array and dependancy map
        // Go through each inner list of prerequisites
        for (int[] edges : prerequisites) {
            // Pop both value
            int to = edges[0];
            int from = edges[1];
            // Since the 'to' is having the course that is dependant on 'from', increment
            // it's indegree by 1
            indegree[to]++;
            // Now, add key 'from' in map and 'to' in it's value list
            if (!adj.containsKey(from)) {
                adj.put(from, new ArrayList<>());
            }
            adj.get(from).add(to);

        }
        System.out.println(adj);
        // Now, whichever course having 0 indegree, add to queue and increment count
        for (int i = 0; i < numCourses; i++) {
            System.out.println(indegree[i]);
            if (indegree[i] == 0) {
                count++;
                q.add(i);
            }
        }
        // Run a loop till queue is empty
        while (!q.isEmpty()) {
            // Poll or take the current course
            int curr = q.poll();
            // Check if it having any courses that are dependant on it, i.e. if the key is
            // present in map
            if (!adj.containsKey(curr)) {
                // If no, then continue
                continue;

            }
            // Yes than get the list of those courses, and decrement their indegree and
            // check if indegree became zero, add it to queue
            List<Integer> currList = adj.get(curr);
            for (int v : currList) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    count++;
                    q.add(v);
                }
            }

        }
        // If count==numCourses, means all courses taken
        return count == numCourses;
    }
}