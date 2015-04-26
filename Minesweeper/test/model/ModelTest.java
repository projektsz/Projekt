/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kmate
 */
public class ModelTest {

    private Model model;
    private List<IntPair> mines;

    public ModelTest() {
        mines = new ArrayList<>();
        mines.add(new IntPair(10, 10));
        mines.add(new IntPair(5, 5));
        mines.add(new IntPair(2, 2));
        mines.add(new IntPair(15, 9));
        mines.add(new IntPair(7, 7));
    }

    @Before
    public void setUp() {
        SpecialTableGenerator gen = new SpecialTableGenerator();
        for (int i = 0; i < mines.size(); ++i) {
            gen.addMine(mines.get(i).first, mines.get(i).second);
        }
        model = new Model(gen);
    }

    /**
     * Test of isMine method, of class Model.
     */
    @Test
    public void testIsMine() {
        model.createNew(20, 20, 5);
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                if (mines.contains(new IntPair(i, j))) {
                    assertTrue(model.isMine(i, j));
                } else {
                    assertFalse(model.isMine(i, j));
                }
            }
        }
    }

    /**
     * Test of isPushed method, of class Model.
     */
    @Test
    public void testIsPushed() throws FieldIsPushedException, FieldIsMineException {
        model.createNew(20, 20, 5);

        model.push(10, 11);

        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                if (i == 10 && j == 11) {
                    assertTrue(model.isPushed(i, j));
                } else {
                    assertFalse(model.isPushed(i, j));
                }
            }
        }
    }

    /**
     * Test of isFine method, of class Model.
     */
    @Test
    public void testIsFine() throws FieldIsPushedException, FieldIsMineException {
        model.createNew(20, 20, 5);
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                if (!mines.contains(new IntPair(i, j))) {
                    model.push(i, j);
                }
                if (!(i == 19 && j == 19)) {
                    assertFalse(model.isFine());
                }
            }
        }
        assertTrue(model.isFine());
    }

    /**
     * Test of push method, of class Model.
     */
    @Test
    public void testPush() throws FieldIsPushedException, FieldIsMineException {
        model.createNew(20, 20, 5);

        model.push(10, 11);
        assertTrue(model.isPushed(10, 11));
        try {
            model.push(10, 10);
            assertTrue(false);
        } catch (FieldIsMineException e) {
            assertTrue(true);
        }
        try {
            model.push(10, 11);
            assertTrue(false);
        } catch (FieldIsPushedException e) {
            assertTrue(true);
        }
    }

    /**
     * Test of hint method, of class Model.
     */
    @Test
    public void testHint() {
        model.createNew(20, 20, 5);

        IntPair pair = model.hint();
        assertFalse(model.isMine(pair.first, pair.second));
        assertFalse(model.isPushed(pair.first, pair.second));
    }

    /**
     *
     */
    @Test
    public void testNumberOfNearlyMines() {
        model.createNew(20, 20, 5);

        assertEquals(model.numberOfNearlyMines(6, 6), 2);
        assertEquals(model.numberOfNearlyMines(6, 7), 1);
        assertEquals(model.numberOfNearlyMines(0, 0), 0);
    }

    /**
     * Test of findEmptyNeighbors method, of class Model
     */
    @Test
    public void testFindEmptyNeighbors() {
        model.createNew(20, 20, 5);
        Collection<IntPair> found = model.findEmptyNeighbors(3, 11);

        Set<IntPair> notPushed = new HashSet<>();
        for (IntPair mine : mines) {
            for (int rowInd = mine.first - 1; rowInd <= mine.first + 1; ++rowInd) {
                for (int colInd = mine.second - 1; colInd <= mine.second + 1; ++colInd) {
                    assertFalse(model.isPushed(rowInd, colInd));
                    notPushed.add(new IntPair(rowInd, colInd));
                }
            }
        }
        Set<IntPair> pushed = new TreeSet<>();
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                IntPair act = new IntPair(i, j);
                if (!notPushed.contains(act)) {
                    assertTrue(model.isPushed(i, j));
                    pushed.add(act);
                }
            }
        }
        assertEquals(pushed, found);
    }
}
