package org.example.dynamic;

import org.junit.Test;

public class DynamicProgramSolutionTest {

    @Test
    public void testFibonacci(){
        int fibonacci = DynamicProgramSolution.Fibonacci(9);
        assert fibonacci == 34;
    }
}
