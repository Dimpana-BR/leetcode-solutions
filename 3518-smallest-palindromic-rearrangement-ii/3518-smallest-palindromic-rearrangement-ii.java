class Solution {

    private long LIMIT;

    public String smallestPalindrome(String s, int k) {
        this.LIMIT = k;

        int[] cnt = new int[26];
        for (char ch : s.toCharArray()) {
            cnt[ch - 'a']++;
        }

        char mid = 0;
        int odd = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                odd++;
                mid = (char) ('a' + i);
            }
            cnt[i] /= 2;
        }

        if (odd > 1) return "";

        int halfLen = s.length() / 2;

        // Check if there are at least k permutations in total
        long totalWays = count(cnt);
        if (totalWays < k) return "";

        StringBuilder left = new StringBuilder();
        long K = k;

        for (int pos = 0; pos < halfLen; pos++) {
            boolean found = false;

            for (int c = 0; c < 26; c++) {
                if (cnt[c] == 0) continue;

                cnt[c]--;

                long ways = count(cnt);

                if (ways >= K) {
                    left.append((char) ('a' + c));
                    found = true;
                    break;
                }

                K -= ways;
                cnt[c]++;
            }

            if (!found) return "";
        }

        StringBuilder ans = new StringBuilder(left);
        if ((s.length() & 1) == 1) {
            ans.append(mid);
        }
        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long count(int[] cnt) {
        int remain = 0;
        for (int x : cnt) remain += x;

        long res = 1;
        int slots = remain;

        for (int f : cnt) {
            if (f == 0) continue;

            long factor = nCr(slots, f);
            
            // Overflow prevention check
            if (factor > 0 && res > (LIMIT + 1) / factor) {
                return LIMIT + 1;
            }

            res *= factor;

            if (res > LIMIT) {
                return LIMIT + 1;
            }

            slots -= f;
        }

        return res;
    }

    // Calculates nCr efficiently on the fly capped at LIMIT + 1
    private long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;

        if (r > n - r) {
            r = n - r; // Optimize using symmetry nCr = nC(n-r)
        }

        long res = 1;
        for (int i = 1; i <= r; i++) {
            res = res * (n - i + 1) / i;
            if (res > LIMIT) {
                return LIMIT + 1;
            }
        }
        return res;
    }
}