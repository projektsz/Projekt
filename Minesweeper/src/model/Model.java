package model;

import java.util.Random;

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
    public void push(int x, int y) throws FieldIsPushedException {
        if (isMine(x, y) || isPushed(x, y)) {
            throw new FieldIsPushedException("");
        }
        table.setField(x, y, ITableGenerator.marks);
        ++countOfPushed;
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
}
