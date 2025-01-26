package com.warehouse.Item;

import java.util.Map;
import java.util.HashMap;
import java.util.List;


public class ItemCommandList {

    public static Map<ItemEnum, Integer> ItemCommandListCreate(List<ItemEnum> item, List<Integer> quantity) {
        if(item.size() != quantity.size()) {
            throw new IllegalArgumentException("The size of the item list and the quantity list must be the same");
        }
        Map<ItemEnum, Integer> commandList = new HashMap<>();
        for (int i = 0; i < item.size(); i++) {
            commandList.put(item.get(i), quantity.get(i));
        }
        return commandList;
    }
    
}
