package model;

/**
 *
 * @author kmate
 */
public class Model implements IModel {

    private ITableGenerator tableGenerator;
    private Table table;
    int countOfMines;
    int countOfPushed;

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
        countOfMines = 25;
        countOfPushed = 0;
        table = tableGenerator.generateTable(10, 10, countOfMines);
    }

    @Override
    public void createNew(int xSize, int ySize, int countOfMines) {
        table = tableGenerator.generateTable(xSize, ySize, countOfMines);
        this.countOfMines = countOfMines;
        this.countOfPushed = 0;
    }

    @Override
    public boolean isMine(int x, int y) {
        return table.getField(x, y) == ITableGenerator.mine;
    }

    @Override
    public boolean isPushed(int x, int y) {
        return table.getField(x, y) == ITableGenerator.marks;
    }

    @Override
    public boolean isFine() {
        return countOfMines + countOfPushed == table.getXSize() * table.getYSize();
    }

    @Override
    public void push(int x, int y) throws FieldIsPushedException {
        if (isMine(x, y) || isPushed(x, y)) {
            throw new FieldIsPushedException("");
        }
        table.setField(x, y, ITableGenerator.marks);
        ++countOfPushed;
    }

    @Override
    public IntPair hint() {
        throw new UnsupportedOperationException("");
    }
}
