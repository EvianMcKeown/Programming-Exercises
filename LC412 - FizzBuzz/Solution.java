import java.util.Arrays;
import java.util.List;

class Solution {
    public List<String> fizzBuzz(int n) {
        String[] s = new String[n + 1];

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                s[i] = "Fizz";
            }
            if (i % 5 == 0) {
                s[i] = "Buzz";
            }
            if (i % 3 == 0 && i % 5 == 0) {
                s[i] = "FizzBuzz";
            }
            if (s[i] == null) {
                s[i] = Integer.toString(i);
            }
        }
        /*
         * --- ASYMPTOTICALLY WORSE, AND BRANCHING NOT GOOD ENOUGH TO OVERCOME
         * for (int i = 1; i <= n; i++){
         * s[i] = Integer.toString(i);
         * }
         * for (int i = 3; i <= n; i += 3){
         * if (!Character.isDigit(s[i].charAt(0))) {
         * s[i] += "Fizz";
         * } else {
         * s[i] = "Fizz";
         * }
         * }
         * 
         * for (int i = 5; i <= n; i += 5){
         * if (!Character.isDigit(s[i].charAt(0))) {
         * s[i] += "Buzz";
         * } else {
         * s[i] = "Buzz";
         * }
         * }
         */

        String[] returnList = Arrays.copyOfRange(s, 1, n + 1);
        return Arrays.asList(returnList);
    }
}