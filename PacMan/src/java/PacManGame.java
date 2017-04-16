
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
        board.generateBoard();
        
        players = new ArrayList<>();
        players.add(new PacManPlayer("P1", 0, 0));
        players.add(new PacManPlayer("P2", boardWidth-1, 0));
        players.add(new PacManPlayer("P3", 0, boardHeight-1));
        players.add(new PacManPlayer("P4", boardWidth-1, boardHeight-1));
        

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
    
    public String getBoardState(){
        
        HashMap<Integer, String> dots = board.getBoard();
        
        // Inserting board details to dotArray in given format
        ArrayList <String> dotArray = new ArrayList();
        
        for(Integer key : dots.keySet()){
        
            ArrayList <String> dotData = new ArrayList();
            
            String colour = dots.get(key);
            // Calculating x-y coordinates from unique key
            int x = key % board.getWidth();
            int y = key / board.getWidth();
            
            dotData.add(colour);
            dotData.add(Integer.toString(x));
            dotData.add(Integer.toString(y));
            
            String dot = String.join(", ", dotData);

            StringBuffer bf = new StringBuffer("[");
            bf.append(dot);
            bf.append("]");
            dotArray.add(bf.toString());
            
        }
        
        String dotData = String.join(",", dotArray);
        
        // Inserting player data to playerArray in given format
        ArrayList <String> playerArray = new ArrayList();
        for (PacManPlayer p : players){
            playerArray.add(p.toString());
        }
        
        String playerData = String.join(",", playerArray);
                
        // Generatin JSON format
        String output = "{ \"DOTS\": [" + dotData + " ], \"PLAYERS\": [ " + playerData + "] }";
        
        System.out.println(output);
        
        return output;
     
    }
   
}
