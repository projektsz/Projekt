package model;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author kmate
 */
class RandomTableGenarator implements ITableGenerator {

    /**
     * Generate a new table with xSize horizontal size and ySize vertical size,
     * and fill mines
     *
     * @param xSize new table's horizontal size
     * @param ySize new table's vertical size
     * @param countOfMines count of the generated mines
     */
    @Override
    public Table generateTable(int xSize, int ySize, int countOfMines) {
        Table table = new Table(xSize, ySize, ITableGenerator.empty);
        Set<IntPair> usedPlaces = new TreeSet<>();
        Random rnd = new Random();

        while (usedPlaces.size() < countOfMines) {
            IntPair coord = new IntPair();

            coord.first = rnd.nextInt(xSize);
            coord.second = rnd.nextInt(ySize);

            if (!usedPlaces.contains(coord)) {
                usedPlaces.add(coord);
                table.setField(coord.first, coord.second, ITableGenerator.mine);
            }
        }
        return table;
    }
}
