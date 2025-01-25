package com.warehouse;

public class ItemFactory {

    public static Item createItem(String name, int weight, int volume) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if(weight < 0) {weight = 0;}
        if (volume < 0) {volume = 0;}

        return new Item(name, weight, volume);
    }

    public static Item createItemEnum(ItemEnum itemEnum) {
        switch (itemEnum) {
            case FOOD:
                return new Item("Food", 1, 50);
            case DRINK:
                return new Item("Drink", 1, 100);
            case ELECTRONICS:
                return new Item("Electronics", 5, 25);
            case CLOTHES:
                return new Item("Clothes", 10, 35);
            case TOYS:
                return new Item("Toys", 1, 15);
            case TOOLS:
                return new Item("Tools", 15, 10);
            case FURNITURE:
                return new Item("Furniture", 50, 10);
            case COMPUTER:
                return new Item("Computer", 25, 5);
            case OTHER:
                return new Item("Other", 1, 50);
            default:
                throw new IllegalArgumentException("Invalid item type");
        }
    }
    
}
