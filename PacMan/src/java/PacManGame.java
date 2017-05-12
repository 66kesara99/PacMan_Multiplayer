
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
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
public class PacManGame {
    private PacManBoard board;
    private PacManPlayer[] players = new PacManPlayer[4];
    
    public PacManGame(int boardWidth, int boardHeight){
        
        board = new PacManBoard(boardWidth, boardHeight);
        board.generateBoard();

        players[0] = new PacManPlayer("P1", 0, 0);
        players[1] = new PacManPlayer("P2", boardWidth-1, 0);
        players[2] = new PacManPlayer("P3", 0, boardHeight-1);
        players[3] = new PacManPlayer("P4", boardWidth-1, boardHeight-1);
    }
    
    public boolean isCollide(PacManPlayer a, PacManPlayer b){
        return (a.getX() == b.getX() && a.getY() == b.getY());
    }
   
    
    public PacManPlayer whoCollide(PacManPlayer player){
        for (PacManPlayer p: players){
            if (!(player.getName().equals(p.getName())) && isCollide(player, p)){
                return p;
            }
        }
        return null;
    }
    
    public void checkCollide(PacManPlayer player){
        
        while (whoCollide(player) != null){
            PacManPlayer p = whoCollide(player);

            while (isCollide(player, p)){
                Random rand = new Random();
                int x1 = rand.nextInt(board.getWidth());
                int y1 = rand.nextInt(board.getHeight());
                p.setPosition(x1, y1);
                p.updateScore('C');
//                System.out.println("p1: "+x1+" "+y1);

                int x2 = rand.nextInt(board.getWidth());
                int y2 = rand.nextInt(board.getHeight());
                player.setPosition(x2, y2);
                player.updateScore('C');
//                System.out.println("p2: "+x2+" "+y2);

            }

        }
    }
    
    public char eatFood(PacManPlayer player){
        return board.removeDot(player.getX(), player.getY());
    }
    
    
    
    public void keyPress(int playerId, char direction){
        
        
        players[playerId].move(direction, board.getWidth(), board.getHeight());
        
        checkCollide(players[playerId]);
        
        char color = eatFood(players[playerId]);
        players[playerId].updateScore(color);
    }
    
    public String getBoardState(int playerCount){
        
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

            StringBuilder bf = new StringBuilder("[");
            bf.append(dot);
            bf.append("]");
            dotArray.add(bf.toString());
            
        }
        
        String dotData = String.join(",", dotArray);
        
        // Inserting player data to playerArray in given format
        ArrayList <String> playerArray = new ArrayList();
        for (int i = 0; i<playerCount; i++){
            playerArray.add(players[i].toString());
        }
        
        String playerData = String.join(",", playerArray);
                
        // Generatin JSON format
        String output = "{ \"DOTS\": [" + dotData + " ], \"PLAYERS\": [ " + playerData + "] }";
        
        // System.out.println(output);
        
        return output;
     
    }
   
}
