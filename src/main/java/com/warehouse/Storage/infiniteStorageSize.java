package com.warehouse.Storage;

import com.warehouse.Item.Item;
import com.warehouse.Map.Pos;

public class infiniteStorageSize extends AbstractShelf{

    public infiniteStorageSize(Pos position) {
        super(position);
    }

    @Override
    public void addItem(Item item){
        int index = getItemIndex(item.getItemEnum());

        if(index != -1){
            Item itemInList = itemList.get(index);
            itemInList.setVolume(itemInList.getVolume() + item.getVolume());
        }else{
            itemList.add(item);
        }
    }

    @Override
    public int getRemainingCapacity(){
        return Integer.MAX_VALUE;
    }
}
