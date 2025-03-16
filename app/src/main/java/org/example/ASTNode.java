package org.example;

abstract class ASTNode {
    abstract int evaluate();
}

class NumberNode extends ASTNode {
    private final int value;

    NumberNode(int value) {
        this.value = value;
    }

    @Override
    int evaluate() {
        return value;
    }
}


class BinaryOpNode extends ASTNode {
    final char op;
    final ASTNode left, right;

    BinaryOpNode(char op, ASTNode left, ASTNode right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    int evaluate() {
        int leftVal = left.evaluate();
        int rightVal = right.evaluate();

        return switch (op) {
            case '+' -> leftVal + rightVal;
            case '-' -> leftVal - rightVal;
            case '*' -> leftVal * rightVal;
            case '/' -> leftVal / rightVal;
            default -> throw new IllegalArgumentException("Invalid operator: " + op);
        };
    }
}