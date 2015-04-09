package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author kmate
 */
class SpecialTableGenerator implements ITableGenerator {

    private final List<IntPair> mines;

    public SpecialTableGenerator() {
        mines = new ArrayList<>();
    }

    /**
     * Adding new mine. When you call {generateTable(int xSize, int ySize, int
     * countOfMines)} generate mines in this coordinates
     *
     * @param x new mine horizontal coord.
     * @param y new mine vertical coord.
     */
    public void addMine(int x, int y) {
        mines.add(new IntPair(x, y));
    }

    /**
     * Generate a new table with xSize horizontal size and ySize vertical size,
     * and fill mines in specified coordinates. If size of specifies coordinates
     * less than countOfMines, fill in random coordinates.
     *
     * @param xSize new table's horizontal size
     * @param ySize new table's vertical size
     * @param countOfMines count of the generated mines
     */
    @Override
    public Table generateTable(int xSize, int ySize, int countOfMines) {
        Table table = new Table(xSize, ySize, ITableGenerator.empty);

        Set<IntPair> randomMines = new TreeSet<>();
        Random rnd = new Random();
        for (int i = 0, minesIndex = 0; i < countOfMines; ++i, ++minesIndex) {
            if (minesIndex < mines.size()) {
                table.setField(mines.get(i).first, mines.get(i).second,
                        ITableGenerator.mine);
            } else {
                IntPair rndCoord = new IntPair();
                rndCoord.first = rnd.nextInt(xSize);
                rndCoord.second = rnd.nextInt(ySize);

                while (mines.contains(rndCoord)
                        || randomMines.contains(rndCoord)) {
                    rndCoord.first = rnd.nextInt(xSize);
                    rndCoord.second = rnd.nextInt(ySize);
                }
                randomMines.add(rndCoord);

                table.setField(rndCoord.first, rndCoord.second,
                        ITableGenerator.mine);
            }
        }
        return table;
    }

}
