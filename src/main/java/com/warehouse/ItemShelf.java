package com.warehouse;

import java.util.ArrayList;

public class ItemShelf implements ItemStorageInterface{

    private ArrayList<Item> itemList = new ArrayList<>();

    public ItemShelf() {
    }

    public void addItem(Item item){
        int index = getItemIndex(item.getItemEnum());

        if(!isEmpty() && index == -1){
            throw new IllegalArgumentException("cannot store two different type of items");
        }

        if(index != -1){
            Item itemInList = itemList.get(index);
            itemInList.setVolume(itemInList.getVolume() + item.getVolume());
        }else{
            itemList.add(item);
        }
    }

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

    private int getItemIndex(ItemEnum item){
        for(int i = 0; i < itemList.size(); i++){
            if(itemList.get(i).getItemEnum().equals(item)){
                return i;
            }
        }
        return -1;
    }
}
