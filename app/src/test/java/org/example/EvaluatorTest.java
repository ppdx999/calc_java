package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EvaluatorTest {
    @Test
    void testSimpleAddition() {
        var evaluator = new Evaluator("1");
        assertEquals(1, evaluator.evaluate());
    }
}
