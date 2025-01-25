package com.warehouse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    public void itemShouldHaveName_Weight_AndVolume() {
        Item item = new Item("item", 10, 10);
        assertEquals("item", item.getName());
        assertEquals(10, item.getWeight());
        assertEquals(10, item.getVolume());
    }
    
}
