package com.warehouse;

import java.util.HashMap;
import java.util.List;

public class PathFinding {
    private HashMap<Pos, List<Pos>> graphOfTheMap;

    public PathFinding(Map map){
        this.graphOfTheMap = computeGraphOfAMap(map);
    }

    public HashMap<Pos, List<Pos>> getGraph(Map map){
        return this.graphOfTheMap;
    }

    public HashMap<Pos, List<Pos>> computeGraphOfAMap(Map map){
        HashMap<Pos, List<Pos>> graph = new HashMap<Pos, List<Pos>>();
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                Pos pos = new Pos(i, j);
                graph.put(pos, map.computePossibleMoves(pos));
            }
        }
        return graph;
    }

}
