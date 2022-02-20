package com.jm.demo.operations;

import org.springframework.stereotype.Service;

@Service
public class OperationsServiceImpl implements OperationsService {
    @Override
    public int factorial(int n) {
        // n -> n * (n-1) * (n-2) ... * 1
        // n >= 0
        if (n < 0) throw new ArithmeticException("Can't get a factorial from negative");
        if (n > 23) throw new IllegalArgumentException();
        if (n == 1 || n == 0) return 1;
        return n * (factorial(n - 1));
    }
}
