package com.warehouse.MapTest;

import org.junit.jupiter.api.Test;

import com.warehouse.TestUtils;
import com.warehouse.Map.Pos;
import com.warehouse.Map.Row;
import com.warehouse.Map.TileEnum;
import com.warehouse.Map.WarehouseMap;
import com.warehouse.Robot.Robot;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class WarehouseMapTest {
    @Test
    public void atInitMap_ShouldBeFullOfPathTileAndWithTheCorrectSize(){
        WarehouseMap map = new WarehouseMap(10, 10);
        int tileCounter = 0;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                assertEquals(TileEnum.PATH, map.getTileType(i, j));
                tileCounter++;
            }
        }
        assertEquals(100, tileCounter);

        WarehouseMap map2 = new WarehouseMap(20, 20);
        tileCounter = 0;
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                assertEquals(TileEnum.PATH, map2.getTileType(i, j));
                tileCounter++;
            }
        }
        assertEquals(400, tileCounter);
    }

    @Test
    public void whenATileTypeIsChanged_ShouldBeChanged(){
        WarehouseMap map = new WarehouseMap(10, 10);
        map.changeTileType(0, 0, TileEnum.SHELF);
        assertEquals(TileEnum.SHELF, map.getTileType(0, 0));
    }

    @Test
    public void whenChangingTileTypeOfARow_ShouldChangeAllTilesOfThatRow(){
        WarehouseMap map = new WarehouseMap(10, 10);
        Row row = new Row(new Pos(0, 0), new Pos(0, 9));
        map.changeTileTipeOfRow(row, TileEnum.SHELF);
        for (int i = 0; i < 10; i++){
            assertEquals(TileEnum.SHELF, map.getTileType(0, i));
        }

        Row row2 = new Row(new Pos(0, 0), new Pos(9, 0));
        map.changeTileTipeOfRow(row2, TileEnum.SHELF);
        for (int i = 0; i < 10; i++){
            assertEquals(TileEnum.SHELF, map.getTileType(i, 0));
        }
    }

    @Test
    public void testIsPositionValid() {
        WarehouseMap map = new WarehouseMap(10, 10);

        assertTrue(map.isPositionValid(new Pos(0, 0)));
        
        assertFalse(map.isPositionValid(new Pos(-1, 5))); // Invalid x negative
        assertFalse(map.isPositionValid(new Pos(5, -1))); // Invalid y negative
        assertFalse(map.isPositionValid(new Pos(10, 5))); // Invalid x too large
        assertFalse(map.isPositionValid(new Pos(5, 10))); // Invalid y too large
        assertFalse(map.isPositionValid(new Pos(-1, -1))); // Both negative
        assertFalse(map.isPositionValid(new Pos(10, 10))); // Both too large
    }

    @Test
    public void changeTileTipeOfRow_WithInvalidPositions_ShouldThrowException() {
        WarehouseMap map = new WarehouseMap(10, 10);
        
        // Test invalid start position
        Row invalidRow1 = new Row(new Pos(-1, 0), new Pos(5, 0));
        assertThrows(IllegalArgumentException.class, () -> {
            map.changeTileTipeOfRow(invalidRow1, TileEnum.SHELF);
        });
    }

    
    @Test
    public void computePossibleMoves_WhenTheRobotIsInTheMiddleAndNoTileAreWalkable_RobotShouldStayInPlace(){
        WarehouseMap map = new WarehouseMap(3, 3);
        map.changeTileType(0, 1, TileEnum.SHELF);
        map.changeTileType(1, 0, TileEnum.SHELF);
        map.changeTileType(1, 2, TileEnum.SHELF);
        map.changeTileType(2, 1, TileEnum.SHELF);
        Robot robot = new Robot(new Pos(1, 1), map);
        ArrayList<Pos> expectedPossbleMoves = new ArrayList<>(Arrays.asList(new Pos(1, 1)));
        ArrayList<Pos> possibleMoves = map.computePossibleMoves(robot.getPosition());
        assertTrue(TestUtils.areTwoPositionListsEquals(expectedPossbleMoves, possibleMoves));
    }

    @Test
    public void computePossibleMoves_WhenTheRobotWhenInTheCornerOfTheMapAndAllTileArePath(){
        WarehouseMap map = new WarehouseMap(2, 2);
        ArrayList<Pos> expectedPossbleMoves = new ArrayList<>(Arrays.asList(new Pos(0,0), new Pos(0, 1), new Pos(1, 0)));
        ArrayList<Pos> possibleMoves = map.computePossibleMoves(new Pos(0, 0));
        assertTrue(TestUtils.areTwoPositionListsEquals(expectedPossbleMoves, possibleMoves)); 
    }

    @Test
    public void computePossibleMoves_WhenTheRobotWhenInTheMiddleOfTheMapAndAllTileArePath(){
        WarehouseMap map = new WarehouseMap(3, 3);
        ArrayList<Pos> expectedPossbleMoves = new ArrayList<>(Arrays.asList(new Pos(1,1), new Pos(0, 1), new Pos(1, 0), new Pos(1, 2), new Pos(2, 1)));
        ArrayList<Pos> possibleMoves = map.computePossibleMoves(new Pos(1, 1));
        assertTrue(TestUtils.areTwoPositionListsEquals(expectedPossbleMoves, possibleMoves));
    }

    @Test
    public void computePossibleMoves_WhenTheRobotWhenInTheCornerOfTheMapAndSomeTileAreNotWalkable(){
        WarehouseMap map = new WarehouseMap(2, 2);
        map.changeTileType(0, 1, TileEnum.SHELF);
        ArrayList<Pos> expectedPossbleMoves = new ArrayList<>(Arrays.asList(new Pos(0, 0),new Pos(1, 0)));
        ArrayList<Pos> possibleMoves = map.computePossibleMoves(new Pos(0, 0));
        assertTrue(TestUtils.areTwoPositionListsEquals(expectedPossbleMoves, possibleMoves));
    }
}
