/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kmate
 */
public class RandomTableGenaratorTest {

    /**
     * Test of generateTable method, of class RandomTableGenarator.
     */
    @Test
    public void testGenerateTable() {
        int xSize = 10;
        int ySize = 10;
        int countOfMines = 25;
        RandomTableGenarator instance = new RandomTableGenarator();
        Table result = instance.generateTable(xSize, ySize, countOfMines);
        int countOfMinesOfResult = 0;
        int countOfEmptyCellsOfResult = 0;

        for (int i = 0; i < result.getXSize(); ++i) {
            for (int j = 0; j < result.getYSize(); ++j) {
                if (result.getField(i, j) == ITableGenerator.mine) {
                    ++countOfMinesOfResult;
                }
                if (result.getField(i, j) == ITableGenerator.empty) {
                    ++countOfEmptyCellsOfResult;
                }
            }

        }
        assertEquals(countOfEmptyCellsOfResult + countOfMinesOfResult,
                xSize * ySize);
        assertEquals(countOfMinesOfResult, countOfMines);
    }

}
