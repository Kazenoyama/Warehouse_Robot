package com.warehouse;

import java.util.ArrayList;

public class Robot {

    private Pos position;
    private final WarehouseMap map;

    public Robot(Pos position, WarehouseMap map) {
        this.position = position;
        this.map = map;
    }

    public ArrayList<Pos> computePossibleMoves() {
        final int xPos = this.position.x;
        final int yPos = this.position.y;
        
        final Pos[] possibleMoves = {
            new Pos(xPos-1, yPos),
            new Pos(xPos+1, yPos),
            new Pos(xPos, yPos-1),
            new Pos(xPos, yPos+1)
        }; 

        ArrayList<Pos> validMoves = new ArrayList<Pos>();
        validMoves.add(this.position);
        for (Pos move : possibleMoves) {
            if(isMapPositionWalkable(move)) {
                validMoves.add(move);
            }
        }

        return validMoves;
    }

    private boolean isMapPositionWalkable(Pos pos) {
        return map.isPositionValid(pos) && map.getTileType(pos.x, pos.y) == TileEnum.PATH;
    }

    public Pos getPosition() {
        return this.position;
    }
}
