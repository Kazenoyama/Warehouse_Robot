package com.warehouse;

public class Tile {

    private Tile[] neighbour = new Tile[4];
    public int typeofTile;

    public Tile(int type){
        this.typeofTile = type;
        for (int i = 0; i < 4; i++) {
            neighbour[i] = null;
        }
    }

    public Tile getNeighbour(int direction){
        return neighbour[direction];
    }

    public int getType(){
        return typeofTile;
    }

    public void setNeighbour(int direction, Tile neighbourTile){
        neighbour[direction] = neighbourTile;
        neighbourTile.neighbour[(direction + 2) % 4] = this;
    }

    public boolean canIWalkNextTile(Tile nextTile){
        return nextTile.getType() == 0;
    }


    
}
