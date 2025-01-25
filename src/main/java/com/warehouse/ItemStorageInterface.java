package com.warehouse;

import java.util.ArrayList;

public interface ItemStorageInterface {
    public void addItem(Item item);
    public void removeItem(ItemEnum item, int numberOfItemToRemove);
    public boolean contains(ItemEnum item);
    public ArrayList<Item> getContainedItemList();
    public int getNumberOfItemInStorage(ItemEnum item);
    public void clear();
    public boolean isEmpty();
}
