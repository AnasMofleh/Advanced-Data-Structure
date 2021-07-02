package game;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SudokuTester {
    private Sudoku mySudoku;

    @Before
    public void setUp() throws Exception {
        mySudoku = new Sudoku();
    }

    @After
    public void tearDown() throws Exception {
        mySudoku.clear();
    }

    @Test
    public void testClear() {
        mySudoku.setValue(1, 2, 3);
        mySudoku.setValue(3, 4, 5);
        mySudoku.setValue(7, 8, 9);
        mySudoku.clear();
        assertEquals("not clearing", mySudoku.getValue(1, 2), 0);
        assertEquals("not clearing", mySudoku.getValue(3, 4), 0);
        assertEquals("not clearing", mySudoku.getValue(7, 8), 0);
    }

    @Test
    public void testSettingOchGetting() {
        mySudoku.setValue(1, 2, 3);
        mySudoku.setValue(3, 4, 5);
        mySudoku.setValue(8, 7, 9);
        assertEquals("not clearing", mySudoku.getValue(1, 2), 3);
        assertEquals("not clearing", mySudoku.getValue(3, 4), 5);
        assertEquals("not clearing", mySudoku.getValue(8, 7), 9);
    }

    @Test
    public void testFindRegion() {
        mySudoku.setValue(0, 0, 9);
        assertTrue("FindDuplicate does not find the Duplicate in region",  mySudoku.FindDuplicate(1, 1, 9));
    }

    @Test
    public void testFindRow() {
        mySudoku.setValue(0, 0, 9);
        assertTrue("FindDuplicate does not find the Duplicate in row", mySudoku.FindDuplicate(0, 8, 9));
    }

    @Test
    public void testFindCol() {
        mySudoku.setValue(0, 0, 9);
        assertTrue("FindDuplicate does not find the Duplicate in col",  mySudoku.FindDuplicate(8, 0, 9));
    }

    @Test
    public void testUnsolvebleShort() {
        mySudoku.setValue(0, 0, 8);
        mySudoku.setValue(0, 1, 8);
        mySudoku.addValues(new Tuple(1, 1, 9));
        mySudoku.addValues(new Tuple(0, 0, 9));
        assertTrue("very slow sudoku", !mySudoku.solve());
    }

    @Test
    public void testUnsolvebleLong() {
        mySudoku.setValue(7, 8, 9);
        mySudoku.setValue(8, 8, 9);
        mySudoku.addValues(new Tuple(7, 8, 9));
        mySudoku.addValues(new Tuple(8, 8, 9));
        assertTrue("its ok,but we need a faster Sudoku", !mySudoku.solve());
    }

    @Test
    public void testSolve() {
        mySudoku.setValue(1, 2, 1);
        mySudoku.setValue(3, 4, 2);
        mySudoku.setValue(5, 6, 3);
        mySudoku.setValue(7, 8, 4);
        mySudoku.setValue(2, 3, 6);
        mySudoku.setValue(4, 5, 7);
        mySudoku.setValue(6, 7, 8);
        mySudoku.setValue(8, 8, 9);
        assertTrue("not Solving", mySudoku.solve());
    }

    @Test
    public void testEmptySudoku() {
        assertTrue("not Solving", mySudoku.solve());
    }
}
