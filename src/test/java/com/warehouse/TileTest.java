package com.warehouse;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TileTest {

    @Test
    public void tileShouldBeTypePath(){
        Tile tile = new Tile(0);
        assertEquals(0, tile.getType());
    }

    @Test
    public void tileShouldBeTypeShelf(){
        Tile tile = new Tile(1);
        assertEquals(1, tile.getType());
    }

    @Test
    public void tileShouldBeTypeWall(){
        Tile tile = new Tile(2);
        assertEquals(2, tile.getType());
    }

    @Test
    public void tileNeighbourShouldBeEmptyAtInit(){
        Tile tile = new Tile(0);
        for (int i = 0; i < 4; i++) {
            assertNull(tile.getNeighbour(i));
        }
    }

    @Test
    public void tileNeighbourShouldBeSetHasExpected(){
        Tile tile = new Tile(0);
        Tile neighbour = new Tile(1);
        tile.setNeighbour(0, neighbour);
        assertEquals(neighbour, tile.getNeighbour(0));
    }

    @Test
    public void tileNeighbourShouldKnowOfPresentTileWhenSet(){
        Tile tile = new Tile(0);
        Tile neighbour = new Tile(1);
        tile.setNeighbour(0, neighbour);
        assertEquals(tile, neighbour.getNeighbour(2));
    }

    @Test
    public void canIAccessTileNeighbour(){
        Tile tile = new Tile(0);
        Tile pathTile = new Tile(0);
        Tile shelfTile = new Tile(1);
        Tile wallTile = new Tile(2);
        tile.setNeighbour(0, pathTile);
        tile.setNeighbour(1, wallTile);
        tile.setNeighbour(2, shelfTile);
        assertTrue(tile.canIWalkNextTile(tile.getNeighbour(0)));
        assertFalse(tile.canIWalkNextTile(tile.getNeighbour(1)));
        assertFalse(tile.canIWalkNextTile(tile.getNeighbour(2)));
    }
    
}
