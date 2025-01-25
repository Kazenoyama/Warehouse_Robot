package com.warehouse;

public class Item {

    private ItemEnum itemEnum;
    private int weight;
    private int volume;

    public Item(ItemEnum itemEnum, int weight, int volume){
        this.itemEnum = itemEnum;
        this.weight = weight;
        this.volume = volume;
    }

    public ItemEnum getItemEnum(){
        return itemEnum;
    }

    public int getWeight(){
        return weight;
    }

    public int getVolume(){
        return volume;
    }

    public int setVolume(int volume){
        return this.volume = volume;
    }
    
}
