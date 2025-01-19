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
}
