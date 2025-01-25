package com.warehouse.Storage;

import java.util.ArrayList;

import com.warehouse.Item.Item;
import com.warehouse.Item.ItemEnum;
import com.warehouse.Map.Pos;

public abstract class AbstractShelf implements ItemStorageInterface{

    protected ArrayList<Item> itemList = new ArrayList<>();
    private Pos position;

    public AbstractShelf(Pos position) {
        this.position = position;
    }

    public abstract void addItem(Item item);

    public void removeItem(ItemEnum item, int numberOfItemToRemove){
        int index = getItemIndex(item);
        if(index == -1){
            throw new IllegalStateException("Item not found in storage");
        }
        Item itemInList = itemList.get(index);
        if(itemInList.getVolume() < numberOfItemToRemove){
            throw new IllegalStateException("Not enough item in storage");
        }
        itemInList.setVolume(itemInList.getVolume() - numberOfItemToRemove);
        if(itemInList.getVolume() == 0){
            itemList.remove(index);
        }
    }

    public int getNumberOfItemInStorage(ItemEnum item){
        int index = getItemIndex(item);
        return index == -1 ? 0 : itemList.get(index).getVolume();
    }

    public boolean contains(ItemEnum item){
        return getNumberOfItemInStorage(item) > 0;
    }

    public ArrayList<Item> getContainedItemList(){
        if(itemList.isEmpty()){
            return null;
        }
        return itemList;
    }
    public void clear(){

    }
    public boolean isEmpty(){
        return itemList.isEmpty();
    }

    public Pos getPosition(){
        return position;
    }

    protected int getItemIndex(ItemEnum item){
        for(int i = 0; i < itemList.size(); i++){
            if(itemList.get(i).getItemEnum().equals(item)){
                return i;
            }
        }
        return -1;
    }
}
