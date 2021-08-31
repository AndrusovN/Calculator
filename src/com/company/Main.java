package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(true){
            String input = scan.nextLine();
            if(input.equals("exit")){
                break;
            }
            try{
                System.out.println(Calculator.analyseStr(input));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }
}
