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
    public void createItemFromEnumShouldReturnCorrectItem() {
        Item item = ItemFactory.createItemEnum(ItemEnum.FOOD);
        assertEquals("Food", item.getName());
        assertEquals(1, item.getWeight());
        assertEquals(50, item.getVolume());

        item = ItemFactory.createItemEnum(ItemEnum.DRINK);
        assertEquals("Drink", item.getName());
        assertEquals(1, item.getWeight());
        assertEquals(100, item.getVolume());

        item = ItemFactory.createItemEnum(ItemEnum.ELECTRONICS);
        assertEquals("Electronics", item.getName());
        assertEquals(5, item.getWeight());
        assertEquals(25, item.getVolume());

        item = ItemFactory.createItemEnum(ItemEnum.CLOTHES);
        assertEquals("Clothes", item.getName());
        assertEquals(10, item.getWeight());
        assertEquals(35, item.getVolume());

        item = ItemFactory.createItemEnum(ItemEnum.TOYS);
        assertEquals("Toys", item.getName());
        assertEquals(1, item.getWeight());
        assertEquals(15, item.getVolume());

        item = ItemFactory.createItemEnum(ItemEnum.TOOLS);
        assertEquals("Tools", item.getName());
        assertEquals(15, item.getWeight());
        assertEquals(10, item.getVolume());

        item = ItemFactory.createItemEnum(ItemEnum.FURNITURE);
        assertEquals("Furniture", item.getName());
        assertEquals(50, item.getWeight());
        assertEquals(10, item.getVolume());

        item = ItemFactory.createItemEnum(ItemEnum.COMPUTER);
        assertEquals("Computer", item.getName());
        assertEquals(25, item.getWeight());
        assertEquals(5, item.getVolume());

        item = ItemFactory.createItemEnum(ItemEnum.OTHER);
        assertEquals("Other", item.getName());
        assertEquals(1, item.getWeight());
        assertEquals(50, item.getVolume());
    }
    
}
