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
public interface IMagicalCardGame {

    /* This function returns the first shuffled
result based on the given criteria.
     */
    public String[][]
            getFirstShuffleResult(MagicalCardGameModel magicalCardGameModel);

    /**
     * This function returns the second shuffled result based on the given
     * criteria.
     */
    public String[][]
            getSecShuffleResult(MagicalCardGameModel magicalCardGameModel);

    /**
     * This function returns the final result.
     */
    public String
            getFinalResult(MagicalCardGameModel magicalCardGameModel);
}
