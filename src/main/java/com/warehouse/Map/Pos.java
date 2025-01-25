package com.warehouse.Map;

public class Pos {
    public int x;
    public int y;

    public Pos(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == null || obj == null){
            throw new NullPointerException("One or both objects are null");
        }

        if(obj.getClass() == this.getClass()){
            Pos other = (Pos) obj;
            return x == other.x && y == other.y;
        }

        if (obj instanceof Pos) {
            Pos other = (Pos) obj;
            return x == other.x && y == other.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
