/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c0711561.midterm.madf3464;

/**
 *
 * @author macstudent
 */
public class C0711561_MidTerm_MADF3464 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Solutions for the first problem:");
        System.out.println("-------------------------------------------------------------------------------------");
        
        executeSoluctionForFirstProblem();
        
        System.out.println("\n-------------------------------------------------------------------------------------");
        System.out.println("Solutions for the second problem:");
        System.out.println("-------------------------------------------------------------------------------------");
        
        executeSoluctionForSecondProblem(); 
    }

    private static void printCardMatrix(String[][] cardMatrix) {
        for (int row = 0; row < cardMatrix.length; row++) {
            for (int col = 0; col < cardMatrix[row].length; col++) {
                System.out.print(cardMatrix[row][col] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    private static void executeSoluctionForFirstProblem() {
        String [][]cardListEx1 = { {"1","A","5"},
                                   {"2","7","3"},
                                   {"3","6","K"} };
        String firstShuffleColPosEx1 = "C3";
        String secShuffleColPosEx1 = "C3";
        
        runGame(cardListEx1, firstShuffleColPosEx1, secShuffleColPosEx1);
       
        System.out.println();
        
        String firstShuffleColPosEx2 = "C2";
        String secShuffleColPosEx2 = "C3";
        String[][] cardListEx2 = {{"A", "4", "3"},
                               {"K", "7", "2"},
                               {"5", "9", "8"}};

        runGame(cardListEx2, firstShuffleColPosEx2, secShuffleColPosEx2);   
    }
    
    private static void runGame(String[][] cardList, String firstShuffleColPos, String secShuffleColPos) {
        System.out.println("Given the following card list:");
        printCardMatrix(cardList);
        
        System.out.println("And the first column position as: " + firstShuffleColPos);
        System.out.println("And the second column position as: " + secShuffleColPos);

        MagicalCardGameModel game = new MagicalCardGameModel();
        game.cardList = cardList;

        game.firstShuffleColPos = firstShuffleColPos;
        String[][] firstShuffleResult = game.getFirstShuffleResult(game);
        
        System.out.println("\nAfter First Shuffle:");
        printCardMatrix(firstShuffleResult);

        game.secShuffleColPos = secShuffleColPos;
        String[][] secShuffleResult = game.getSecShuffleResult(game);
        System.out.println("After Second Shuffle:");
        printCardMatrix(secShuffleResult);
        
        System.out.println("getFinalResult function returns: " + game.getFinalResult(game));
    }
    
    private static void executeSoluctionForSecondProblem() {
        
        System.out.println("\nReversed String");
        System.out.println(StringTools.reverse("Lambton"));
        System.out.println(StringTools.reverse("Denis willian"));
        System.out.println(StringTools.reverse(""));
        System.out.println(StringTools.reverse(null));
        
        System.out.println("\nBinary to Decimal");
        System.out.println(StringTools.binaryToDecimal("1000"));
        System.out.println(StringTools.binaryToDecimal("10001"));
        System.out.println(StringTools.binaryToDecimal("111111"));
        System.out.println(StringTools.binaryToDecimal("111112"));
        System.out.println(StringTools.binaryToDecimal("100001.1"));
        
        System.out.println("\nInitials");
        System.out.println(StringTools.initials("James tiBeriUs kiRK"));
        System.out.println(StringTools.initials("jean luc picard"));
        System.out.println(StringTools.initials("deniS WilliaN Gois"));
        System.out.println(StringTools.initials("AaroN LANGille"));
        
        System.out.println("\nMost Frequent");
        StringTools.mostFrequent("Deniiiiisss");
        StringTools.mostFrequent("Tekrsasdfffddd");
        System.out.println(StringTools.mostFrequent(null));
        
        System.out.println("Replace Sub String");
        System.out.println(StringTools.replaceSubString("the dog jumped over the fence", "the", "that"));
        System.out.println(StringTools.replaceSubString(null, "the", "that"));
        System.out.println(StringTools.replaceSubString("the dog jumped over the fence", "the", null));
        System.out.println(StringTools.replaceSubString("the dog jumped over the fence", null, "that"));
    }
}
