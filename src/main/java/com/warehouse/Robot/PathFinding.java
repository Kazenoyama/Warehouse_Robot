package com.warehouse.Robot;

import java.util.HashMap;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import org.jgrapht.alg.shortestpath.AStarShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.warehouse.Map.Pos;
import com.warehouse.Map.WarehouseMap;



public class PathFinding {
    private HashMap<Pos, List<Pos>> graphOfTheMap;
    private Graph<Pos, DefaultWeightedEdge> jgraphtGraph;

    public PathFinding(WarehouseMap map){
        this.graphOfTheMap = computeGraphOfAMap(map);
        this.jgraphtGraph = convertToJGraphT();
    }

    public HashMap<Pos, List<Pos>> getGraph(WarehouseMap map){
        return this.graphOfTheMap;
    }

    public HashMap<Pos, List<Pos>> computeGraphOfAMap(WarehouseMap map){
        HashMap<Pos, List<Pos>> graph = new HashMap<Pos, List<Pos>>();
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                Pos pos = new Pos(i, j);
                if(map.isMapPositionWalkable(pos))
                    graph.put(pos, map.computePossibleMoves(pos));
            }
        }
        return graph;
    }

    private Graph<Pos, DefaultWeightedEdge> convertToJGraphT() {
        Graph<Pos, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    
        // Add all vertices first
        for (Pos pos : graphOfTheMap.keySet()) {
            graph.addVertex(pos);
        }
    
        // Add all edges
        for (Pos source : graphOfTheMap.keySet()) {
            for (Pos target : graphOfTheMap.get(source)) {
                if (!source.equals(target) && !graph.containsEdge(source, target)) {
                    DefaultWeightedEdge edge = graph.addEdge(source, target);
                    // Set weight to 1 for adjacent cells
                    graph.setEdgeWeight(edge, 1.0);
                }
            }
        }
    
        return graph;
    }

    public List<Pos> computeShortestPath(Pos start, Pos end) {
        // Manhattan distance heuristic for A*
        AStarAdmissibleHeuristic<Pos> heuristic = new AStarAdmissibleHeuristic<Pos>() {
            @Override
            public double getCostEstimate(Pos sourceVertex, Pos targetVertex) {
                return Math.abs(sourceVertex.x - targetVertex.x) + 
                       Math.abs(sourceVertex.y - targetVertex.y);
            }
        };

        AStarShortestPath<Pos, DefaultWeightedEdge> astar = 
            new AStarShortestPath<>(jgraphtGraph, heuristic);

        return astar.getPath(start, end).getVertexList();
    }

}
