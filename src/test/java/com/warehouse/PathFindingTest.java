package com.warehouse;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PathFindingTest {
    @Test
    public void testTransformMapIntoGraph(){
        Map map = new Map(2, 2);
        HashMap<Pos, List<Pos>> expectedGraph = new HashMap<Pos, List<Pos>>();
        expectedGraph.put(new Pos(0, 0), Arrays.asList(new Pos(0, 0), new Pos(0, 1), new Pos(1, 0)));
        expectedGraph.put(new Pos(0, 1), Arrays.asList(new Pos(0, 1), new Pos(0, 0), new Pos(1, 1)));
        expectedGraph.put(new Pos(1, 0), Arrays.asList(new Pos(1, 0), new Pos(0, 0), new Pos(1, 1)));
        expectedGraph.put(new Pos(1, 1), Arrays.asList(new Pos(1, 1), new Pos(0, 1), new Pos(1, 0)));

        PathFinding pathFinding = new PathFinding(map);
        HashMap<Pos, List<Pos>> graph = pathFinding.getGraph(map);

        assertEqualityOfHashmapOfPositions(expectedGraph, graph);
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
