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
public class RandomTableGenaratorTest {

    public RandomTableGenaratorTest() {
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
     * Test of generateTable method, of class RandomTableGenarator.
     */
    @Test
    public void testGenerateTable() {
        System.out.println("generateTable");
        int xSize = 0;
        int ySize = 0;
        int countOfMines = 0;
        RandomTableGenarator instance = new RandomTableGenarator();
        Table expResult = null;
        Table result = instance.generateTable(xSize, ySize, countOfMines);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
