/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day4;

/**
 *
 * @author macstudent
 */
public class TypeConversion {
    
    public static void main(String[] args) {
        int a = 10;
        int b = a;
        long c = 100;
        a = (int) c;
        int d = a * b;
        System.out.println(d);
        
        short x = 2;
        short y = 2;
        // error because it converts to int short z = x + y;
        int z = x + y;
        System.out.println(z);
        
        
        
    }
    
}
