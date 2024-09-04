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
    public static int Fibonacci(int n) {

        final int MOD = 1000000007;
        if (n == 1 || n == 2) return 1;
        int a = 1, b = 1, c = 0;
        for (int i = 3; i <= n; i++) {
            c = (a + b) % MOD;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 矩阵快速幂解斐波那契数列
     * @param n 正整数
     * @return 斐波那契数列的第n项
     */
//    public static int FibonacciPow(int n) {
//
//        final int MOD = 1000000007;
//
//
//    }

}
