

public class Solution {
    
    // Helper Segment Tree for Range Maximum Query
    private static class SegmentTree {
        int n;
        int[] tree;

        public SegmentTree(int[] arr) {
            this.n = arr.length;
            if (n > 0) {
                this.tree = new int[4 * n];
                build(arr, 0, 0, n - 1);
            }
        }

        private void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }
            int mid = start + (end - start) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
        }

        public int query(int node, int start, int end, int l, int r) {
            if (l > r || start > r || end < l) return 0;
            if (l <= start && end <= r) return tree[node];

            int mid = start + (end - start) / 2;
            int leftMax = query(2 * node + 1, start, mid, l, r);
            int rightMax = query(2 * node + 2, mid + 1, end, l, r);
            return Math.max(leftMax, rightMax);
        }

        public int queryRange(int l, int r) {
            if (n == 0 || l > r) return 0;
            return query(0, 0, n - 1, l, r);
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnesInS = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') totalOnesInS++;
        }

        // 1. Group consecutive characters into segments
        List<int[]> segments = new ArrayList<>(); // [type ('0' or '1'), start, end]
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                segments.add(new int[]{s.charAt(i) - '0', start, i});
                start = i + 1;
            }
        }

        int m = segments.size();
        int[] segMap = new int[n]; // Maps string index to segment index
        for (int i = 0; i < m; i++) {
            for (int j = segments.get(i)[1]; j <= segments.get(i)[2]; j++) {
                segMap[j] = i;
            }
        }

        // 2. Precompute gain for fully internal 1-segments
        int[] internalGain = new int[m];
        for (int i = 1; i < m - 1; i++) {
            if (segments.get(i)[0] == 1) { // 1-segment bounded by 0-segments
                int lenLeft = segments.get(i - 1)[2] - segments.get(i - 1)[1] + 1;
                int lenRight = segments.get(i + 1)[2] - segments.get(i + 1)[1] + 1;
                internalGain[i] = lenLeft + lenRight;
            }
        }

        SegmentTree segTree = new SegmentTree(internalGain);
        List<Integer> ans = new ArrayList<>(queries.length);

        // 3. Process each query
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            int segL = segMap[l];
            int segR = segMap[r];

            int maxGain = 0;

            // Query fully internal 1-segments in range [segL + 2, segR - 2]
            int firstInternal = segL + 2;
            int lastInternal = segR - 2;
            if (firstInternal <= lastInternal) {
                maxGain = Math.max(maxGain, segTree.queryRange(firstInternal, lastInternal));
            }

            // Check boundary candidate 1-segments
            int[] candidates = {segL + 1, segR - 1};
            for (int cand : candidates) {
                if (cand >= 0 && cand < m && segments.get(cand)[0] == 1) {
                    int[] curr1 = segments.get(cand);
                    // 1-segment must be fully contained within [l, r]
                    if (curr1[1] >= l && curr1[2] <= r) {
                        int left0Len = 0;
                        int right0Len = 0;

                        if (cand > 0 && segments.get(cand - 1)[0] == 0) {
                            int oL = Math.max(l, segments.get(cand - 1)[1]);
                            int oR = Math.min(r, segments.get(cand - 1)[2]);
                            if (oL <= oR) left0Len = oR - oL + 1;
                        }

                        if (cand < m - 1 && segments.get(cand + 1)[0] == 0) {
                            int oL = Math.max(l, segments.get(cand + 1)[1]);
                            int oR = Math.min(r, segments.get(cand + 1)[2]);
                            if (oL <= oR) right0Len = oR - oL + 1;
                        }

                        // BOTH adjacent 0-segments must be present in [l, r] to surround the '1' block in t
                        if (left0Len > 0 && right0Len > 0) {
                            maxGain = Math.max(maxGain, left0Len + right0Len);
                        }
                    }
                }
            }

            ans.add(totalOnesInS + maxGain);
        }

        return ans;
    }
}