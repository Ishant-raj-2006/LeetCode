
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        // Sort logs by timestamp
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int k = queries.length;
        int[] ans = new int[k];

        // Store query indices
        Integer[] order = new Integer[k];

        for (int i = 0; i < k; i++) {
            order[i] = i;
        }

        // Sort queries by time
        Arrays.sort(order, (a, b) ->
            Integer.compare(queries[a], queries[b])
        );

        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;
        int active = 0;

        for (int idx : order) {

            int q = queries[idx];

            // Add logs up to current query time
            while (right < logs.length && logs[right][1] <= q) {

                int server = logs[right][0];

                map.put(server, map.getOrDefault(server, 0) + 1);

                if (map.get(server) == 1) {
                    active++;
                }

                right++;
            }

            // Remove logs outside the time window
            while (left < right && logs[left][1] < q - x) {

                int server = logs[left][0];

                map.put(server, map.get(server) - 1);

                if (map.get(server) == 0) {
                    map.remove(server);
                    active--;
                }

                left++;
            }

            // Servers with zero requests
            ans[idx] = n - active;
        }

        return ans;
    }
}
