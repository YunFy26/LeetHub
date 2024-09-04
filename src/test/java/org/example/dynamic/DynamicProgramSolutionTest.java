package org.example.dynamic;

import org.junit.Test;

public class DynamicProgramSolutionTest {

    @Test
    public void testFibonacci(){
        int fibonacci = DynamicProgramSolution.Fibonacci(48);
        System.out.println(fibonacci);
        assert fibonacci == 807526948;
    }
}
