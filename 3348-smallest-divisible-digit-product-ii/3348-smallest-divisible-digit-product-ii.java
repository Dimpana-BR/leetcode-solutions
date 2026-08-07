import java.util.*;

class Solution {
    public String smallestNumber(String num, long t) {
        long temp = t;
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        while (temp % 2 == 0) { c2++; temp /= 2; }
        while (temp % 3 == 0) { c3++; temp /= 3; }
        while (temp % 5 == 0) { c5++; temp /= 5; }
        while (temp % 7 == 0) { c7++; temp /= 7; }

        if (temp > 1) return "-1";

        int n = num.length();

        // 1. Check if num itself is valid
        int firstZero = num.indexOf('0');
        if (firstZero == -1) {
            int cur2 = c2, cur3 = c3, cur5 = c5, cur7 = c7;
            for (int i = 0; i < n; i++) {
                int d = num.charAt(i) - '0';
                if (d == 2) cur2--;
                else if (d == 3) cur3--;
                else if (d == 4) cur2 -= 2;
                else if (d == 5) cur5--;
                else if (d == 6) { cur2--; cur3--; }
                else if (d == 7) cur7--;
                else if (d == 8) cur2 -= 3;
                else if (d == 9) cur3 -= 2;
            }
            if (cur2 <= 0 && cur3 <= 0 && cur5 <= 0 && cur7 <= 0) {
                return num;
            }
        }

        // Precompute prefix factor requirements up to valid digits
        int maxI = (firstZero == -1) ? n - 1 : firstZero;
        int[][] prefixReq = new int[n + 1][4];
        prefixReq[0] = new int[]{c2, c3, c5, c7};

        int limit = (firstZero == -1) ? n : firstZero;
        for (int i = 0; i < limit; i++) {
            int d = num.charAt(i) - '0';
            prefixReq[i + 1] = deduct(prefixReq[i], d);
        }

        // 2. Try prefix matches ending at index i - 1 (from maxI down to 0)
        for (int i = maxI; i >= 0; i--) {
            int remLen = n - 1 - i;
            int startDigit = num.charAt(i) - '0' + 1;

            for (int d = startDigit; d <= 9; d++) {
                int[] nextReq = deduct(prefixReq[i], d);
                int minNeeded = getMinLen(nextReq[0], nextReq[1], nextReq[2], nextReq[3]);

                if (minNeeded <= remLen) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append(d);
                    sb.append(buildFullSuffix(nextReq[0], nextReq[1], nextReq[2], nextReq[3], remLen));
                    return sb.toString();
                }
            }
        }

        // 3. If length n is insufficient, extend string length
        int minNeeded = getMinLen(c2, c3, c5, c7);
        int targetLen = Math.max(n + 1, minNeeded);

        return buildFullSuffix(c2, c3, c5, c7, targetLen);
    }

    private int[] deduct(int[] req, int d) {
        int[] res = req.clone();
        if (d == 2) res[0]--;
        else if (d == 3) res[1]--;
        else if (d == 4) res[0] -= 2;
        else if (d == 5) res[2]--;
        else if (d == 6) { res[0]--; res[1]--; }
        else if (d == 7) res[3]--;
        else if (d == 8) res[0] -= 3;
        else if (d == 9) res[1] -= 2;
        return res;
    }

    private int getMinLen(int c2, int c3, int c5, int c7) {
        String base23 = getBest23(c2, c3);
        return base23.length() + Math.max(0, c5) + Math.max(0, c7);
    }

    private String buildFullSuffix(int c2, int c3, int c5, int c7, int totalLen) {
        String base23 = getBest23(c2, c3);
        StringBuilder sb = new StringBuilder(base23);
        for (int k = 0; k < Math.max(0, c5); k++) sb.append('5');
        for (int k = 0; k < Math.max(0, c7); k++) sb.append('7');

        int padOnes = totalLen - sb.length();
        for (int k = 0; k < padOnes; k++) sb.append('1');

        char[] chars = sb.toString().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    // O(1) greedy construction for factors of 2 and 3
    private String getBest23(int c2, int c3) {
        if (c2 <= 0 && c3 <= 0) return "";
        c2 = Math.max(0, c2);
        c3 = Math.max(0, c3);

        int num8 = c2 / 3;
        int rem2 = c2 % 3;
        int num9 = c3 / 2;
        int rem3 = c3 % 2;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num8; i++) sb.append('8');
        for (int i = 0; i < num9; i++) sb.append('9');

        if (rem2 == 0 && rem3 == 1) sb.append('3');
        else if (rem2 == 1 && rem3 == 0) sb.append('2');
        else if (rem2 == 1 && rem3 == 1) sb.append('6');
        else if (rem2 == 2 && rem3 == 0) sb.append('4');
        else if (rem2 == 2 && rem3 == 1) { sb.append('2'); sb.append('6'); }

        char[] chars = sb.toString().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}