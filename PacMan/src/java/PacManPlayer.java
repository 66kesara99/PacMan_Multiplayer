/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kesara
 */
public class PacManPlayer {
    private String name;
    private int score;
    private int x;
    private int y;
    private int initialX;
    private int initialY;
    
    public PacManPlayer (String name, int x, int y){
        this.name = name;
        this.score = 0;
        this.x = x;
        this.initialX = x;
        this.y = y;
        this.initialY = y;
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
    
    public void move(char direction, int width, int height){
        switch (direction){
            case 'U':
                y--;
                if (y<0) y = height;
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
                if (x<0) x = width;
                break;
            default :
                break;
        }                
    }
    
    public void updateScore(char update){
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
            case 'C':
                score -= 3;
                reset();
                break;
            default:
                break;
        }
    }
    
    public void reset(){
        x = initialX;
        y = initialY;
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
