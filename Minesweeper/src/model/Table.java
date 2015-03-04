/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author kmate
 */
class Table {

    private final int[][] table;

    Table(int xSize, int ySize) {
        table = new int[xSize][ySize];
    }

    Table(int xSize, int ySize, int defaultValue) {
        table = new int[xSize][ySize];
        setAllField(defaultValue);
    }

    int getField(int x, int y) {
        return table[x][y];
    }

    void setField(int x, int y, int value) {
        table[x][y] = value;
    }

    private void setAllField(int value) {
        for (int i = 0; i < table.length; ++i) {
            for (int j = 0; j < table[i].length; ++j) {
                table[i][j] = value;
            }
        }
    }
}
