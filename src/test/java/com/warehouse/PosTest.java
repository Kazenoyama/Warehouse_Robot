package com.warehouse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PosTest {
    @Test
    public void testSameAxisPositions() {
        Pos pos1 = new Pos(1, 1);
        Pos pos2 = new Pos(1, 2);
        Pos pos3 = new Pos(2, 1);
        
        assertFalse(pos1.equals(pos2)); // Same X, different Y
        assertFalse(pos1.equals(pos3)); // Same Y, different X
    }

    @Test
    public void testNullPositions() {
        Pos pos1 = new Pos(1, 1);
        Pos nullPos = null;
        
        assertThrows(NullPointerException.class, () -> pos1.equals(nullPos));
        assertThrows(NullPointerException.class, () -> nullPos.equals(pos1));
        assertThrows(NullPointerException.class, () -> nullPos.equals(nullPos));
    }
    @Test
    public void testIdenticalPositions() {
        Pos pos1 = new Pos(5, 5);
        Pos pos2 = new Pos(5, 5);
        Pos samePos = pos1;
        
        assertTrue(pos1.equals(pos2)); // Different objects, same coordinates
        assertTrue(pos1.equals(samePos)); // Same object reference
    }
}


