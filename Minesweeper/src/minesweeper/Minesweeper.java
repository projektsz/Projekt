package minesweeper;

import View.MinesweeperView;
import model.Model;
import model.SpecialTableGenerator;

/**
 *
 * @author kmate
 */
public class Minesweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpecialTableGenerator asd = new SpecialTableGenerator();
        Model logic = new Model(asd);
        logic.createNew(10,10,30);
        new MinesweeperView(logic).setVisible(true);
    }

}
