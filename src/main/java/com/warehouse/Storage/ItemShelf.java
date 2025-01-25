package com.warehouse.Storage;

import com.warehouse.Item.Item;

public class ItemShelf extends AbstractShelf {

    private int maxCapacity;

    public ItemShelf(int maxCapacity) {
        super();
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void addItem(Item item){
        int index = getItemIndex(item.getItemEnum());

        if(!isEmpty() && index == -1){
            throw new IllegalArgumentException("cannot store two different type of items");
        }

        if(index != -1){
            if(getRemainingCapacity() < item.getVolume()){
                throw new IllegalArgumentException("Not enough space in storage");
            }
            Item itemInList = itemList.get(index);
            itemInList.setVolume(itemInList.getVolume() + item.getVolume());
        }else{
            if(getRemainingCapacity() < item.getVolume()){
                throw new IllegalArgumentException("Not enough space in storage");
            }
            itemList.add(item);
        }
    }

    public int getRemainingCapacity(){
        int totalVolume = 0;
        for(Item item : itemList){
            totalVolume += item.getVolume();
        }
        return maxCapacity - totalVolume;
    }


}
