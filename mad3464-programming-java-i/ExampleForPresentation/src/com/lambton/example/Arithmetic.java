package com.lambton.example;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author macstudent
 */
public class Arithmetic {
    
    public static void main(String[] args) {
        
        if (args.length != 3) {
            System.out.println("Insufience arguments. \n You have to specify 3 arguments: <First Operand> <Operator> <Second Operand>\n Ex: 20 + 20 or 20 / 20");
            return;
        }
        
        int firstOperand = Integer.parseInt(args[0]);
        int secondOperand = Integer.parseInt(args[2]);
        char operator = args[1].charAt(0);
        
        switch(operator) {
            case '+': 
                System.out.println(add(firstOperand, secondOperand));
                break;
            case '-': 
                System.out.println(subtract(firstOperand, secondOperand));
                break;
            case '*': 
                System.out.println(multply(firstOperand, secondOperand));
                break;
            case '/': 
                System.out.println(divide(firstOperand, secondOperand));
                break;
            case '%': 
                System.out.println(module(firstOperand, secondOperand));    
                break;
            default:
                System.out.println("The operation it is not valid.\n It must be: + - / % *");
        }
    }
    
    static int add(int a, int b) {
        return a + b;
    }
    
    static int subtract(int a, int b) {
        return a - b;
    }
    
    static int multply(int a, int b) {
        return a * b;
    }
    
    static int module(int a, int b) {
        return a % b;
    }
    
    static int divide(int a, int b) {
        return a / b;
    } 
}
