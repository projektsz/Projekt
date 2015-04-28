package model;

import java.util.Collection;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author kmate
 */
public class Model implements IModel {

    private ITableGenerator tableGenerator;
    private Table table;
    private int countOfMines;
    private int countOfPushed;

    /**
     * Construct a new object with 10x10 table, and 35 mines.
     */
    public Model() {
        this(new RandomTableGenarator());
    }

    /**
     * Construct a new object with 10x10 table, and 35 mines.
     *
     * @param tableGenerator ITableGenerator object. The model will use this in
     * createNew method
     */
    Model(ITableGenerator tableGenerator) {
        this.tableGenerator = tableGenerator;
    }

    /**
     * See {@link IModel#createNew(int xSize, int ySize, int countOfMines)}
     */
    @Override
    public void createNew(int xSize, int ySize, int countOfMines) {
        table = tableGenerator.generateTable(xSize, ySize, countOfMines);
        this.countOfMines = countOfMines;
        this.countOfPushed = 0;
    }

    /**
     * See {@link IModel#isMine(int x, int y)}
     */
    @Override
    public boolean isMine(int x, int y) {
        return table.getField(x, y) == ITableGenerator.mine;
    }

    /**
     * See {@link IModel#isPushed(int x, int y)}
     */
    @Override
    public boolean isPushed(int x, int y) {
        return table.getField(x, y) == ITableGenerator.marks;
    }

    /**
     * See {@link IModel#isFine()}
     */
    @Override
    public boolean isFine() {
        return countOfMines + countOfPushed == table.getXSize() * table.getYSize();
    }

    /**
     * See {@link IModel#push(int x, int y)}
     */
    @Override
    public void push(int x, int y) throws FieldIsPushedException, FieldIsMineException {
        if (isPushed(x, y)) {
            throw new FieldIsPushedException("");
        }
        if (isMine(x, y)) {
            throw new FieldIsMineException("");
        }

        table.setField(x, y, ITableGenerator.marks);
        ++countOfPushed;
    }

    /**
     * See {@link IModel#numberOfNearlyMines()}
     */
    @Override
    public int numberOfNearlyMines(int x, int y) {
        int mines = 0;
        for (int rowMod = -1; rowMod <= 1; ++rowMod) {
            for (int colMod = -1; colMod <= 1; ++colMod) {
                try {
                    if (isMine(x + colMod, y + rowMod)) {
                        ++mines;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return mines;
    }

    /**
     * See {@link IModel#hint()}
     */
    @Override
    public IntPair hint() {
        Random rnd = new Random();
        int x = rnd.nextInt(table.getXSize());
        int y = rnd.nextInt(table.getYSize());
        while (isPushed(x, y) || isMine(x, y)) {
            x = rnd.nextInt();
            y = rnd.nextInt();
        }

        return new IntPair(x, y);
    }

    /**
     * See {@link IModel#findEmptyNeighbors()}
     */
    @Override
    public Collection<IntPair> findEmptyNeighbors(int x, int y) {
        Set<IntPair> coords = new TreeSet<>();

        for (int rowInd = x - 1; rowInd <= x + 1; ++rowInd) {
            for (int colInd = y - 1; colInd <= y + 1; ++colInd) {
                try {
                    if (//!(x == rowInd && y == colInd)
                            //  &&
                            numberOfNearlyMines(rowInd, colInd) == 0) {

                        push(rowInd, colInd);

                        coords.add(new IntPair(rowInd, colInd));
                        coords.addAll(findEmptyNeighbors(rowInd, colInd));
                    }
                } catch (ArrayIndexOutOfBoundsException |
                        FieldIsMineException | FieldIsPushedException e) {
                    // if (x,y) is the border of table or exceptions of push()
                }
            }
        }
        return coords;
    }

    private void pushAll(Collection<IntPair> coords)
            throws FieldIsPushedException, FieldIsMineException {
        for (IntPair coord : coords) {
            push(coord.first, coord.second);
        }
    }
    
}
