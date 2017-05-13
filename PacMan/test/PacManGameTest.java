/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
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
public class PacManGameTest {
    
    PacManGame game;
    PacManBoard board;
    PacManPlayer[] players;
    PacManPlayer p1;
    PacManPlayer p2;
    
    
    @Before
    public void setUp() {
        game = new PacManGame(45,45);
        board = game.getPacManGameBoard();
        players = game.getPacManGamePlayers();
        p1 = players[0];
        p2 = players[1];
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void isCollideTest0() {
         
         boolean actual = game.isCollide(p1, p2);
         boolean expected = false;
         
         assertEquals(actual, expected);
     }
     
     @Test
     public void isCollideTest1() {
         
         p2.setPosition(0, 0);
         
         boolean expected = true;
         boolean actual = game.isCollide(p1, p2);
         
         assertEquals(actual, expected);
     }
     
     @Test
     //No collision Test
     public void whoCollideTest0() {
         
         PacManPlayer expected = null;
         PacManPlayer actual = game.whoCollide(p1);
         
         assertEquals(actual, expected);
     }
     
     
     @Test
     //Collision Test
     public void whoCollideTest1() {
         
         p2.setPosition(0, 0);
         
         PacManPlayer expected = p2;
         PacManPlayer actual = game.whoCollide(p1);
         
         assertEquals(actual, expected);
     }
     
     @Test
     public void eatFoodTest0() {
         
         char expected = board.getFoodColour(20, 10);
         p1.setPosition(20, 10);
         
         char actual = game.eatFood(p1);
         
         assertEquals(actual, expected);
     }
     
     @Test
     public void eatFoodTest1() {
         
         char expected = board.getFoodColour(20, 20);
         p1.setPosition(20, 20);
         
         char actual = game.eatFood(p1);
         
         assertEquals(actual, expected);
     }
    
}
