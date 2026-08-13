class Solution {
    static class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        char prefChar;
        char suffChar;
        int len;

        public Node() {}

        public Node(char c) {
            this.maxLen = 1;
            this.prefLen = 1;
            this.suffLen = 1;
            this.prefChar = c;
            this.suffChar = c;
            this.len = 1;
        }
    }

    private Node[] tree;

    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node res = new Node();
        res.len = left.len + right.len;
        res.prefChar = left.prefChar;
        res.suffChar = right.suffChar;

        // Prefix length calculation
        res.prefLen = left.prefLen;
        if (left.prefLen == left.len && left.suffChar == right.prefChar) {
            res.prefLen = left.len + right.prefLen;
        }

        // Suffix length calculation
        res.suffLen = right.suffLen;
        if (right.suffLen == right.len && left.suffChar == right.prefChar) {
            res.suffLen = right.len + left.suffLen;
        }

        // Overall max length calculation
        res.maxLen = Math.max(left.maxLen, right.maxLen);
        if (left.suffChar == right.prefChar) {
            res.maxLen = Math.max(res.maxLen, left.suffLen + right.prefLen);
        }

        return res;
    }

    private void build(char[] chars, int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(chars, 2 * node, start, mid);
        build(chars, 2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char val) {
        if (start == end) {
            tree[node] = new Node(val);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        tree = new Node[4 * n];
        
        build(s.toCharArray(), 1, 0, n - 1);

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            update(1, 0, n - 1, idx, ch);
            ans[i] = tree[1].maxLen;
        }

        return ans;
    }
}