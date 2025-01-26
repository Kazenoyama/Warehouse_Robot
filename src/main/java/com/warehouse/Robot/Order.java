package com.warehouse.Robot;

import com.warehouse.Item.ItemEnum;
import com.warehouse.Storage.ItemStorageInterface;

public class Order {

    public ItemEnum item;
    public ItemStorageInterface storage;
    public ItemStorageInterface delivery;

    public Order(ItemEnum item, ItemStorageInterface storage, ItemStorageInterface delivery){
        this.item = item;
        this.storage = storage;
        this.delivery = delivery;
    }
}
