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
public class ModelTest {

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
     * Test of createNew method, of class Model.
     */
    @Test
    public void testCreateNew() {
        System.out.println("createNew");
        int horisontalSize = 0;
        int verticalSize = 0;
        int countOfMines = 0;
        Model instance = new Model();
        instance.createNew(horisontalSize, verticalSize, countOfMines);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMine method, of class Model.
     */
    @Test
    public void testIsMine() {
        System.out.println("isMine");
        int x = 0;
        int y = 0;
        Model instance = new Model();
        boolean expResult = false;
        boolean result = instance.isMine(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isChecked method, of class Model.
     */
    @Test
    public void testIsChecked() {
        System.out.println("isChecked");
        int x = 0;
        int y = 0;
        Model instance = new Model();
        boolean expResult = false;
        boolean result = instance.isPushed(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFine method, of class Model.
     */
    @Test
    public void testIsFine() {
        System.out.println("isFine");
        Model instance = new Model();
        boolean expResult = false;
        boolean result = instance.isFine();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of check method, of class Model.
     */
    @Test
    public void testCheck() throws FieldIsPushedException {
        System.out.println("check");
        int x = 0;
        int y = 0;
        Model instance = new Model();
        instance.push(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hint method, of class Model.
     */
    @Test
    public void testHint() {
        System.out.println("hint");
        Model instance = new Model();
        IntPair expResult = null;
        IntPair result;
        result = instance.hint();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
