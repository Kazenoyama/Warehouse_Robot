package com.warehouse.ItemTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.warehouse.Item.ItemEnum;
import com.warehouse.Item.ItemCommandList;

import java.util.Map;

public class ItemCommandListTest {
    
    @Test
    public void commandListCreated_ContainTheRightItemWithTheRightQuantity() {
        ItemEnum[] item = {ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS};
        int[] quantity = {2, 3, 4};
        Map<ItemEnum, Integer> commandList = ItemCommandList.ItemCommandListCreate(item, quantity);
        assertEquals(2, commandList.get(ItemEnum.FOOD));
        assertEquals(3, commandList.get(ItemEnum.DRINK));
        assertEquals(4, commandList.get(ItemEnum.ELECTRONICS));
    }

}
