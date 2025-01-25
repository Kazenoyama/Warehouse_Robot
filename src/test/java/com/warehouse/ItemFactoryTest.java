package com.warehouse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemFactoryTest {

    @Test
    public void createItemShouldThrowExceptionWhenNameIsNull_OrItisEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemFactory.createItem(null, 1, 1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ItemFactory.createItem("", 1, 1);
        });
    }

    @Test
    public void createItemShouldNotHaveNegativeWeightOrVolumeAtInit() {
        Item item = ItemFactory.createItem("Item", -1, -1);
        assertEquals(0, item.getWeight());
        assertEquals(0, item.getVolume());
    }

    @Test
    public void createItemEnumShouldReturnItemWithTheCorrectNumberOfItems() {
        Item item = ItemFactory.createItemEnum(ItemEnum.FOOD, 5);
        assertEquals("Food", item.getName());
        assertEquals((int) ItemEnum.FOOD.getWeight() * 5, item.getWeight());
        assertEquals(5, item.getVolume());

        item = ItemFactory.createItemEnum(ItemEnum.DRINK, 5);
        assertEquals("Drink", item.getName());
        assertEquals((int) ItemEnum.DRINK.getWeight() * 5, item.getWeight());
        assertEquals(5, item.getVolume());
    }
    
}
