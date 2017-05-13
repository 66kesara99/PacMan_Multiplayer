/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kesara
 */

// Class for pac man player
public class PacManPlayer {
    
    private String name;
    private int score;
    private int x;
    private int y;
    
    public PacManPlayer (String name, int x, int y){
        this.name = name;
        this.score = 0;
        this.x = x;
        this.y = y;
        
    }
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public String getName(){
        return name;
    }
    
    public int getScore(){
        return score;
    }
    
    // Function to update the position according to the keypress
    public void move(char direction, int width, int height){
        switch (direction){
            case 'U':
                y--;
                if (y<0) y = height-1;
                break;
            case 'D':
                y++;
                y = y%height;
                break;
            case 'R':
                x++;
                x = x%width;
                break;
            case 'L':
                x--;
                if (x<0) x = width-1;
                break;
            default :
                break;
        }                
    }
    
    // Function to update the score
    public void updateScore(char update){
        
        // Score is increased by the color dots
        switch (update){
            case 'R':
                score += 1;
                break;
            case 'G':
                score += 2;
                break;
            case 'B':
                score += 4;
                break;
            case 'C':       // For collisions
                score -= 3;
                break;
            default:
                break;
        }
    }

    
    @Override
    public String toString(){
        
        StringBuffer bf = new StringBuffer("[\"");
        bf.append(name);
        bf.append("\", ");
        bf.append(Integer.toString(score));
        bf.append(", ");
        bf.append(Integer.toString(x));
        bf.append(", ");
        bf.append(Integer.toString(y));
        bf.append("]");
        
        return bf.toString();
    }
}
