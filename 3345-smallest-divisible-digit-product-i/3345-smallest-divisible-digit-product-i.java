class Solution {
    public int smallestNumber(int n, int t) {
        for (int x = n; ; x++) {
            if (getDigitProduct(x) % t == 0) {
                return x;
            }
        }
    }

    private int getDigitProduct(int num) {
        int prod = 1;
        while (num > 0) {
            prod *= num % 10;
            num /= 10;
        }
        return prod;
    }
}