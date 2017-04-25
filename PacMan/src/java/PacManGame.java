
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
    private PacManPlayer p1, p2, p3, p4;
    private boolean pressed = false;
    
    public PacManGame(int boardWidth, int boardHeight){
        
        board = new PacManBoard(boardWidth, boardHeight);
        board.generateBoard();
        
        players = new ArrayList<>();
        p1 = new PacManPlayer("P1", 0, 0);
        p2 = new PacManPlayer("P2", boardWidth-1, 0);
        p3 = new PacManPlayer("P3", 0, boardHeight-1);
        p4 = new PacManPlayer("P4", boardWidth-1, boardHeight-1);
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
    }
    
//    @Override
//    public void run() {
//        while (!Thread.interrupted())
//            try {
//                synchronized (this) {
//                    // Update game function
//                    notifyAll();
//                    pressed = false;
//                }
//                while (pressed == false){}
////                    Thread.sleep(1);
//            } catch (Exception e) {
//                Logger.getGlobal().log(Level.INFO, "Stock updates terminated!");
//                break;
//            }
//    }
    
    public boolean isColide(PacManPlayer a, PacManPlayer b){
        return a.getX() == b.getX() && a.getY() == b.getY();
    }
    
    public char eatFood(PacManPlayer player){
        return board.removeDot(player.getX(), player.getY());
    }
    
    public void keyPress(char direction){
        pressed = true;
        p1.move(direction, board.getWidth(), board.getHeight());
        char color = eatFood(p1);
        p1.updateScore(color);
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
        
        // System.out.println(output);
        
        return output;
     
    }
   
}
