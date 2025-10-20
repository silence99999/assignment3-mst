package mst.model;

import mst.algorithms.UnionFind;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class UnionFindTest {

    @Test
    @DisplayName("UnionFind initialization")
    public void testInitialization() {
        UnionFind uf = new UnionFind(5);

        assertEquals(0, uf.find(0));
        assertEquals(1, uf.find(1));
        assertEquals(2, uf.find(2));
        assertEquals(3, uf.find(3));
        assertEquals(4, uf.find(4));
    }

    @Test
    @DisplayName("Union operation")
    public void testUnion() {
        UnionFind uf = new UnionFind(5);

        assertTrue(uf.union(0, 1));
        assertEquals(uf.find(0), uf.find(1));
    }

    @Test
    @DisplayName("Union of same set returns false")
    public void testUnionSameSet() {
        UnionFind uf = new UnionFind(5);

        uf.union(0, 1);
        assertFalse(uf.union(0, 1));
    }

    @Test
    @DisplayName("Path compression works")
    public void testPathCompression() {
        UnionFind uf = new UnionFind(5);

        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(2, 3);

        int root = uf.find(3);
        assertEquals(root, uf.find(0));
        assertEquals(root, uf.find(1));
        assertEquals(root, uf.find(2));
    }

    @Test
    @DisplayName("Operation count tracking")
    public void testOperationCount() {
        UnionFind uf = new UnionFind(4);

        int initialOps = uf.getOperations();
        uf.union(0, 1);
        int afterUnion = uf.getOperations();

        assertTrue(afterUnion > initialOps);
    }

    @Test
    @DisplayName("Reset operations")
    public void testResetOperations() {
        UnionFind uf = new UnionFind(4);

        uf.union(0, 1);
        uf.union(1, 2);
        assertTrue(uf.getOperations() > 0);

        uf.resetOperations();
        assertEquals(0, uf.getOperations());
    }

    @Test
    @DisplayName("Multiple unions create correct structure")
    public void testMultipleUnions() {
        UnionFind uf = new UnionFind(6);

        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);

        assertEquals(uf.find(0), uf.find(1));
        assertEquals(uf.find(2), uf.find(3));
        assertEquals(uf.find(4), uf.find(5));

        assertNotEquals(uf.find(0), uf.find(2));
        assertNotEquals(uf.find(0), uf.find(4));
        assertNotEquals(uf.find(2), uf.find(4));
    }
}