package org.example;

public class Evaluator {
    private final String expression;

    public Evaluator(String expression) {
        this.expression = expression;
    }

    public int evaluate() {
        return Integer.parseInt(expression);
    }
}
