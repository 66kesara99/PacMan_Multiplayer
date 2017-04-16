
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kesara
 */
public class PacManGame {
    private PacManBoard board;
    private ArrayList<PacManPlayer> players;
    
    public PacManGame(int boardWidth, int boardHeight){
        
        board = new PacManBoard(boardWidth, boardHeight);
        
        players = new ArrayList<>();
        players.add(new PacManPlayer("P1", 0, 0));
        players.add(new PacManPlayer("P2", boardWidth, 0));
        players.add(new PacManPlayer("P3", 0, boardHeight));
        players.add(new PacManPlayer("P4", boardWidth, boardHeight));
        

    }
    
    public String getBoardJSON(){
        HashMap<Integer, Character> dots = board.getBoard();
        String output = "JSON String";
        return output;
    }
}
