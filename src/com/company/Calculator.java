package com.company;
import java.lang.Math;

public class Calculator {
    private static final String numerals = "1234567890";
    private static final String symbols = "+-*/^";
    private static final String letters = "qwertyuiopasdfghjklzxcvbnm";

    public static double analyseStr(String data) throws Exception{
        int state = 0;
        int fId = 0;
        char symb = '+';
        StringBuilder funcName = new StringBuilder();
        double curValue = 0;
        double oldValue = 0;
        int bracketsCount = 0;
        boolean hasPoint = false;
        int isNegative = 1;
        //System.out.println(data);
        for (int i = 0; i < data.length(); i++){
            //
            if(numerals.indexOf(data.charAt(i)) != -1){
                if(state == 0){
                    state = 1;
                    fId = i;
                } else{
                    if(state != 1 && state != 3){
                        throw new Exception("Invalid numeral at position " + String.valueOf(i+1));
                    }
                }
            }
            if(symbols.indexOf(data.charAt(i)) != -1){
                if(state == 2){
                    try{
                        oldValue = Operand.impOp(oldValue, curValue, symb);
                    } catch (OpException e){
                        throw new Exception(e.getMessage());
                    }
                    symb = data.charAt(i);
                    state = 0;
                } else{
                    if(state == 1){
                        if(!hasPoint){
                            curValue = Integer.parseInt(data.substring(fId, i)) * isNegative;
                            isNegative = 1;
                        } else{
                            hasPoint = false;
                            curValue = Double.parseDouble(data.substring(fId, i)) * isNegative;
                            isNegative = 1;
                        }

                        try{
                            oldValue = Operand.impOp(oldValue, curValue, symb);
                        } catch (OpException e){
                            throw new Exception(e.getMessage());
                        }
                        symb = data.charAt(i);
                        state = 0;
                    } else{
                        if(state == 4){
                            curValue = Constants.getConstant(funcName.toString()) * isNegative;
                            funcName = new StringBuilder();
                            isNegative = 1;
                            try{
                                oldValue = Operand.impOp(oldValue, curValue, symb);
                            } catch (OpException e){
                                throw new Exception(e.getMessage());
                            }
                            symb = data.charAt(i);
                            state = 0;
                        }else{
                            if(state != 3) {
                                if(data.charAt(i) != '-') {
                                    throw new Exception("Invalid operator at position " + String.valueOf(i + 1));
                                }
                            }
                        }
                    }
                }

            }

            if(data.charAt(i) == '('){
                if(state == 0 || state == 4){
                    fId = i;
                    state = 3;
                    bracketsCount = 1;
                } else{
                    if(state != 3) {
                        throw new Exception("Invalid bracket at position " + String.valueOf(i + 1));
                    } else{
                        bracketsCount += 1;
                    }
                }
            }
            if(data.charAt(i) == ')'){
                if (state == 3) {
                    bracketsCount -= 1;
                    if(bracketsCount == 0){
                        curValue = Functions.func(Calculator.analyseStr(data.substring(fId + 1, i)), funcName.toString());
                        funcName = new StringBuilder();
                        state = 2;
                    }
                } else{
                    throw new Exception("Invalid bracket at position " + String.valueOf(i+1));
                }
            }
            if(state == 0){
                if(data.charAt(i) == '-'){
                    isNegative = -1;
                }
            }

            if(state == 1){
                if(data.charAt(i) == '.'){
                    if(!hasPoint){
                        hasPoint = true;
                    } else{
                        throw new Exception("Second point in number at position " + String.valueOf(i+1));
                    }
                }
            }

            if(letters.indexOf(data.charAt(i)) != -1) {
                if(state == 0) {
                    //System.out.println("Somedich");
                    funcName = new StringBuilder().append(data.charAt(i));
                    state = 4;
                } else{
                    if(state == 4){
                        //System.out.println("anotherDich");
                        funcName.append(data.charAt(i));
                    }
                }
            }


        }
        if(state == 1){
            if(!hasPoint){
                curValue = Integer.parseInt(data.substring(fId)) * isNegative;
                isNegative = 1;
            } else{
                hasPoint = false;
                curValue = Double.parseDouble(data.substring(fId)) * isNegative;
                isNegative = 1;
            }
            try{
                oldValue = Operand.impOp(oldValue, curValue, symb);

            } catch (OpException e){
                throw new Exception(e.getMessage());
            }
        }
        if(state == 2) {
            try {
                oldValue = Operand.impOp(oldValue, curValue, symb);
            } catch (OpException e) {
                throw new Exception(e.getMessage());
            }
        }
        if(state == 3) {
            throw new Exception("No exit bracket at the end!");
        }

        if(state == 4){
            curValue = Constants.getConstant(funcName.toString()) * isNegative;
            funcName = new StringBuilder();
            isNegative = 1;
            try{
                oldValue = Operand.impOp(oldValue, curValue, symb);
            } catch (OpException e){
                throw new Exception(e.getMessage());
            }
        }
        return(oldValue);
    }
}
