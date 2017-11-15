/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denis.com.study.algorithms;

import java.util.Arrays;

/**
 *
 * @author macstudent
 */
public class PeakFinder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] possiblePeaks = {6, 7, 4, 3, 2, 1, 4, 8};
        
        //straightforwardAlgorithm(possiblePeaks);
        dividAndConquerAlgorithm(possiblePeaks);
    }

    private static void straightforwardAlgorithm(int[] a) {
        int foundPeaks = 0;
        
        for (int i = 0; i < a.length; i++) {
            if (i == 0 && a[i] >= a[i + 1]) {
                foundPeaks = a[i] > foundPeaks ? a[i] : foundPeaks;
            } else if (i == a.length - 1 && a[i] > a[i - 1]) {
                foundPeaks = a[i] > foundPeaks ? a[i] : foundPeaks;
            } else if (a[i] >= a[i + 1] && a[i] >= a[i - 1]){
                foundPeaks = a[i] > foundPeaks ? a[i] : foundPeaks;
            }
        }
        
        System.out.println(foundPeaks);
    }
    
    private static void dividAndConquerAlgorithm(int[] a) {
        int length = a.length;
        String foundPeaks = "";
        
        if (a[length/2] < a[length/2 - 1]) {
            dividAndConquerAlgorithm(Arrays.copyOfRange(a, 0, length/2 - 1));
        } else if (a[length/2] < a[length/2 + 1]) {
            dividAndConquerAlgorithm(Arrays.copyOfRange(a, length/2 + 1, length - 1));
        } else {
            foundPeaks += " " + a[length/2];
        }
        
        System.out.println(foundPeaks);
    }

}
