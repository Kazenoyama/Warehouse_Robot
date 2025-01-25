package com.warehouse.MapTest;

import org.junit.jupiter.api.Test;

import com.warehouse.Map.TileEnum;

public class TileEnumTest {
    @Test
    public void productEnumShouldHaveCorrectValues() {
        TileEnum.PATH.toString().equals("PATH");
        TileEnum.SHELF.toString().equals("SHELF");
        TileEnum.STORAGE.toString().equals("STORAGE");
        TileEnum.DELIVERY.toString().equals("DELIVERY");
    }
}
