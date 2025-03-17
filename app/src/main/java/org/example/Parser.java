package org.example;

public class Parser {
    private final String input;
    private int pos = 0;
    private char currentChar;

    public Parser(String input) {
        this.input = preprocess(input);
        this.currentChar = this.input.charAt(0);
    }

    private String preprocess(String input) {
        StringBuilder sb = new StringBuilder(input.length());
        for (char c : input.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private void nextChar() {
        pos++;
        currentChar = pos < input.length() ? input.charAt(pos) : '\0';
    }


    public Ast parse() {
        return parseExpression();
    }


    private Ast parseExpression() {
        Ast left = parseTerm();
        while (currentChar == '+' || currentChar == '-') {
            char op = currentChar;
            nextChar();
            Ast right = parseTerm();
            left = new Ast.BinaryOp(op, left, right);
        }
        return left;
    }

    private Ast parseTerm() {
        Ast left = parseFactor();
        while (currentChar == '*' || currentChar == '/') {
            char op = currentChar;
            nextChar();
            Ast right = parseFactor();
            left = new Ast.BinaryOp(op, left, right);
        }
        return left;
    }

    private Ast parseFactor() {
        if (currentChar == '(') {
            nextChar();
            Ast node = parseExpression();
            if (currentChar == ')') {
                nextChar();
                return node;
            } else {
                throw new RuntimeException("Missing closing parenthesis");
            }
        } else if (Character.isDigit(currentChar)) {
            return parseNumber();
        } else {
            throw new RuntimeException("Unexpected character: " + currentChar);
        }
    }


    private Ast parseNumber() {
        int start = pos;
        while (Character.isDigit(currentChar)) {
            nextChar();
        }
        return new Ast.Number(Integer.parseInt(input.substring(start, pos)));
    }
}
