package bst;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Treetester {
    private BinarySearchTree<Integer> tree;

    @Before
    public void setUp() throws Exception {
        tree = new BinarySearchTree<>();
    }

    @After
    public void tearDown() throws Exception {
        tree = null;
    }

    @Test
    public void testAdd() {
        tree.add(1);
        tree.add(1);
        tree.add(5);
        tree.add(3);
        tree.add(4);
        assertEquals("fel add", tree.size(), 4);


    }

    @Test
    public void testPrinttree(){
        tree.add(null);
        tree.add(1);
        tree.add(9);
        tree.add(9);
        tree.add(5);
        tree.add(2);
        tree.add(6);
        tree.add(3);
        tree.add(7);
        tree.printTree();

    }

    @Test
    public void testHeight(){
		tree.add(7);
		tree.add(6);
		tree.add(3);
        tree.add(null);
        tree.add(2);
        tree.add(13);
        tree.add(14);
        assertEquals("fel height", tree.height(), 4);
    }
    @Test
    public void EmptySize(){
        tree.add(null);
        tree.add(null);
        tree.add(null);
        assertEquals("fel size", tree.size, 0);
    }

    @Test
    public void buildtree(){
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(13);
        assertEquals("fel add", tree.height(), 7);
        int befor = tree.height();
        tree.rebuild();
        assertEquals("fel rebuild", tree.height(), 3);
        int after = tree.height();
        System.out.println("The tree height when build is: " + befor + "\n and after the rebuild is: " + after);
    }


}

