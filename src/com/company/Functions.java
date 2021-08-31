package com.company;
import java.lang.Math;

public class Functions {
    public static final double e = 2.718281828459045;


    public static double func(double a, String name) throws Exception{
        switch (name){
            case "ln":
                return(Math.log(a));
            case "exp":
                return(Math.pow(e, a));
            case "sqrt":
                return(Math.pow(a, 0.5));
            case "qbrt":
                return(Math.pow(a, 1.0/3.0));
            case "sin":
                return(Math.sin(a));
            case "cos":
                return(Math.cos(a));
            case "tg":
                return(Math.tan(a));
            case "ctg":
                return(1 / Math.tan(a));
            case "arcsin":
                return(Math.asin(a));
            case "arccos":
                return (Math.acos(a));
            case "arctg":
                return (Math.atan(a));
            case "abs":
                return(Math.abs(a));
            case "round":
                return(Math.round(a));
            case "":
                return(a);
            default:
                throw new Exception("No function with name " + name);
        }
    }
}
