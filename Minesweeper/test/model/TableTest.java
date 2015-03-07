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
public class TableTest {

    /**
     * Test of constructor, of class Table.
     */
    @Test
    public void testConstructor() {
        Table table1 = new Table(10, 11);
        assertEquals(table1.getXSize(), 10);
        assertEquals(table1.getYSize(), 11);

        Table table2 = new Table(10, 20, 42);
        assertEquals(table2.getXSize(), 10);
        assertEquals(table2.getYSize(), 20);
        for (int i = 0; i < table2.getXSize(); ++i) {
            for (int j = 0; j < table2.getYSize(); ++j) {
                assertEquals(table2.getField(i, j), 42);
            }
        }
    }

    /**
     * Test of setField method, of class Table.
     */
    @Test
    public void testSetField() {
        Table table = new Table(10, 10, 42);
        table.setField(5, 5, 5);
        table.setField(6, 6, 6);

        assertEquals(table.getField(5, 5), 5);
        assertEquals(table.getField(6, 6), 6);
    }

}
