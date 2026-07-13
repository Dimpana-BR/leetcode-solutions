import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";
        
        // Loop through all possible lengths of sequential digits (from 2 to 9)
        for (int length = 2; length <= 9; length++) {
            // Slide the window across the "123456789" string
            for (int start = 0; start <= 9 - length; start++) {
                String sub = digits.substring(start, start + length);
                int num = Integer.parseInt(sub);
                
                // If the number is within the range, add it to our list
                if (num >= low && num <= high) {
                    result.add(num);
                }
                // Optimization: If the generated number exceeds high, 
                // any larger length numbers will also exceed it.
                if (num > high) {
                    break;
                }
            }
        }
        
        return result;
    }
}