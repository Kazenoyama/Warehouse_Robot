package com.warehouse.ItemTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.warehouse.Item.ItemEnum;
import com.warehouse.Item.ItemCommandList;

import java.util.List;
import java.util.Map;

public class ItemCommandListTest {
    
    @Test
    public void commandListCreated_ContainTheRightItemWithTheRightQuantity() {
        List<ItemEnum> item = List.of(ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS);
        List<Integer> quantity = List.of(2, 3, 4);
        Map<ItemEnum, Integer> commandList = ItemCommandList.ItemCommandListCreate(item, quantity);
        assertEquals(2, commandList.get(ItemEnum.FOOD));
        assertEquals(3, commandList.get(ItemEnum.DRINK));
        assertEquals(4, commandList.get(ItemEnum.ELECTRONICS));
    }

}
