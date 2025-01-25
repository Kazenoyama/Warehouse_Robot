package com.warehouse.Robot;

import com.warehouse.Item.Item;
import com.warehouse.Item.ItemEnum;
import com.warehouse.Map.Pos;
import com.warehouse.Map.WarehouseMap;
import com.warehouse.Storage.ItemStorageInterface;

public class Robot {

    private Pos position;
    private Item itemInHand = null;
    private int capacity;
    private final WarehouseMap map;

    public Robot(Pos position, WarehouseMap map, int capacity) {
        this.map = map;
        this.position = position;
        this.capacity = capacity;
    }

    public Pos getPosition() {
        return this.position;
    }

    public Item getItemInHand() {
        return this.itemInHand;
    }

    public void pickItemInStorage(ItemEnum itemName, ItemStorageInterface storage) {
        assertSingleItemType(itemName);
        ensureRobotInStorageRange(storage);
        int numberOfItemToRemove = Math.min(this.capacity, storage.getNumberOfItemInStorage(itemName));
        storage.removeItem(itemName, numberOfItemToRemove);
        this.itemInHand = new Item(itemName, 0, this.capacity);
    }

    public void dropItemInHandInStorage(ItemStorageInterface storage){
        ensureRobotInStorageRange(storage);
        int numberOfItemToDrop = Math.min(this.itemInHand.getVolume(), storage.getRemainingCapacity());

        if(numberOfItemToDrop == this.itemInHand.getVolume())
            transferAllItemsToStorage(storage);
        else
            transferItemVolume(storage, numberOfItemToDrop);
    }

    private void transferAllItemsToStorage(ItemStorageInterface storage) {
        storage.addItem(this.itemInHand);
        this.itemInHand = null;
    }

    private void transferItemVolume(ItemStorageInterface storage, int numberOfItemToDrop) {
        storage.addItem(new Item(this.itemInHand.getItemEnum(), 0, numberOfItemToDrop));
        this.itemInHand.setVolume(this.itemInHand.getVolume() - numberOfItemToDrop);
    }

    private void assertSingleItemType(ItemEnum itemName) {
        if(this.itemInHand != null && this.itemInHand.getItemEnum() != itemName){
            throw new IllegalArgumentException("Robot can only carry one type of item at a time");
        }
    }

    private boolean isRobotInRangeOfShelf(ItemStorageInterface storage){
        return Math.abs(this.position.x - storage.getPosition().x) <= 1 && 
               Math.abs(this.position.y - storage.getPosition().y) <= 1;
    }

    private void ensureRobotInStorageRange(ItemStorageInterface storage) {
        if(!isRobotInRangeOfShelf(storage)){
            throw new IllegalStateException("Robot is not in range of the shelf");
        }
    }
}
