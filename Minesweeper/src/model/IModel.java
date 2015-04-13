package model;

import java.util.Collection;

/**
 *
 * @author kmate
 */
public interface IModel {

    /**
     * construct a new table with specified properties
     *
     * @param xSize horizontal size of new table
     * @param ySize vertical size of new table
     * @param countOfMines count of mines of new table
     */
    public void createNew(int xSize, int ySize, int countOfMines);

    /**
     * Check this field, is this a mine
     *
     * @param x table's horizontal coordinate
     * @param y table's vertical coordinate
     * @return if the field a mine, returns true; else false
     */
    public boolean isMine(int x, int y);

    /**
     * Check this field, is this pushed
     *
     * @param x table's horizontal coordinate
     * @param y table's vertical coordinate
     * @return if the field pushed, returns true; else false
     */
    public boolean isPushed(int x, int y);

    /**
     * Check the game is fine
     *
     * @return if the game is fine: true ; else false
     */
    public boolean isFine();

    /**
     * Push the (x,y) field
     *
     * @param x table's horizontal coordinate
     * @param y table's vertical coordinate
     * @throws FieldIsPushedException if the (x,y) field is checked
     * @throws FieldIsMineException if the (x,y) field is mine
     */
    public void push(int x, int y) throws FieldIsPushedException, FieldIsMineException;

    /**
     * Give a hint in the format: (x,y)
     *
     * @return returns a pair. The first is x coord., second is y coord.
     */
    public IntPair hint();

    /**
     *
     * @param x table's horizontal coordinate
     * @param y table's vertical coordinate
     * @return number of the mines which is in the neighbour if this element
     */
    public int numberOfNearlyMines(int x, int y);

    /**
     * Find neighbors of (x,y) witch is not pushed or mine. It will push them
     * and return coordinates
     *
     * @param x table's horizontal coordinate
     * @param y table's vertical coordinate
     * @return coordinates of pushed fields
     */
    public Collection<IntPair> findEmptyNeighbors(int x, int y);

}
