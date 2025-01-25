package com.warehouse;

public class ItemFactory {

    public static Item createItem(ItemEnum itemEnum, int weight, int volume) {
        if(itemEnum == null) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if(weight < 0) {weight = 0;}
        if (volume < 0) {volume = 0;}

        return new Item(itemEnum, weight, volume);
    }

    public static Item createItemEnum(ItemEnum itemEnum, int numberOfItem) {
        switch (itemEnum) {
            case FOOD:
                return new Item("Food", (int) itemEnum.getWeight() * numberOfItem, numberOfItem);
            case DRINK:
                return new Item("Drink", (int) itemEnum.getWeight() * numberOfItem, numberOfItem);
            case ELECTRONICS:
                return new Item("Electronics", (int) itemEnum.getWeight()* numberOfItem, numberOfItem);
            case CLOTHES:
                return new Item("Clothes", (int) itemEnum.getWeight()* numberOfItem, numberOfItem);
            case TOYS:
                return new Item("Toys", (int) itemEnum.getWeight()* numberOfItem, numberOfItem);
            case TOOLS:
                return new Item("Tools", (int) itemEnum.getWeight()* numberOfItem, numberOfItem);
            case FURNITURE:
                return new Item("Furniture", (int) itemEnum.getWeight()* numberOfItem, numberOfItem);
            case COMPUTER:
                return new Item("Computer", (int) itemEnum.getWeight()* numberOfItem, numberOfItem);
            case DENTIFRICE:
                return new Item("Dentifrice", (int) itemEnum.getWeight()* numberOfItem, numberOfItem);
            case PAIN:
                return new Item("Pain", (int) itemEnum.getWeight()* numberOfItem, numberOfItem);
            case OTHER:
                return new Item("Other", (int) itemEnum.getWeight() * numberOfItem, numberOfItem);
            default:
                throw new IllegalArgumentException("ItemEnum not found");
        }
    }
}
