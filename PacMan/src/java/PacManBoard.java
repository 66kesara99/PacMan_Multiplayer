
import java.util.HashMap;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kesara
 */


// Class for saving the game board
public class PacManBoard {
    
    private final int width;
    private final int height;
    private final HashMap<Integer, String> board;
    
    public PacManBoard (int width, int height){
        this.width = width;
        this.height = height;
        board = new HashMap<>();
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }
    
    public HashMap<Integer, String> getBoard(){
        return board;
    }
    
    // Function to generate a board with random food
    public void generateBoard(){
        
        for (int i = 0; i<width; i++){
            for (int j = 0; j<height; j++){
                
                Random rand = new Random();
                int col = rand.nextInt(4);
                
                // Keeping 4 corners of the board empty (Initial positions of the players)
                if((i == 0 && j == 0)|| (i == 0 && j == height-1)|| (i == width-1 && j == 0)||(i == width-1 && j == height-1)){
                    col = 0;
                }
                
                // If col == 0 board is empty othewise it has dots
                switch (col) {
                    case 1:
                        board.put(j*width+i, "\"R\"");
                        break;
                    case 2:
                        board.put(j*width+i, "\"G\"");
                        break;
                    case 3:
                        board.put(j*width+i, "\"B\"");
                        break;
                    default:
                        break;
                }
            }
        }
        
    }
    
    // Function to remove a given food dot and return the repective colour
    public char removeDot(int x, int y){
        int key = y*width+x;
        char colour = 0;
        if (board.containsKey(key)){
            String val = board.get(key);
            if (val.equals("\"R\""))
                colour = 'R';
            else if (val.equals("\"G\""))
                colour = 'G';
            else if (val.equals("\"B\""))
                colour = 'B';
            board.remove(key);
        }
        return colour;
    }
    
    //Function to get return the colour of a given dot
    public char getFoodColour(int x, int y){
        int key = y * width + x;
        char colour = 0;
        if (board.containsKey(key)){
            String val = board.get(key);
            if (val.equals("\"R\""))
                colour = 'R';
            else if (val.equals("\"G\""))
                colour = 'G';
            else if (val.equals("\"B\""))
                colour = 'B';
        }
        return colour;
        
    }
   
}
