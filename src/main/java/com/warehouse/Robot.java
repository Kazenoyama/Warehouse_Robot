package com.warehouse;

public class Robot {

    private Pos position;
    private final WarehouseMap map;

    public Robot(Pos position, WarehouseMap map) {
        this.map = map;
        this.position = position;
    }

    public Pos getPosition() {
        return this.position;
    }
}
