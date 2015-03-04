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

    public TableTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getField method, of class Table.
     */
    @Test
    public void testGetField() {
        System.out.println("getField");
        int x = 0;
        int y = 0;
        Table instance = null;
        int expResult = 0;
        int result = instance.getField(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setField method, of class Table.
     */
    @Test
    public void testSetField() {
        System.out.println("setField");
        int x = 0;
        int y = 0;
        int value = 0;
        Table instance = null;
        instance.setField(x, y, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
