/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (var stdin = new Scanner(System.in)) {
            var input = readAll(stdin);
            System.out.println(expr(input));
        }
    };

    private static StringBuffer readAll(Scanner scanner){
        var sb = new StringBuffer();
        while (scanner.hasNext()) {
            sb.append(scanner.next());
        }
        return sb;
    }

    private static int _expr(String input) {
        var parser = new Parser(input);
        ASTNode ast = parser.parse();
        return ast.evaluate();
    }

    public static int expr(StringBuffer input) {
        return _expr(input.toString());
    }

    public static int expr(String input) {
        return _expr(input);
    }
}