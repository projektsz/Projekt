package model;

import javafx.util.Pair;

/**
 *
 * @author kmate
 */
public interface IModel {

    public void createNew(int xSize, int ySize, int countOfMines);

    public boolean isMine(int x, int y);

    public boolean isChecked(int x, int y);

    public boolean isFine();

    public void check(int x, int y) throws FieldIsAllocatedException;

    public Pair<Integer, Integer> hint();

}
