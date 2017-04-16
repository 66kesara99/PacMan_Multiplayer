
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
public class PacManBoard {
    private int width;
    private int height;
    private HashMap<Integer, Character> board;
    
    public PacManBoard (int width, int height){
        this.width = width;
        this.height = height;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }
    
    public void generateBoard(){
        
        for (int i = 0; i<width; i++){
            for (int j = 0; j<height; j++){
                Random rand = new Random();
                int col = rand.nextInt()%4;
                
                // If col == 0 board is empty
                switch (col) {
                    case 1:
                        board.put(j*width+i, 'R');
                        break;
                    case 2:
                        board.put(j*width+i, 'G');
                        break;
                    case 3:
                        board.put(j*width+i, 'B');
                        break;
                    default:
                        break;
                }
            }
        }
        
    }
    
    public HashMap<Integer, Character> getBoard(){
        return board;
    }
}
