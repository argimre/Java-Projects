import java.util.Scanner;

public final class RecursiveCalculator {
    public static int evaluateExpression(StringBuilder source) {
        int value = evaluateTerm(source);
        while (source.length() > 0) {
            char op = source.charAt(0);
            if (op == '+' || op == '-') {
                source.deleteCharAt(0);
                int nextTerm = evaluateTerm(source);
                if (op == '+') {
                    value += nextTerm;
                } else {
                    value -= nextTerm;
                }
            } else {
                break;
            }
        }
        return value;
    }

    private static int evaluateTerm(StringBuilder source) {
        int value = evaluateFactor(source);
        while (source.length() > 0) {
            char op = source.charAt(0);
            if (op == '*' || op == '/') {
                source.deleteCharAt(0);
                int nextFactor = evaluateFactor(source);
                if (op == '*') {
                    value *= nextFactor;
                } else {
                    if (nextFactor == 0) {
                        throw new ArithmeticException("Division by zero is not allowed");
                    }
                    value /= nextFactor;
                }
            } else {
                break;
            }
        }
        return value;
    }

    private static int evaluateFactor(StringBuilder source) {
        int value;
        if (source.charAt(0) == '(') {
            source.deleteCharAt(0);
            value = evaluateExpression(source);
            if (source.length() > 0 && source.charAt(0) == ')') {
                source.deleteCharAt(0);
            } else {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
        } else if (Character.isDigit(source.charAt(0))) {
            value = evaluateNumber(source);
        } else {
            throw new IllegalArgumentException("Invalid character encountered");
        }
        return value;
    }

    private static int evaluateNumber(StringBuilder source) {
        int value = 0;
        while (source.length() > 0 && Character.isDigit(source.charAt(0))) {
            value = value * 10 + Character.digit(source.charAt(0), 10);
            source.deleteCharAt(0);
        }
        return value;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an expression: ");
        String expression = input.nextLine();
        expression = expression.replaceAll("\\s+", "");

        StringBuilder source = new StringBuilder(expression);

        try {
            int result = evaluateExpression(source);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error in expression: " + e.getMessage());
        }
        input.close();
    }
}