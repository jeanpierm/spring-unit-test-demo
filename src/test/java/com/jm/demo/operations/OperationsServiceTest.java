package com.jm.demo.operations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OperationsServiceTest {
    private OperationsService operationsService;

    @BeforeEach
    public void setup() {
        operationsService = new OperationsServiceImpl();
    }

    @Test
    public void factorialTest() {
        Assertions.assertEquals(24, operationsService.factorial(4));
        Assertions.assertEquals(1, operationsService.factorial(1));
        Assertions.assertEquals(1, operationsService.factorial(1));
    }

    @Test
    public void factorialFailTest() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            operationsService.factorial(-1);
        });
        Assertions.assertThrows(ArithmeticException.class, () -> {
            operationsService.factorial(-10);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            operationsService.factorial(24);
        });
    }
}
