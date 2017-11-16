/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.f2017.mad3464.finalproject;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 *
 * @author macstudent
 */
public class Util {
    
    private static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();
    
    public static String toCurrencyFormatFrom(double value) {   
        return FORMATTER.format(value);
    }
    
    public static String toCurrencyFormatFrom(BigDecimal value) {
        return FORMATTER.format(value);
    }
}
