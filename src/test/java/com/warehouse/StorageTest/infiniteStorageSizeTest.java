package com.warehouse.StorageTest;

import org.junit.jupiter.api.Test;

import com.warehouse.Storage.infiniteStorageSize;
import com.warehouse.Item.Item;
import com.warehouse.Item.ItemEnum;
import com.warehouse.Map.Pos;
import com.warehouse.Storage.ItemStorageInterface;

import static org.junit.jupiter.api.Assertions.*;

public class infiniteStorageSizeTest {
    @Test
    public void testAddItems() {
        ItemStorageInterface shelf = new infiniteStorageSize(new Pos(0, 1));
        assertTrue(shelf.isEmpty());
        shelf.addItem(new Item(ItemEnum.DENTIFRICE, 0, 1));
        shelf.addItem(new Item(ItemEnum.CLOTHES, 0, 3));
        assertEquals(2, shelf.getContainedItemList().size());
        assertEquals(1, shelf.getNumberOfItemInStorage(ItemEnum.DENTIFRICE));
        assertEquals(3, shelf.getNumberOfItemInStorage(ItemEnum.CLOTHES));

        shelf.removeItem(ItemEnum.DENTIFRICE, 1);
        assertEquals(0, shelf.getNumberOfItemInStorage(ItemEnum.DENTIFRICE));
        assertEquals(1, shelf.getContainedItemList().size());
    }
}
