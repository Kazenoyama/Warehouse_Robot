package com.warehouse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class RobotTest {
    @Test
    public void atInstanciationRobot_ShouldBePlacedAtCoordinatesAndKnowAboutTheMap() {
        Map map = new Map(5, 5);
        Robot robot = new Robot(new Pos(0, 0), map);
        Pos expectedPos = new Pos(0, 0);
        assertTrue(Pos.arePositionEqual(expectedPos, robot.getPosition()));

        robot = new Robot(new Pos(1, 1), map);
        expectedPos = new Pos(1, 1);
        assertTrue(Pos.arePositionEqual(expectedPos, robot.getPosition()));
    }

    @Test
    public void computePossibleMoves_WhenTheRobotIsInTheMiddleAndNoTileAreWalkable_RobotShouldStayInPlace(){
        Map map = new Map(3, 3);
        map.changeTileType(0, 1, TileEnum.SHELF);
        map.changeTileType(1, 0, TileEnum.SHELF);
        map.changeTileType(1, 2, TileEnum.SHELF);
        map.changeTileType(2, 1, TileEnum.SHELF);
        Robot robot = new Robot(new Pos(1, 1), map);
        ArrayList<Pos> expectedPossbleMoves = new ArrayList<>(Arrays.asList(new Pos(1, 1)));
        ArrayList<Pos> possibleMoves = robot.computePossibleMoves();
        assertPositionEqualityInArrays(expectedPossbleMoves, possibleMoves);
    }

    @Test
    public void computePossibleMoves_WhenTheRobotWhenInTheCornerOfTheMapAndAllTileArePath(){
        Map map = new Map(2, 2);
        Robot robot = new Robot(new Pos(0, 0), map);
        ArrayList<Pos> expectedPossbleMoves = new ArrayList<>(Arrays.asList(new Pos(0,0), new Pos(0, 1), new Pos(1, 0)));
        ArrayList<Pos> possibleMoves = robot.computePossibleMoves();
        assertPositionEqualityInArrays(expectedPossbleMoves, possibleMoves); 
    }

    @Test
    public void computePossibleMoves_WhenTheRobotWhenInTheMiddleOfTheMapAndAllTileArePath(){
        Map map = new Map(3, 3);
        Robot robot = new Robot(new Pos(1, 1), map);
        ArrayList<Pos> expectedPossbleMoves = new ArrayList<>(Arrays.asList(new Pos(1,1), new Pos(0, 1), new Pos(1, 0), new Pos(1, 2), new Pos(2, 1)));
        ArrayList<Pos> possibleMoves = robot.computePossibleMoves();
        assertPositionEqualityInArrays(expectedPossbleMoves, possibleMoves);
    }

    @Test
    public void computePossibleMoves_WhenTheRobotWhenInTheCornerOfTheMapAndSomeTileAreNotWalkable(){
        Map map = new Map(2, 2);
        map.changeTileType(0, 1, TileEnum.SHELF);
        Robot robot = new Robot(new Pos(0, 0), map);
        ArrayList<Pos> expectedPossbleMoves = new ArrayList<>(Arrays.asList(new Pos(0, 0),new Pos(1, 0)));
        ArrayList<Pos> possibleMoves = robot.computePossibleMoves();
        assertPositionEqualityInArrays(expectedPossbleMoves, possibleMoves);
    }


        

    private void assertPositionEqualityInArrays(ArrayList<Pos> expected, ArrayList<Pos> actual){
        int counter = 0;
        for(int i = 0; i < expected.size(); i++)
            for(int j = 0; j < actual.size(); j++)
                if(Pos.arePositionEqual(expected.get(i), actual.get(j)))
                    counter++;

        assertEquals(expected.size(), counter);
    }
}