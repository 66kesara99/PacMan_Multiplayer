/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author e13110
 */
public class PacManBoardTest {
    
    PacManBoard board = new PacManBoard(40, 40);
    
    @Test
    public void removeDotTest(){
        
        char expected = board.getFoodColour(20, 20);
        char actual = board.removeDot(20, 20);
         
        assertEquals(actual, expected);
    }

}
