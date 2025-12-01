package model;

import java.util.Stack;

public class ExpressionEvaluator {

    public static double evaluate(String expr) {
        if (expr == null) {
            throw new IllegalArgumentException("Expression is null");
        }

        expr = expr.replace(" ", "");

        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expr.length() &&
                        (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i));
                    i++;
                }
                double val = Double.parseDouble(sb.toString());
                values.push(val);
                continue;
            }

            if (c == '(') {
                ops.push(c);
                i++;
                continue;
            }

            if (c == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    applyTopOperator(values, ops);
                }
                if (ops.isEmpty() || ops.peek() != '(') {
                    throw new IllegalArgumentException("Unbalanced parentheses");
                }
                ops.pop(); // sacar '('
                i++;
                continue;
            }

            // Operador
            if (isOperator(c)) {
                while (!ops.isEmpty() && hasPrecedence(ops.peek(), c)) {
                    applyTopOperator(values, ops);
                }
                ops.push(c);
                i++;
                continue;
            }

            throw new IllegalArgumentException("Invalid character in expression: " + c);
        }

        while (!ops.isEmpty()) {
            if (ops.peek() == '(' || ops.peek() == ')') {
                throw new IllegalArgumentException("Unbalanced parentheses");
            }
            applyTopOperator(values, ops);
        }

        if (values.size() != 1) {
            throw new IllegalStateException("Invalid expression");
        }

        return values.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op1 == '(' || op1 == ')') return false;

        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return true;

        if ((op1 == '+' || op1 == '-') && (op2 == '+' || op2 == '-')) return true;
        if ((op1 == '*' || op1 == '/') && (op2 == '*' || op2 == '/')) return true;
        return false;
    }

    private static void applyTopOperator(Stack<Double> values, Stack<Character> ops) {
        if (values.size() < 2) {
            throw new IllegalStateException("Not enough values for operation");
        }
        double b = values.pop();
        double a = values.pop();
        char op = ops.pop();

        double result;
        switch (op) {
            case '+' -> result = a + b;
            case '-' -> result = a - b;
            case '*' -> result = a * b;
            case '/' -> {
                if (b == 0.0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = a / b;
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + op);
        }

        values.push(result);
    }
}
