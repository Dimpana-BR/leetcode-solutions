class Solution {
    class SegmentTree {
        int[][] count;
        int[] total;
        int n;
        int k;

        public SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            count = new int[4 * n][k];
            total = new int[4 * n];
            build(0, 0, n - 1, nums);
        }

        private void build(int node, int l, int r, int[] nums) {
            if (l == r) {
                int val = nums[l] % k;
                total[node] = val;
                count[node][val] = 1;
                return;
            }
            int mid = l + (r - l) / 2;
            build(2 * node + 1, l, mid, nums);
            build(2 * node + 2, mid + 1, r, nums);
            merge(node);
        }

        private void merge(int node) {
            int left = 2 * node + 1;
            int right = 2 * node + 2;
            
            // Total product of the merged segment modulo k
            total[node] = (total[left] * total[right]) % k;
            
            // Start by carrying over the prefix frequencies from the left child
            for (int i = 0; i < k; i++) {
                count[node][i] = count[left][i];
            }
            
            // Then add the prefix frequencies from the right child, 
            // factoring in the total product of the entire left child
            for (int i = 0; i < k; i++) {
                int newMod = (total[left] * i) % k;
                count[node][newMod] += count[right][i];
            }
        }

        public void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                int v = val % k;
                total[node] = v;
                for (int i = 0; i < k; i++) {
                    count[node][i] = 0;
                }
                count[node][v] = 1;
                return;
            }
            int mid = l + (r - l) / 2;
            if (idx <= mid) {
                update(2 * node + 1, l, mid, idx, val);
            } else {
                update(2 * node + 2, mid + 1, r, idx, val);
            }
            merge(node);
        }

        public int[] query(int node, int l, int r, int ql, int qr) {
            // Returns an array of size k+1 where indices 0 to k-1 contain the prefix mod counts,
            // and the index k contains the total segment product modulo k.
            if (ql <= l && r <= qr) {
                int[] res = new int[k + 1];
                for (int i = 0; i < k; i++) {
                    res[i] = count[node][i];
                }
                res[k] = total[node];
                return res;
            }
            int mid = l + (r - l) / 2;
            
            if (qr <= mid) {
                return query(2 * node + 1, l, mid, ql, qr);
            } else if (ql > mid) {
                return query(2 * node + 2, mid + 1, r, ql, qr);
            } else {
                int[] left = query(2 * node + 1, l, mid, ql, qr);
                int[] right = query(2 * node + 2, mid + 1, r, ql, qr);
                
                int[] res = new int[k + 1];
                res[k] = (left[k] * right[k]) % k;
                
                for (int i = 0; i < k; i++) {
                    res[i] = left[i];
                }
                for (int i = 0; i < k; i++) {
                    res[(left[k] * i) % k] += right[i];
                }
                return res;
            }
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        SegmentTree st = new SegmentTree(nums, k);
        int m = queries.length;
        int[] ans = new int[m];
        
        for (int i = 0; i < m; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            
            // 1. Update the value at idx
            st.update(0, 0, nums.length - 1, idx, val);
            
            // 2. Compute prefixes for the subarray starting from 'start'
            int[] res = st.query(0, 0, nums.length - 1, start, nums.length - 1);
            
            // 3. Extract the x-value (occurrences of mod x)
            ans[i] = res[x];
        }
        
        return ans;
    }
}