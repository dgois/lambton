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
public class MagicalCardGameModel implements IMagicalCardGame {

    String firstShuffleColPos;
    String secShuffleColPos;
    String[][] cardList = null;
    String[][] firstShuffle = null;
    String[][] secShuffle = null;
    String guessedCard;
    
    @Override
    public String[][] getFirstShuffleResult(MagicalCardGameModel magicalCardGameModel) {
        
        if(!isValidCardList(magicalCardGameModel.cardList)) {
            System.out.println("You must inform a card list of 3 x 3");
            return null;
        }
        
        String[][] afterFirstShuffle = shuffle(magicalCardGameModel.firstShuffleColPos, magicalCardGameModel.cardList);
        
        this.firstShuffle = afterFirstShuffle;
        
        return this.firstShuffle;
    }
    
    private boolean isValidCardList(String[][] cardListToValidate) {
        boolean valid = true;
        if (cardListToValidate == null) {
            valid = false;
        } else if (cardListToValidate.length != 3) {
            valid = false;
        } else if (cardListToValidate[0].length != 3 && cardListToValidate[1].length != 3 && cardListToValidate[2].length != 3) {
            valid = false;
        }
        
        return valid;
    }

    @Override
    public String[][] getSecShuffleResult(MagicalCardGameModel magicalCardGameModel) {
        if(!isValidCardList(magicalCardGameModel.cardList)) {
            System.out.println("You must inform a card list of 3 x 3");
            return null;
        }
        
        String[][] afterSecShuffle = shuffle(magicalCardGameModel.secShuffleColPos, magicalCardGameModel.firstShuffle);
        
        this.secShuffle = afterSecShuffle;
        
        return this.secShuffle;
    }

    @Override
    public String getFinalResult(MagicalCardGameModel magicalCardGameModel) {
        if (magicalCardGameModel.secShuffle == null) {
            return "Uknow anwser duo to a invalid input";
        } 
        
        guessedCard = magicalCardGameModel.secShuffle[1][1];
        return guessedCard;
    }
    
    private String[][] shuffle(String colPos, String[][] cardMatrix) {
        String[][] afterShuffle = new String[3][3];
        if ("C1".equalsIgnoreCase(colPos)) {
            
            afterShuffle[0][0] = cardMatrix[0][1];
            afterShuffle[0][1] = cardMatrix[1][1];
            afterShuffle[0][2] = cardMatrix[2][1];
            
            afterShuffle[1][0] = cardMatrix[0][0];
            afterShuffle[1][1] = cardMatrix[1][0];
            afterShuffle[1][2] = cardMatrix[2][0];
            
            afterShuffle[2][0] = cardMatrix[0][2];
            afterShuffle[2][1] = cardMatrix[1][2];
            afterShuffle[2][2] = cardMatrix[2][2];    
            
        } else if ("C2".equalsIgnoreCase(colPos)) {
            
            afterShuffle[0][0] = cardMatrix[0][0];
            afterShuffle[0][1] = cardMatrix[1][0];
            afterShuffle[0][2] = cardMatrix[2][0];
            
            
            afterShuffle[1][0] = cardMatrix[0][1];
            afterShuffle[1][1] = cardMatrix[1][1];
            afterShuffle[1][2] = cardMatrix[2][1];
            
            
            afterShuffle[2][0] = cardMatrix[0][2];
            afterShuffle[2][1] = cardMatrix[1][2];
            afterShuffle[2][2] = cardMatrix[2][2];        
            
        } else if ("C3".equalsIgnoreCase(colPos)) {
            
            afterShuffle[0][0] = cardMatrix[0][1];
            afterShuffle[0][1] = cardMatrix[1][1];
            afterShuffle[0][2] = cardMatrix[2][1];
            
            afterShuffle[1][0] = cardMatrix[0][2];
            afterShuffle[1][1] = cardMatrix[1][2];
            afterShuffle[1][2] = cardMatrix[2][2];
            
            afterShuffle[2][0] = cardMatrix[0][0];
            afterShuffle[2][1] = cardMatrix[1][0];
            afterShuffle[2][2] = cardMatrix[2][0];        
        } else {
            System.out.println("Inform a column!");
        }
        
        return afterShuffle;
    }
}
