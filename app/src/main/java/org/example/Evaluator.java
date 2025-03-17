package org.example;

public class Evaluator implements Ast.Visitor<Integer> {
    @Override
    public Integer visitNumber(Ast.Number node) {
        return node.value;
    }

    @Override
    public Integer visitBinaryOp(Ast.BinaryOp node) {
        int leftVal = node.left.accept(this);
        int rightVal = node.right.accept(this);

        return switch (node.op) {
            case '+' -> leftVal + rightVal;
            case '-' -> leftVal - rightVal;
            case '*' -> leftVal * rightVal;
            case '/' -> leftVal / rightVal;
            default -> throw new IllegalArgumentException("Invalid operator: " + node.op);
        };
    }

}
