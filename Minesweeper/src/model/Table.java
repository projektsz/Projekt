package model;

/**
 *
 * @author kmate
 */
class Table {

    private final int[][] table;

    /**
     * construct an object with a new table
     *
     * @param xSize table size in x axis
     * @param ySize table size in y axis
     */
    Table(int xSize, int ySize) {
        table = new int[xSize][ySize];
    }

    /**
     * construct an object with a new table with full of default value
     *
     * @param xSize table size in x axis
     * @param ySize table size in y axis
     * @param defaultValue the new table all fields has this value
     */
    Table(int xSize, int ySize, int defaultValue) {
        table = new int[xSize][ySize];
        setAllField(defaultValue);
    }

    /**
     * returns the actual value on table's (x,y) coordinate
     *
     * @param x table coordinate in x axis
     * @param y table coordinate in y axis
     * @return return value is the data on (x,y) coord.
     */
    int getField(int x, int y) {
        return table[x][y];
    }

    /**
     * set the table's field to new value
     *
     * @param x table coordinate in x axis
     * @param y table coordinate in y axis
     * @param value new value of (x,y) coord.
     */
    void setField(int x, int y, int value) {
        table[x][y] = value;
    }

    /**
     * sets all field the value
     *
     * @param x table coordinate in x axis
     * @param y table coordinate in y axis
     * @param value all field of table has this value
     */
    private void setAllField(int value) {
        for (int i = 0; i < table.length; ++i) {
            for (int j = 0; j < table[i].length; ++j) {
                table[i][j] = value;
            }
        }
    }

    /**
     * @return return returns the horizontal size of table
     */
    public int getXSize() {
        return table.length;
    }

    /**
     * @return return returns the vertical size of table
     */
    public int getYSize() {
        return table[0].length;
    }
}
