/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kmate
 */
public class SpecialTableGeneratorTest {

    /**
     * Test of generateTable method, of class SpecialTableGenerator.
     */
    @Test
    public void testGenerateTable() {
        SpecialTableGenerator gen = new SpecialTableGenerator();

        gen.addMine(1, 1);
        gen.addMine(2, 2);
        gen.addMine(3, 3);
        Table table = gen.generateTable(5, 5, 3);
        Table table2 = gen.generateTable(5, 5, 5);

        int countOfMinesOfTable2 = 0;
        int countOfEmptyCellsOfTable2 = 0;
        int countOfEmptyCellsOfTable = 0;

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (table2.getField(i, j) == ITableGenerator.mine) {
                    ++countOfMinesOfTable2;
                }
                if (table2.getField(i, j) == ITableGenerator.empty) {
                    ++countOfEmptyCellsOfTable2;
                }
                if (table.getField(i, j) == ITableGenerator.empty) {
                    ++countOfEmptyCellsOfTable;
                }
            }

        }

        assertEquals(table.getField(1, 1), ITableGenerator.mine);
        assertEquals(table.getField(2, 2), ITableGenerator.mine);
        assertEquals(table.getField(3, 3), ITableGenerator.mine);

        assertEquals(table2.getField(1, 1), ITableGenerator.mine);
        assertEquals(table2.getField(2, 2), ITableGenerator.mine);
        assertEquals(table2.getField(3, 3), ITableGenerator.mine);

        assertEquals(countOfEmptyCellsOfTable2 + countOfMinesOfTable2, 5 * 5);
        assertEquals(countOfMinesOfTable2, 5);
        assertEquals(countOfEmptyCellsOfTable, 5 * 5 - 3);
    }

}
