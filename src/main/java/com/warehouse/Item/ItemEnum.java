package com.warehouse.Item;

public enum ItemEnum {
    FOOD(2.0),
    DRINK(1.5),
    ELECTRONICS(5.0),
    CLOTHES(1.0),
    TOYS(3.0),
    TOOLS(7.5),
    FURNITURE(20.0),
    COMPUTER(10.0),
    DENTIFRICE(0.5),
    PAIN(1.2),
    OTHER(0.0);

    private final double weight;

    // Constructor
    ItemEnum(double weight) {
        this.weight = weight;
    }

    // Getter for weight
    public double getWeight() {
        return weight;
    }
}
