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
        IModel logic = new Model();
        logic.createNew(10, 10, 30);
        new MinesweeperView(logic).setVisible(true);
    }

}
