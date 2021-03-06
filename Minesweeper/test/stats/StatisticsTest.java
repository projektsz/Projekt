/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Balázs
 */
public class StatisticsTest {

    public StatisticsTest() {
    }

    @Before
    public void setUp() {
        Statistics s = Statistics.getInstance();
        s.deleteStatFile();
        s.createStatFile();

        //10 felhasznaloval tesztelunk
        s.addWinner("TestUser0", 5, 23);
        s.addWinner("TestUser1", 5, 31);
        s.addWinner("TestUser2", 10, 45);
        s.addWinner("TestUser3", 10, 36);
        s.addWinner("TestUser4", 10, 31);
        s.addWinner("TestUser5", 15, 61);
        s.addWinner("TestUser6", 15, 69);
        s.addWinner("TestUser7", 15, 58);
        s.addWinner("TestUser8", 15, 59);
        s.addWinner("TestUser9", 15, 68);

        //Nehanyan tobszor is jatszottak ugyan azt
        s.addWinner("TestUser0", 5, 21);
        s.addWinner("TestUser0", 5, 20);
        s.addWinner("TestUser0", 5, 21);
        s.addWinner("TestUser1", 5, 36);
        s.addWinner("TestUser2", 10, 46);
        s.addWinner("TestUser7", 15, 50);
        s.addWinner("TestUser8", 15, 70);
        s.addWinner("TestUser9", 15, 67);

        //Ezek kozul vannak akik mast is jatszottak
        s.addWinner("TestUser1", 10, 19);
        s.addWinner("TestUser7", 5, 22);
        s.addWinner("TestUser8", 15, 74);
    }

    @After
    public void tearDown() {
        Statistics s = Statistics.getInstance();
        s.deleteStatFile();
    }

    /**
     * Test of getInstance method, of class Statistics.
     */
    @Test
    public void testGetInstance() {
        Statistics expResult = Statistics.getInstance();
        Statistics result = Statistics.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of isStatfileExists method, of class Statistics.
     */
    @Test
    public void testIsStatfileExists() {
        Statistics instance = Statistics.getInstance();
        boolean expResult = true;
        boolean result = instance.isStatfileExists();
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteStatFile method, of class Statistics.
     */
    @Test
    public void testDeleteStatFile() {
        Statistics instance = Statistics.getInstance();
        instance.deleteStatFile();
        assertEquals(0, instance.getWinnerCount());
    }

    /**
     * Test of createStatFile method, of class Statistics.
     */
    @Test
    public void testCreateStatFile() {
        Statistics instance = Statistics.getInstance();
        try {
            instance.createStatFile();
        } catch (Exception e) {
            fail("Exception az createStatFile test");
        }
    }

    /**
     * Test of addWinner method, of class Statistics.
     */
    @Test
    public void testAddWinner_3args() {
        String name = "TestUserAddWinner_3args";
        int gametype = 1;
        int time = 56;
        Statistics instance = Statistics.getInstance();

        int winnercount = instance.getWinnerCount();
        instance.addWinner(name, gametype, time);
        assertEquals(winnercount + 1, instance.getWinnerCount());
    }

    /**
     * Test of addWinner method, of class Statistics.
     */
    @Test
    public void testAddWinner_Winner() {
        Winner winner = new Winner("TestUserAddWinner", 0, 40);
        Statistics instance = Statistics.getInstance();

        int winnercount = instance.getWinnerCount();
        instance.addWinner(winner);
        assertEquals(winnercount + 1, instance.getWinnerCount());
    }

    /**
     * Test of getRecorder method, of class Statistics.
     */
    @Test
    public void testGetRecorder() {
        Statistics instance = Statistics.getInstance();

        Winner result = instance.getRecorder(5);
        assertEquals("TestUser0", result.getName());
        assertEquals(5, result.getGametype());
        assertEquals(20, result.getTime());

        result = instance.getRecorder(10);
        assertEquals("TestUser1", result.getName());
        assertEquals(10, result.getGametype());
        assertEquals(19, result.getTime());

        result = instance.getRecorder(15);
        assertEquals("TestUser7", result.getName());
        assertEquals(15, result.getGametype());
        assertEquals(50, result.getTime());
    }

    /**
     * Test of getAverageTime method, of class Statistics.
     */
    @Test
    public void testGetAverageTime() {
        Statistics instance = Statistics.getInstance();

        float result = instance.getAverageTime(5);
        assertEquals(174.0 / 7.0, result, 0.1);

        result = instance.getAverageTime(10);
        assertEquals(177.0 / 5.0, result, 0.1);

        result = instance.getAverageTime(15);
        assertEquals(576.0 / 9.0, result, 0.1);
    }

    /**
     * Test of getWinnerCount method, of class Statistics.
     */
    @Test
    public void testGetWinnerCount_int() {
        Statistics instance = Statistics.getInstance();

        int result = instance.getWinnerCount(5);
        assertEquals(7, result);

        result = instance.getWinnerCount(10);
        assertEquals(5, result);

        result = instance.getWinnerCount(15);
        assertEquals(9, result);
    }

    /**
     * Test of getWinnerCount method, of class Statistics.
     */
    @Test
    public void testGetWinnerCount_0args() {
        Statistics instance = Statistics.getInstance();
        int expResult = 21;
        int result = instance.getWinnerCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMostWinner method, of class Statistics.
     */
    @Test
    public void testGetMostWinner_int() {
        Statistics instance = Statistics.getInstance();

        String result = instance.getMostWinner(5);
        assertEquals("TestUser0", result);

        result = instance.getMostWinner(10);
        assertEquals("TestUser2", result);

        result = instance.getMostWinner(15);
        assertEquals("TestUser8", result);
    }

    /**
     * Test of getMostWinner method, of class Statistics.
     */
    @Test
    public void testGetMostWinner_0args() {
        Statistics instance = Statistics.getInstance();
        String expResult = "TestUser0";
        String result = instance.getMostWinner();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWinners method, of class Statistics.
     */
    @Test
    public void testGetWinners() {
        Statistics instance = Statistics.getInstance();
        List<Winner> result = instance.getWinners();
        assertEquals(21, result.size());
    }
}
