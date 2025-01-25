package com.warehouse;

import java.util.ArrayList;

public class Map {

    private Tile[][] map;

    Map(int lengthAxeX, int lengthAxeY) {
        map = new Tile[lengthAxeX][lengthAxeY];
        for (int i = 0; i < lengthAxeX; i++) {
            for (int j = 0; j < lengthAxeY; j++) {
                map[i][j] = new Tile(TileEnum.PATH);
            }
        }
    }

    public int getHeight() {
        return map.length;
    }

    public int getWidth() {
        return map[0].length;
    }

    public TileEnum getTileType(int i, int j) {
        return map[i][j].getType();
    }

    public void changeTileType(int i, int j, TileEnum type) {
        map[i][j].typeofTile = type;
    }

    public void changeTileTipeOfRow(Row row, TileEnum type) {
        if(!areRowIndexValid(row)){
            throw new IllegalArgumentException("Row index out of bounds");
        }
        Pos start = row.getStart();
        Pos end = row.getEnd();

        if (start.x == end.x) {
            for (int i = start.y; i <= end.y; i++) {
                map[start.x][i].typeofTile = type;
            }
        } else {
            for (int i = start.x; i <= end.x; i++) {
                map[i][start.y].typeofTile = type;
            }
        }
    }

    public boolean areRowIndexValid(Row row) {
        return isPositionValid(row.getStart()) && isPositionValid(row.getEnd());
    }

    public boolean isPositionValid(Pos pos) {
        return  pos.x >= 0 && pos.x < map.length &&
                pos.y >= 0 && pos.y < map[0].length;
    }

    public ArrayList<Pos> computePossibleMoves(Pos position) {
        final int xPos = position.x;
        final int yPos = position.y;
        
        final Pos[] possibleMoves = {
            new Pos(xPos-1, yPos),
            new Pos(xPos+1, yPos),
            new Pos(xPos, yPos-1),
            new Pos(xPos, yPos+1)
        }; 

        ArrayList<Pos> validMoves = new ArrayList<Pos>();
        validMoves.add(position);
        for (Pos move : possibleMoves) {
            if(isMapPositionWalkable(move)) {
                validMoves.add(move);
            }
        }

        return validMoves;
    }

    private boolean isMapPositionWalkable(Pos pos) {
        return this.isPositionValid(pos) && this.getTileType(pos.x, pos.y) == TileEnum.PATH;
    }
}
