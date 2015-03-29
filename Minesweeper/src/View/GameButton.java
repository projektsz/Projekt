/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JButton;

/**
 *
 * @author Balint
 */
public class GameButton extends JButton{
    private int x;
    private int y;

    public GameButton(int x, int y) {
        //super();
        this.x = x;
        this.y = y;
        
    }
    
    public int getXB(){
        return x;
    }
    
    public int getYB(){
        return y;
    }
    
}
