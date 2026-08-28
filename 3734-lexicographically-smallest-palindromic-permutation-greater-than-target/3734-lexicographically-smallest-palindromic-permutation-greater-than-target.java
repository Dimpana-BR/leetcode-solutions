import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Check if a palindromic permutation is possible
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        // Half frequencies for building the first half
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        int halfLen = n / 2;
        
        // We want to find the longest common prefix of the first half with target's first half
        // Try match length L from halfLen down to 0
        for (int L = halfLen; L >= 0; L--) {
            int[] currentHalfCount = halfCount.clone();
            boolean possible = true;
            StringBuilder prefix = new StringBuilder();

            // 1. Try to match target[0...L-1]
            for (int i = 0; i < L; i++) {
                char tChar = target.charAt(i);
                if (currentHalfCount[tChar - 'a'] > 0) {
                    currentHalfCount[tChar - 'a']--;
                    prefix.append(tChar);
                } else {
                    possible = false;
                    break;
                }
            }

            if (!possible) continue;

            // 2. If L < halfLen, we must pick a character at index L strictly greater than target[L]
            if (L < halfLen) {
                char targetChar = target.charAt(L);
                boolean foundNext = false;

                for (int c = targetChar - 'a' + 1; c < 26; c++) {
                    if (currentHalfCount[c] > 0) {
                        currentHalfCount[c]--;
                        StringBuilder candidatePrefix = new StringBuilder(prefix);
                        candidatePrefix.append((char) ('a' + c));
                        
                        // Fill the rest of the first half with smallest available characters
                        for (int k = 0; k < 26; k++) {
                            while (currentHalfCount[k] > 0) {
                                candidatePrefix.append((char) ('a' + k));
                                currentHalfCount[k]--;
                            }
                        }

                        // Form complete palindrome
                        String fullPalindrome = constructPalindrome(candidatePrefix.toString(), midChar, n % 2 != 0);
                        if (fullPalindrome.compareTo(target) > 0) {
                            return fullPalindrome;
                        }
                        
                        // Reset count state to try next character option
                        foundNext = true;
                        break; 
                    }
                }
            } else { // L == halfLen
                // Form palindrome with exact match on first half and check if it's strictly greater
                String fullPalindrome = constructPalindrome(prefix.toString(), midChar, n % 2 != 0);
                if (fullPalindrome.compareTo(target) > 0) {
                    return fullPalindrome;
                }
            }
        }

        return "";
    }

    private String constructPalindrome(String half, char midChar, boolean isOdd) {
        StringBuilder sb = new StringBuilder(half);
        if (isOdd) {
            sb.append(midChar);
        }
        for (int i = half.length() - 1; i >= 0; i--) {
            sb.append(half.charAt(i));
        }
        return sb.toString();
    }
}