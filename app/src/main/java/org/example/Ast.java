package org.example;

abstract class Ast {
    interface Visitor<R> {
        R visitNumber(Number node);

        R visitBinaryOp(BinaryOp node);
    }

    static class Number extends Ast {
        final int value;

        Number(int value) {
            this.value = value;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitNumber(this);
        }
    }

    static class BinaryOp extends Ast {
        final char op;
        final Ast left, right;

        BinaryOp(char op, Ast left, Ast right) {
            this.op = op;
            this.left = left;
            this.right = right;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBinaryOp(this);
        }
    }

    abstract <R> R accept(Visitor<R> visitor);
}
