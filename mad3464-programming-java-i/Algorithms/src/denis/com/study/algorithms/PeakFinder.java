/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denis.com.study.algorithms;

/**
 *
 * @author macstudent
 */
public class PeakFinder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] possiblePeaks = {6, 7, 4, 3, 2, 1, 4, 5};
        
        String foundPeaks = "";
        
        for (int i = 0; i < possiblePeaks.length; i++) {
            if (i == 0 && possiblePeaks[i] > possiblePeaks[i + 1]) {
                foundPeaks += " " + possiblePeaks[i];
            } else if (i == possiblePeaks.length - 1 && possiblePeaks[i] > possiblePeaks[i - 1]) {
                
            }
            
            
            
        }
    }
    
}
