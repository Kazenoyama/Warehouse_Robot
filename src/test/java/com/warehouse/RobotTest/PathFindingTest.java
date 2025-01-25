package com.warehouse.RobotTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.warehouse.Map.Pos;
import com.warehouse.Map.TileEnum;
import com.warehouse.Map.WarehouseMap;
import com.warehouse.Robot.PathFinding;

public class PathFindingTest {
    @Test
    public void testTransformMapIntoGraph(){
        WarehouseMap map = new WarehouseMap(2, 2);
        HashMap<Pos, List<Pos>> expectedGraph = new HashMap<Pos, List<Pos>>();
        expectedGraph.put(new Pos(0, 0), Arrays.asList(new Pos(0, 0), new Pos(0, 1), new Pos(1, 0)));
        expectedGraph.put(new Pos(0, 1), Arrays.asList(new Pos(0, 1), new Pos(0, 0), new Pos(1, 1)));
        expectedGraph.put(new Pos(1, 0), Arrays.asList(new Pos(1, 0), new Pos(0, 0), new Pos(1, 1)));
        expectedGraph.put(new Pos(1, 1), Arrays.asList(new Pos(1, 1), new Pos(0, 1), new Pos(1, 0)));

        PathFinding pathFinding = new PathFinding(map);
        HashMap<Pos, List<Pos>> graph = pathFinding.getGraph(map);

        assertEqualityOfHashmapOfPositions(expectedGraph, graph);
    }

    @Test
    public void testTransformMapIntoGraphHarder(){
        WarehouseMap map = new WarehouseMap(3, 3);
        map.changeTileType(0, 1, TileEnum.SHELF);
        map.changeTileType(1, 1, TileEnum.SHELF);
        HashMap<Pos, List<Pos>> expectedGraph = new HashMap<Pos, List<Pos>>();
        expectedGraph.put(new Pos(0, 0), Arrays.asList(new Pos(0, 0), new Pos(1, 0)));
        expectedGraph.put(new Pos(1, 0), Arrays.asList(new Pos(1, 0), new Pos(0,0), new Pos(2, 0)));
        expectedGraph.put(new Pos(2, 0), Arrays.asList(new Pos(2, 0), new Pos(1, 0), new Pos(2, 1)));
        expectedGraph.put(new Pos(2, 1), Arrays.asList(new Pos(2, 1), new Pos(2, 0), new Pos(2, 2)));
        expectedGraph.put(new Pos(2, 2), Arrays.asList(new Pos(2, 2), new Pos(2, 1), new Pos(1, 2)));
        expectedGraph.put(new Pos(1, 2), Arrays.asList(new Pos(1, 2), new Pos(2, 2), new Pos(0, 2)));
        expectedGraph.put(new Pos(0, 2), Arrays.asList(new Pos(0, 2), new Pos(1, 2)));

        PathFinding pathFinding = new PathFinding(map);
        HashMap<Pos, List<Pos>> graph = pathFinding.getGraph(map);

        assertEqualityOfHashmapOfPositions(expectedGraph, graph);
    }

    @Test
    public void computeShortestPath(){
        WarehouseMap map = new WarehouseMap(2, 2);
        PathFinding pathFinding = new PathFinding(map);
        Pos start = new Pos(0, 0);
        Pos end = new Pos(1, 1);
        List<Pos> expectedPath = Arrays.asList(new Pos(0, 0), new Pos(1, 0), new Pos(1, 1));
        List<Pos> path = pathFinding.computeShortestPath(start, end);
        System.out.println("Path found: " + path);
        assertEquals(expectedPath, path);
    }

    @Test
    public void computeAHarderShortestPath(){
        WarehouseMap map = new WarehouseMap(3, 3);
        map.changeTileType(0, 1, TileEnum.SHELF);
        map.changeTileType(1, 1, TileEnum.SHELF);
        PathFinding pathFinding = new PathFinding(map);
        Pos start = new Pos(0, 0);
        Pos end = new Pos(0, 2);
        List<Pos> expectedPath = Arrays.asList(new Pos(0, 0), new Pos(1, 0), new Pos(2, 0), new Pos(2, 1), new Pos(2, 2), new Pos(1, 2), new Pos(0, 2));
        List<Pos> path = pathFinding.computeShortestPath(start, end);
        System.out.println("Path found: " + path);
        assertEquals(expectedPath, path);
    }

    private void assertEqualityOfHashmapOfPositions(HashMap<Pos, List<Pos>> expectedGraph, HashMap<Pos, List<Pos>> graph) {
        if(expectedGraph.size() != graph.size()){
            fail("The size of the two hashmaps is different");
        }
        for (Pos pos : expectedGraph.keySet()) {
            List<Pos> expectedList = expectedGraph.get(pos);
            List<Pos> list = graph.get(pos);
            assertTrue(expectedList.containsAll(list) && list.containsAll(expectedList));
        }
    }
}
