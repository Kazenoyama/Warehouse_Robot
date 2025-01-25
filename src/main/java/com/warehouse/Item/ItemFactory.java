package com.warehouse.Item;

public class ItemFactory {

    public static Item createItem(ItemEnum itemEnum, int weight, int volume) {
        if(itemEnum == null) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if(weight < 0) {weight = 0;}
        if (volume < 0) {volume = 0;}

        return new Item(itemEnum, weight, volume);
    }
}
