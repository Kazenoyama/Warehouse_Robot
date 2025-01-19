package com.warehouse;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TileTest {

    @Test
    public void tileShouldBeTypePath(){
        Tile tile = new Tile(TileEnum.PATH);
        assertEquals(TileEnum.PATH, tile.getType());
    }

    @Test
    public void tileNeighbourShouldBeEmptyAtInit(){
        Tile tile = new Tile(TileEnum.PATH);
        for (int i = 0; i < 4; i++) {
            assertNull(tile.getNeighbour(i));
        }
    }

    @Test
    public void tileShouldKnowNeighbour_NeighbourShouldKnowCurrentTile(){
        Tile tile = new Tile(TileEnum.PATH);
        Tile neighbour = new Tile(TileEnum.SHELF);
        tile.setNeighbour(0, neighbour);
        
        assertEquals(neighbour, tile.getNeighbour(0));
    }

    @Test
    public void canIAccessTileNeighbour(){
        Tile tile = new Tile(TileEnum.PATH);
        Tile pathTile = new Tile(TileEnum.PATH);
        Tile shelfTile = new Tile(TileEnum.SHELF);
        Tile wallTile = new Tile(TileEnum.STORAGE);
        tile.setNeighbour(0, pathTile);
        tile.setNeighbour(1, wallTile);
        tile.setNeighbour(2, shelfTile);
        assertTrue(tile.canIWalkNextTile(tile.getNeighbour(0)));
        assertFalse(tile.canIWalkNextTile(tile.getNeighbour(1)));
        assertFalse(tile.canIWalkNextTile(tile.getNeighbour(2)));
    }

    @Test
    public void TwobyTwoMapTileShouldKnowNeighbour(){
        Tile leftDownTile = new Tile(TileEnum.PATH);
        Tile rightDownTile = new Tile(TileEnum.STORAGE);
        Tile leftUpTile = new Tile(TileEnum.SHELF);
        Tile rightUpTile = new Tile(TileEnum.PATH);

        leftDownTile.setNeighbour(2, rightDownTile);
        leftDownTile.setNeighbour(1, leftUpTile);
        leftUpTile.setNeighbour(2, rightUpTile);
        rightDownTile.setNeighbour(1, rightUpTile);

        assertEquals(leftDownTile, rightDownTile.getNeighbour(0));
        assertEquals(leftDownTile, leftUpTile.getNeighbour(3));

        assertEquals(leftUpTile, leftDownTile.getNeighbour(1));
        assertEquals(leftUpTile, rightUpTile.getNeighbour(0));

        assertEquals(rightUpTile, leftUpTile.getNeighbour(2));
        assertEquals(rightUpTile, rightDownTile.getNeighbour(1));

        assertEquals(rightDownTile, rightUpTile.getNeighbour(3));
        assertEquals(rightDownTile, leftDownTile.getNeighbour(2));
    }
    
}
