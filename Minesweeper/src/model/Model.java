package model;

import javafx.util.Pair;

/**
 *
 * @author kmate
 */
public class Model implements IModel {

    private ITableGenerator tableGenerator;
    private Table table;

    public Model() {
        tableGenerator = new RandomTableGenarator();
    }

    Model(ITableGenerator tableGenerator) {
        this.tableGenerator = tableGenerator;
    }

    @Override
    public void createNew(int xSize, int ySize, int countOfMines) {
        table = tableGenerator.generateTable(xSize, ySize, countOfMines);
    }

    @Override
    public boolean isMine(int x, int y) {
        return table.getField(x, y) == ITableGenerator.mine;
    }

    @Override
    public boolean isChecked(int x, int y) {
        return table.getField(x, y) == ITableGenerator.marks;
    }

    @Override
    public boolean isFine() {
        throw new UnsupportedOperationException("");
    }

    @Override
    public void check(int x, int y) throws FieldIsAllocatedException {
        if (isMine(x, y) || isChecked(x, y)) {
            throw new FieldIsAllocatedException("");
        }
        table.setField(x, y, ITableGenerator.marks);
    }

    @Override
    public Pair<Integer, Integer> hint() {
        throw new UnsupportedOperationException("");
    }
}
