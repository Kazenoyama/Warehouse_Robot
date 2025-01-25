package com.warehouse.ItemTest;

import org.junit.jupiter.api.Test;

import com.warehouse.Item.Item;
import com.warehouse.Item.ItemEnum;
import com.warehouse.Item.ItemFactory;

import static org.junit.jupiter.api.Assertions.*;

public class ItemFactoryTest {

    @Test
    public void createItemShouldThrowExceptionWhenNameIsNull_OrItisEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemFactory.createItem(null, 1, 1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ItemFactory.createItem(null, 1, 1);
        });
    }

    @Test
    public void createItemShouldNotHaveNegativeWeightOrVolumeAtInit() {
        Item item = ItemFactory.createItem(ItemEnum.CLOTHES, -1, -1);
        assertEquals(0, item.getWeight());
        assertEquals(0, item.getVolume());
    }

    @Test
    public void createItemEnumShouldReturnItemWithTheCorrectNumberOfItems() {
        Item item = ItemFactory.createItem(ItemEnum.FOOD, 5, 5);
        assertEquals(ItemEnum.FOOD, item.getItemEnum());
        assertEquals(5, item.getWeight());
        assertEquals(5, item.getVolume());

        item = ItemFactory.createItem(ItemEnum.DRINK, 10, 10);
        assertEquals(ItemEnum.DRINK, item.getItemEnum());
        assertEquals(10, item.getWeight());
        assertEquals(10, item.getVolume());
    }
    
}
