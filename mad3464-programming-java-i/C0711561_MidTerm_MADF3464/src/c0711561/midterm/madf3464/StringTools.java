/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.midterm.madf3464;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author macstudent
 */
public class StringTools {
    
    public static String reverse(String s) {
        if (s == null) return null;
        
        String reversed = "";
        char[] c = s.toCharArray();
         
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed += c[i];
        }
        
        return reversed;
    } 
    
    public static int binaryToDecimal(String s) {
        char[] binarayArray = s.toCharArray();
        int decimal = 0;
        
        for (int i = binarayArray.length - 1; i >= 0 ; i--) {
            if (binarayArray[i] != '0' && binarayArray[i] != '1') {
                return -1;
            } else {
                if (binarayArray[i] == '1') {
                    int expoent = binarayArray.length - 1 - i;
                    decimal += (int) Math.pow(2, expoent);
                }
            }
        }
        
        return decimal;
    }
    
    public static String initials(String s) {
        String[] names = s.split(" ");
        String lastNameWithInitials = "";
        
        if(names.length != 3) {
            lastNameWithInitials = null;
        } else {
            lastNameWithInitials = new Character(names[0].charAt(0)).toString().toUpperCase() + ". ";
            lastNameWithInitials += new Character(names[1].charAt(0)).toString().toUpperCase() + ". ";
            lastNameWithInitials += new Character(names[2].charAt(0)).toString().toUpperCase();
            lastNameWithInitials += names[2].substring(1, names[2].length()).toLowerCase();
        }

        return lastNameWithInitials;
    }
    
    public static char mostFrequent(String s) {
        Map<Character, Integer> frequency = new LinkedHashMap<>();
        
        if (s == null) {
            return ' ';
        }
        
        char[] c = s.toCharArray();
        for(int i = 0; i < c.length; i++) {
            if (frequency.containsKey(c[i])) {
                Integer cont = frequency.get(c[i]) + 1;
                frequency.put(c[i], cont);
            } else {
                frequency.put(c[i], 1);
            }
        }
        
        char mostFrequentChar = 0;
        int greater = 0;
        for (Character key : frequency.keySet()) {
            if (frequency.get(key) >= greater) {
                greater = frequency.get(key);
                mostFrequentChar = key;
            }
        }
        
        System.out.println("Input: " + s + " - "+ mostFrequentChar + " - " + frequency.get(mostFrequentChar));
        return mostFrequentChar;
    }
    
    public static String replaceSubString(String s1, String s2, String s3) {
        
        if (s1 == null || s2 == null || s3 == null) return null;
        
        String text = s1.toLowerCase();
        String pattern = s2.toLowerCase();
        String replace = s3;
        
        String[] words = text.split(" ");
        String[] newPhrase = Arrays.copyOf(words, words.length);
        
        for(int i = 0; i < words.length; i++) {
            if (words[i].equals(pattern)) {
                newPhrase[i] = replace;
            }
        }
        
        String newString = "";
        for(int i = 0; i < newPhrase.length; i++) {
            newString += newPhrase[i] + " ";
        }
        
        //return text.replaceAll(pattern, replace);
        return newString;
    }
}
