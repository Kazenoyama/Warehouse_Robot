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

    public static Item createItemEnum(ItemEnum itemEnum, int numberOfItem) {
        switch (itemEnum) {
            case FOOD:
                return new Item("Food", 1 * numberOfItem, numberOfItem);
            case DRINK:
                return new Item("Drink", 1 * numberOfItem, numberOfItem);
            case ELECTRONICS:
                return new Item("Electronics", 1 * numberOfItem, numberOfItem);
            case CLOTHES:
                return new Item("Clothes", 1 * numberOfItem, numberOfItem);
            case TOYS:
                return new Item("Toys", 1 * numberOfItem, numberOfItem);
            case TOOLS:
                return new Item("Tools", 1 * numberOfItem, numberOfItem);
            case FURNITURE:
                return new Item("Furniture", 1 * numberOfItem, numberOfItem);
            case COMPUTER:
                return new Item("Computer", 1 * numberOfItem, numberOfItem);
            case DENTIFRICE:
                return new Item("Dentifrice", 1 * numberOfItem, numberOfItem);
            case PAIN:
                return new Item("Pain", 1 * numberOfItem, numberOfItem);
            case OTHER:
                return new Item("Other", 1 * numberOfItem, numberOfItem);
            default:
                throw new IllegalArgumentException("ItemEnum not found");
        }
    }
    
}
