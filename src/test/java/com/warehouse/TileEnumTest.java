package com.warehouse;

import org.junit.jupiter.api.Test;

public class TileEnumTest {
    @Test
    public void productEnumShouldHaveCorrectValues() {
        TileEnum.PATH.toString().equals("PATH");
        TileEnum.SHELF.toString().equals("SHELF");
        TileEnum.STORAGE.toString().equals("STORAGE");
        TileEnum.DELIVERY.toString().equals("DELIVERY");
    }
}
