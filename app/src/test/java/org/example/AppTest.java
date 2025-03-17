package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    // ✅ 単一の数値
    @Test
    void SingleNumber() {
        assertEquals(1, App.expr("1"));
        assertEquals(42, App.expr("42"));
        assertEquals(999, App.expr("999"));
    }

    // ✅ 足し算
    @Test
    void Add() {
        assertEquals(3, App.expr("1+2"));
        assertEquals(10, App.expr("4+6"));
        assertEquals(100, App.expr("50+50"));
    }

    // ✅ 引き算
    @Test
    void Sub() {
        assertEquals(1, App.expr("2-1"));
        assertEquals(5, App.expr("10-5"));
        assertEquals(0, App.expr("7-7"));
    }

    // ✅ 掛け算
    @Test
    void Mul() {
        assertEquals(6, App.expr("2*3"));
        assertEquals(25, App.expr("5*5"));
        assertEquals(100, App.expr("10*10"));
    }

    // ✅ 割り算
    @Test
    void Div() {
        assertEquals(2, App.expr("6/3"));
        assertEquals(4, App.expr("8/2"));
        assertEquals(10, App.expr("100/10"));
    }

    // ✅ 演算子の優先順位
    @Test
    void OperatorPrecedence() {
        assertEquals(14, App.expr("2+3*4")); // 2 + (3*4) = 14
        assertEquals(6, App.expr("2*3+0")); // (2*3) + 0 = 6
        assertEquals(1, App.expr("2-3+2")); // (2-3) + 2 = 1
    }

    // ✅ 括弧を使った計算
    @Test
    void Parentheses() {
        assertEquals(20, App.expr("(2+3)*4")); // (2+3) * 4 = 20
        assertEquals(5, App.expr("(10-5)")); // (10-5) = 5
        assertEquals(8, App.expr("(2+2)+(3+1)")); // (2+2) + (3+1) = 8
    }

    // ✅ 空白を含むケース
    @Test
    void IgnoreWhitespace() {
        assertEquals(14, App.expr(" 2 + 3 * 4 ")); // 空白を無視
        assertEquals(20, App.expr(" ( 2 + 3 ) * 4")); // 空白を無視
    }

    // ✅ 異常ケース（不正な入力）
    @Test
    void InvalidExpressions() {
        assertThrows(RuntimeException.class, () -> App.expr("2++3"));
        assertThrows(RuntimeException.class, () -> App.expr("10*/5"));
        assertThrows(RuntimeException.class, () -> App.expr(")2+3("));
        assertThrows(RuntimeException.class, () -> App.expr("5+"));
    }

    // ✅ ゼロ除算
    @Test
    void DivisionByZero() {
        assertThrows(ArithmeticException.class, () -> App.expr("10/0"));
    }
}
