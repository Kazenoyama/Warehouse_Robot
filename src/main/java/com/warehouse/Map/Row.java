package com.warehouse.Map;

public class Row {
    private Pos start;
    private Pos end;

    public Row(Pos start, Pos end){
        this.start = start;
        this.end = end;
        if(isRowDiagonal()){
            throw new IllegalArgumentException("Row must be on axis only");
        }
    }

    public Pos getStart(){
        return start;
    }

    public Pos getEnd(){
        return end;
    }

    private boolean isRowDiagonal(){
        return start.x != end.x && start.y != end.y;
    }
    
}
