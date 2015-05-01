package minesweeper;

import View.MinesweeperView;
import model.IModel;
import model.Model;

/**
 *
 * @author kmate
 */
public class Minesweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      //  IModel logic = new Model();
        //logic.createNew(5, 5, 10);          
        new MinesweeperView().setVisible(true);       
    }

}
