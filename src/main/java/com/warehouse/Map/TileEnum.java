package com.warehouse.Map;

import java.awt.*;

public enum TileEnum {
    PATH(Color.WHITE),
    SHELF(Color.GRAY),
    WALL(Color.BLACK),
    STORAGE(Color.GREEN),
    DELIVERY(Color.RED);

    private final Color color;

    TileEnum(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
