package com.warehouse;

public class Item {

    private String name;
    private int weight;
    private int volume;

    public Item(String name, int weight, int volume){
        this.name = name;
        this.weight = weight;
        this.volume = volume;
    }

    public String getName(){
        return name;
    }

    public int getWeight(){
        return weight;
    }

    public int getVolume(){
        return volume;
    }
    
}
