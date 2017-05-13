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
public class PacManPlayerTest {
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
    
    @Test
     //Test player movement UP and appear at other end
     public void moveTest0() {
         
         p1.move('U', board.getWidth(), board.getHeight());
         
         int actualY = p1.getY();
         int expectedY = board.getHeight() - 1;
         
         assertEquals(actualY, expectedY);
     }
     
     @Test
     //Test player movement LEFT and appear at other end
     public void moveTest1() {
         
         p1.move('L', board.getWidth(), board.getHeight());
         
         int actualX = p1.getX();
         int expectedX = board.getWidth() - 1;
         
         assertEquals(actualX, expectedX);
     }
     
     @Test
     //Test player movement DOWN
     public void moveTest2() {
         
         p1.move('D', board.getWidth(), board.getHeight());
         
         int actualY = p1.getY();
         int expectedY = 1;
         
         assertEquals(actualY, expectedY);
     }
     
     @Test
     //Test player movement RIGHT
     public void moveTest3() {
         
         p1.move('R', board.getWidth(), board.getHeight());
         
         int actualX = p1.getX();
         int expectedX = 1;
         
         assertEquals(actualX, expectedX);
     }
     
     @Test
     public void updateScoreTest0() {
         
         p1.setScore(10);
         p1.updateScore('R');
         
         int actualX = p1.getScore();
         int expectedX = 10 + 1;
         
         assertEquals(actualX, expectedX);
     }
     
     @Test
     public void updateScoreTest1() {
         
         p1.setScore(10);
         p1.updateScore('G');
         
         int actualX = p1.getScore();
         int expectedX = 10 + 2;
         
         assertEquals(actualX, expectedX);
     }

     @Test
     public void updateScoreTest2() {
         
         p1.setScore(10);
         p1.updateScore('B');
         
         int actualX = p1.getScore();
         int expectedX = 10 + 4;
         
         assertEquals(actualX, expectedX);
     }
     
     @Test
     public void updateScoreTest3() {
         
         p1.setScore(10);
         p1.updateScore('C');
         
         int actualX = p1.getScore();
         int expectedX= 10 - 3;
         
         assertEquals(actualX, expectedX);
     }
}
