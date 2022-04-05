/**
 * CMSC 350 Project 1
 * Conversions from prefix to postfix, and postfix to prefix
 * 
 * @author Christopher Stoner
 */
package project1;

import java.util.*;

public class FixConversions {

    /**
     * Expecting tokenized String with only one character
     * Compares the tokenized String as a char between '0' and '9'
     * confiming if the char is a digit.
     * 
     * @param operand the potential operand we wish to check
     * @return Bool, if operand true, if NOT operand false
     */
    private static Boolean isOperand(String operand) {
        char c = operand.charAt(0);
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    /**
     * Expecting tokenized String with only one character
     * Compares the tokenized String as a char against an array of acceptable
     * operators.
     * 
     * @param operator the potential operator we wish to check
     * @return Bool, if contains acceptableOperators true, if not, false
     */
    private static Boolean isOperator(String operator) {
        char[] acceptableOperators = { '+', '-', '*', '/', '%', '^' };
        char op = operator.charAt(0);
        for (char c : acceptableOperators) {
            if (c == op) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts a prefix expression into a postfix expression with the use of two
     * stacks: reversal Stack and operand Stack
     * 
     * @param expression prefix String expression
     * @return the top of the operand stack which should be the completed postfix
     *         String expression
     * @throws EmptyStackException If an attempt is made to pop one of the stacks
     *                             without having a value on top.
     */
    public static String convertPrefixPostfix(String expression) throws EmptyStackException {
        Stack<String> reversalStack = new Stack<>();
        Stack<String> operandStack = new Stack<>();

        String token;
        StringTokenizer tokenizer = new StringTokenizer(expression, " ");
        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            if (!Character.isSpaceChar(token.charAt(0))) { // Check that the token is NOT a space
                reversalStack.push(token); // If the token is not a space, then push token on to the reversalStack
            }
        }

        while (!reversalStack.isEmpty()) { // While the reversal Stack isn't empty, keep popping values from it and
            token = reversalStack.pop(); // place them in token.
            if (isOperand(token)) { // Check for operand, if so, push to operandStack, then repeat loop
                operandStack.push(token);
            } else if (isOperator(token)) { // Check for operator, if so, attempt to create a String value from two top
                                            // values off of operandStack
                try {
                    String op2 = operandStack.pop();
                    String op1 = operandStack.pop();

                    // Create string to push on to operandStack
                    String string = op2 + " " + op1 + " " + token;
                    operandStack.push(string);

                } catch (EmptyStackException e) { // If pop is attempted from empty stack
                    throw e;
                }
            }
        }

        try {
            return operandStack.pop();
        } catch (EmptyStackException e) { // If pop is attempted on empty stack
            throw e;
        }

    }

    /**
     * Converts a postfix expression into a prefix expression with the use of one
     * stack operand Stack
     * 
     * @param expression postfix String expression
     * @return the top of the operand stack which should be the completed prefix
     *         String expression
     * @throws EmptyStackException If an attempt is made to pop one of the stacks
     *                             without having a value on top.
     */
    public static String convertPostfixPrefix(String expression) throws EmptyStackException {
        Stack<String> operandStack = new Stack<String>();

        String token;
        StringTokenizer tokenizer = new StringTokenizer(expression, " ");
        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            if (Character.isSpaceChar(token.charAt(0))) { // While the tokenizer has more tokens, check if the token is
                                                          // a space, if so, continue the loop until an operand or
                                                          // operator is found
                continue;
            } else if (isOperand(token)) { // If operand, push to stack
                operandStack.push(token);
            } else if (isOperator(token)) { // if operator, attempt String creation from top two stack values
                try {
                    String op2 = operandStack.pop();
                    String op1 = operandStack.pop();

                    String string = token + " " + op1 + " " + op2;
                    operandStack.push(string);

                } catch (EmptyStackException e) { // If pop is attempted from empty stack
                    throw e;
                }
            }
        }

        try {
            return operandStack.pop();
        } catch (EmptyStackException e) { // If pop is attempted from empty stack
            throw e;
        }
    }

}
