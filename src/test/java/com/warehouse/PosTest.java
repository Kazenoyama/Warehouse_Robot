package com.warehouse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PosTest {
    @Test
    public void testSameAxisPositions() {
        Pos pos1 = new Pos(1, 1);
        Pos pos2 = new Pos(1, 2);
        Pos pos3 = new Pos(2, 1);
        
        assertFalse(Pos.arePositionEqual(pos1, pos2)); // Same X, different Y
        assertFalse(Pos.arePositionEqual(pos1, pos3)); // Same Y, different X
    }

    @Test
    public void testNullPositions() {
        Pos pos1 = new Pos(1, 1);
        Pos nullPos = null;
        
        assertThrows(NullPointerException.class, () -> Pos.arePositionEqual(pos1, nullPos));
        assertThrows(NullPointerException.class, () -> Pos.arePositionEqual(nullPos, pos1));
        assertThrows(NullPointerException.class, () -> Pos.arePositionEqual(nullPos, nullPos));
    }
    @Test
    public void testIdenticalPositions() {
        Pos pos1 = new Pos(5, 5);
        Pos pos2 = new Pos(5, 5);
        Pos samePos = pos1;
        
        assertTrue(Pos.arePositionEqual(pos1, pos2)); // Different objects, same coordinates
        assertTrue(Pos.arePositionEqual(pos1, samePos)); // Same object reference
        assertTrue(Pos.arePositionEqual(pos1, pos1)); // Same object with itself
    }
}


