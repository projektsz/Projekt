package model;

/**
 *
 * @author kmate
 */
interface ITableGenerator {

    static int mine = -1;
    static int empty = 0;
    static int marks = 1;

    Table generateTable(int xSize, int ySize, int countOfMines);
}
