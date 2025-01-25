package com.warehouse.Storage;

import java.util.ArrayList;

import com.warehouse.Item;
import com.warehouse.ItemEnum;

/***
 * Interface for ItemStorage classes
 * Two main classes that implement this interface are ItemShelf and infiniteStorageSize
 * ItemShelf is a storage class that can contain only one type of item, and a limited amount of that item
 * infiniteStorageSize is a storage class that can contain multiple types of items and an infinite amount of each item
 */
public interface ItemStorageInterface {
    public void addItem(Item item);
    public void removeItem(ItemEnum item, int numberOfItemToRemove);
    public boolean contains(ItemEnum item);
    public ArrayList<Item> getContainedItemList();
    public int getNumberOfItemInStorage(ItemEnum item);
    public int getRemainingCapacity();
    public void clear();
    public boolean isEmpty();
}
