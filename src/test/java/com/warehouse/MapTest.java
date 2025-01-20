package com.warehouse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MapTest {
    @Test
    public void atInitMap_ShouldBeFullOfPathTileAndWithTheCorrectSize(){
        Map map = new Map(10, 10);
        int tileCounter = 0;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                assertEquals(TileEnum.PATH, map.getTyleType(i, j));
                tileCounter++;
            }
        }
        assertEquals(100, tileCounter);

        Map map2 = new Map(20, 20);
        tileCounter = 0;
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                assertEquals(TileEnum.PATH, map2.getTyleType(i, j));
                tileCounter++;
            }
        }
        assertEquals(400, tileCounter);
    }

    @Test
    public void whenATyleTypeIsChanged_ShouldBeChanged(){
        Map map = new Map(10, 10);
        map.changeTyleType(0, 0, TileEnum.SHELF);
        assertEquals(TileEnum.SHELF, map.getTyleType(0, 0));
    }

    @Test
    public void whenChangingTileTypeOfARow_ShouldChangeAllTilesOfThatRow(){
        Map map = new Map(10, 10);
        Row row = new Row(new Pos(0, 0), new Pos(0, 9));
        map.changeTyleTipeOfRow(row, TileEnum.SHELF);
        for (int i = 0; i < 10; i++){
            assertEquals(TileEnum.SHELF, map.getTyleType(0, i));
        }

        Row row2 = new Row(new Pos(0, 0), new Pos(9, 0));
        map.changeTyleTipeOfRow(row2, TileEnum.SHELF);
        for (int i = 0; i < 10; i++){
            assertEquals(TileEnum.SHELF, map.getTyleType(i, 0));
        }
    }

    @Test
    public void testIsPositionValid() {
        Map map = new Map(10, 10);

        assertTrue(map.isPositionValid(new Pos(0, 0)));
        
        assertFalse(map.isPositionValid(new Pos(-1, 5))); // Invalid x negative
        assertFalse(map.isPositionValid(new Pos(5, -1))); // Invalid y negative
        assertFalse(map.isPositionValid(new Pos(10, 5))); // Invalid x too large
        assertFalse(map.isPositionValid(new Pos(5, 10))); // Invalid y too large
        assertFalse(map.isPositionValid(new Pos(-1, -1))); // Both negative
        assertFalse(map.isPositionValid(new Pos(10, 10))); // Both too large
    }

    @Test
public void changeTyleTipeOfRow_WithInvalidPositions_ShouldThrowException() {
    Map map = new Map(10, 10);
    
    // Test invalid start position
    Row invalidRow1 = new Row(new Pos(-1, 0), new Pos(5, 0));
    assertThrows(IllegalArgumentException.class, () -> {
        map.changeTyleTipeOfRow(invalidRow1, TileEnum.SHELF);
    });
    }
}
