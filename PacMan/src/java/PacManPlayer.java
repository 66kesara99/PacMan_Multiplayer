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
}
