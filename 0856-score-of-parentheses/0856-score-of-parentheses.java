import java.util.Stack;

class Solution {
    public int scoreOfParentheses(String s) {

        Stack<Integer> stack = new Stack<>();

        
        stack.push(0);

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

               
                stack.push(0);

            } else {

                
                int inner = stack.pop();

                
                int score = Math.max(1, 2 * inner);

                
                int outer = stack.pop();
                stack.push(outer + score);
            }
        }

        return stack.peek();
    }
}