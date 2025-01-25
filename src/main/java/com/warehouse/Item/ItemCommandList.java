package com.warehouse.Item;

import java.util.Map;
import java.util.HashMap;


public class ItemCommandList {

    public static Map<ItemEnum, Integer> ItemCommandListCreate(ItemEnum[] item, int[] quantity) {
        Map<ItemEnum, Integer> commandList = new HashMap<>();
        for (int i = 0; i < item.length; i++) {
            commandList.put(item[i], quantity[i]);
        }
        return commandList;
    }
    
}
