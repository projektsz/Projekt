package model;

/**
 *
 * @author kmate
 */
interface ITableGenerator {

    static final int mine = -1;
    static final int empty = 0;
    static final int marks = 1;

    /**
     * Generate a Table object with mines
     *
     * @param xSize horizontal size of generated table
     * @param ySize vertical size of generated table
     * @param countOfMines number of mines in table
     * @return Table object with mines
     */
    Table generateTable(int xSize, int ySize, int countOfMines);
}
