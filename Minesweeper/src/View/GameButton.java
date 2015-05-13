package View;

import javax.swing.JToggleButton;

/**
 *
 * @author Balint
 */
public class GameButton extends JToggleButton{
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
