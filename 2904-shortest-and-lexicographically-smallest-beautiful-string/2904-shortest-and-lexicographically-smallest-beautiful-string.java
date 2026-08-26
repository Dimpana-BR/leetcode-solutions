class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int left = 0;
        int ones = 0;

        String answer = "";

        for (int right = 0; right < s.length(); right++) {

            // Expand window
            if (s.charAt(right) == '1') {
                ones++;
            }

            // Window has exactly k ones
            while (ones == k) {

                String candidate = s.substring(left, right + 1);

                // Update answer
                if (answer.equals("")
                        || candidate.length() < answer.length()
                        || (candidate.length() == answer.length()
                            && candidate.compareTo(answer) < 0)) {

                    answer = candidate;
                }

                // Shrink window
                if (s.charAt(left) == '1') {
                    ones--;
                }

                left++;
            }
        }

        return answer;
    }
}