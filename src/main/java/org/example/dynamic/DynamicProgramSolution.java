package org.example.dynamic;

public class DynamicProgramSolution {

    /**
     * f(x) = 1, x = 1 | x = 2
     * f(x) = f(x-1) + f(x-2), x > 2
     * 1 1 2 3 5 8 13 21 34 55
     * 输入一个正整数n,输出斐波那契数列的第n项
     * @param n 正整数
     * @return 斐波那契数列的第n项
     */
    public static int Fibonacci(int n){

        if (n == 1 || n == 2) return 1;
        return 0;
    }

}
