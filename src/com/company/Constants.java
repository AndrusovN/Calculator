package com.company;
import java.lang.Math;
public class Constants {
    public static double getConstant(String name) throws Exception{
        switch (name){
            case "e":
                return(2.718281828459045);
            case "pi":
                return(3.14159265358979322846264);
            case "phi":
                return((Math.sqrt(5)+1)/2.0);
            default:
                throw new Exception("Unknown constant " + name);
        }
    }

}
