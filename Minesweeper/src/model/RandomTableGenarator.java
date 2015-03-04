package model;

/**
 *
 * @author kmate
 */
class RandomTableGenarator implements ITableGenerator {

    @Override
    public Table generateTable(int xSize, int ySize, int countOfMines) {
        Table table = new Table(xSize, ySize, ITableGenerator.empty);

        return table;
    }

}
