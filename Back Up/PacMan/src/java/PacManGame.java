
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kesara
 */
public class PacManGame extends Thread {
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
    
    @Override
    public void run() {
        while (!Thread.interrupted())
            try {
                synchronized (this) {
                    // Update game function
                    notifyAll();
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Logger.getGlobal().log(Level.INFO, "Stock updates terminated!");
                break;
            }
    }
    
    public String getBoardJSON(){
        HashMap<Integer, Character> dots = board.getBoard();
        String output = "{" +
                "  \"DOTS\":   [" +
                " [\"B\", 5, 6] , [\"G\", 23, 12] ,  [\"R\", 34, 7], [\"B\", 25, 8] , [\"G\", 28, 1] ,  [\"R\", 42, 17]," +
                " [\"B\", 15, 36] , [\"G\", 22, 22] ,  [\"R\", 5, 37], [\"B\", 25, 28] , [\"G\", 9, 39] ,  [\"R\", 10, 21] " +
                " ] , " +
                " \"PLAYERS\": [ " +
                " [\"P1\", 8, 0, 0] , [\"P2\", 5, 44, 0] , " +
                " [\"P3\", -6, 0, 44] , [\"P4\", 10, 44, 44]" +
                " ] " +
                " }";
        return output;
    }
}
