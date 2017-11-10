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
public class WrapperClass {
    
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "Hello".trim();
        System.out.println(s1 == s2);
    
        // Wrapper question
        Integer a = 1;
        int c = a;
        Integer d = new Integer(20);
        // Correct way to parse values
        int b = Integer.parseInt("32");
        String s = String.valueOf(12);
    }
    
}
