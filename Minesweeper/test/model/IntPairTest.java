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
public class IntPairTest {

    private IntPair one;
    private IntPair two;

    @Before
    public void setUp() {
        one = new IntPair();
        two = new IntPair();
    }

    /**
     * Test of constructor, of class IntPair.
     */
    @Test
    public void testConstructor() {

        IntPair pair = new IntPair();
        IntPair pair2 = new IntPair(1, 2);

        assertEquals(pair.first, 0);
        assertEquals(pair.second, 0);

        assertEquals(pair2.first, 1);
        assertEquals(pair2.second, 2);
    }

    /**
     * Test of compareTo method, of class IntPair.
     */
    @Test
    public void testCompareTo() {

        fillDatas(1, 1, 1, 1);
        assertEquals(one.compareTo(two), 0);
        assertEquals(two.compareTo(one), 0);

        fillDatas(1, 1, 1, 0);
        assertEquals(one.compareTo(two), 1);
        assertEquals(two.compareTo(one), -1);

        fillDatas(1, 0, 1, 1);
        assertEquals(one.compareTo(two), -1);
        assertEquals(two.compareTo(one), 1);

        fillDatas(0, 1, 1, 1);
        assertEquals(one.compareTo(two), -1);
        assertEquals(two.compareTo(one), 1);

        fillDatas(1, 1, 0, 1);
        assertEquals(one.compareTo(two), 1);
        assertEquals(two.compareTo(one), -1);
    }

    private void fillDatas(int oneFirst, int oneSecond,
            int twoFirst, int twoSecond) {
        one.first = oneFirst;
        one.second = oneSecond;
        two.first = twoFirst;
        two.second = twoSecond;
    }

}
