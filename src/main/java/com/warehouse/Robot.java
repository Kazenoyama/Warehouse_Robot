package com.warehouse;

public class Robot {

    private Pos position;

    public Robot(Pos position, Map map) {
        this.position = position;
    }

    public Pos getPosition() {
        return this.position;
    }
}
