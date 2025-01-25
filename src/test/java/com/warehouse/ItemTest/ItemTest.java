package com.warehouse.ItemTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.warehouse.Item.Item;
import com.warehouse.Item.ItemEnum;

public class ItemTest {

    @Test
    public void itemShouldHaveName_Weight_AndVolume() {
        Item item = new Item(ItemEnum.FOOD, 10, 10);
        assertEquals(ItemEnum.FOOD, item.getItemEnum());
        assertEquals(10, item.getWeight());
        assertEquals(10, item.getVolume());
    }
    
}
