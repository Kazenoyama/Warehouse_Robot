package com.warehouse;

import java.util.ArrayList;

public class WarehouseMap {

    private Tile[][] map;

    WarehouseMap(int lengthAxeX, int lengthAxeY) {
        map = new Tile[lengthAxeX][lengthAxeY];
        for (int i = 0; i < lengthAxeX; i++) {
            for (int j = 0; j < lengthAxeY; j++) {
                map[i][j] = new Tile(TileEnum.PATH);
            }
        }
    }

    public TileEnum getTyleType(int i, int j) {
        return map[i][j].getType();
    }

    public void changeTyleType(int i, int j, TileEnum type) {
        map[i][j].typeofTile = type;
    }

    public void changeTyleTipeOfRow(Row row, TileEnum type) {
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

    private boolean areRowIndexValid(Row row) {
        return isPositionValid(row.getStart()) && isPositionValid(row.getEnd());
    }

    private boolean isPositionValid(Pos pos) {
        return  pos.x >= 0 && pos.x < map.length &&
                pos.y >= 0 && pos.y < map[0].length;
    }

    public int getSizeX() {
        return map.length;
    }

    public int getSizeY() {
        return map[0].length;
    }
}
