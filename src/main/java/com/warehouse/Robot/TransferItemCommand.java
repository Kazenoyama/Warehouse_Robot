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

    @Override
    public void execute() {
        Order order = new Order(item, originStorage, destinationStorage);
        robot.giveOrder(order);
    }
    
}
