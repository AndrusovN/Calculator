package com.company;
import java.lang.Math;
public class Operand {
    public static double impOp(double a, double b, char Op) throws OpException{
        switch (Op){
            case '^':
                return(Math.pow(a, b));
            case '*':
                return(a * b);
            case '/':
                return(a / b);
            case '+':
                return(a + b);
            case '-':
                return(a - b);
            default:
                throw new OpException("Invalid operator!");
        }
    }
}
