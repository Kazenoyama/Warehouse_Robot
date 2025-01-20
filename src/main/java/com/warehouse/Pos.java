package com.warehouse;

public class Pos {
    public int x;
    public int y;

    public Pos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static boolean arePositionEqual(Pos pos1, Pos pos2){
        return pos1.x == pos2.x && pos1.y == pos2.y;
    }
    
}
