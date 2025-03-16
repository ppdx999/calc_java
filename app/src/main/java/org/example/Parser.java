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


    public ASTNode parse() {
        return parseExpression();
    }


    private ASTNode parseExpression() {
        ASTNode left = parseTerm();
        while (currentChar == '+' || currentChar == '-') {
            char op = currentChar;
            nextChar();
            ASTNode right = parseTerm();
            left = new BinaryOpNode(op, left, right);
        }
        return left;
    }

    private ASTNode parseTerm() {
        ASTNode left = parseFactor();
        while (currentChar == '*' || currentChar == '/') {
            char op = currentChar;
            nextChar();
            ASTNode right = parseFactor();
            left = new BinaryOpNode(op, left, right);
        }
        return left;
    }

    private ASTNode parseFactor() {
        if (currentChar == '(') {
            nextChar();
            ASTNode node = parseExpression();
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


    private ASTNode parseNumber() {
        int start = pos;
        while (Character.isDigit(currentChar)) {
            nextChar();
        }
        return new NumberNode(Integer.parseInt(input.substring(start, pos)));
    }
}
