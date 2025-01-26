package com.warehouse.Robot;

import com.warehouse.Item.ItemEnum;
import com.warehouse.Storage.ItemStorageInterface;

public class TransferItemCommand implements RobotCommand {
    private Robot robot;
    private ItemEnum item;
    private ItemStorageInterface originStorage;
    private ItemStorageInterface destinationStorage;

    public TransferItemCommand(Robot robot, ItemEnum item, ItemStorageInterface originStorage, ItemStorageInterface destinationStorage) {
        this.robot = robot;
        this.item = item;
        this.originStorage = originStorage;
        this.destinationStorage = destinationStorage;
    }

    public TransferItemCommand(Robot robot, Order order) {
        this.robot = robot;
        this.item = order.item;
        this.originStorage = order.storage;
        this.destinationStorage = order.delivery;
    }

    @Override
    public boolean execute() {
        Order order = new Order(item, originStorage, destinationStorage);
        return robot.giveOrder(order);
    }
    
}
