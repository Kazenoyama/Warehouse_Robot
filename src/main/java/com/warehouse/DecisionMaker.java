package com.warehouse;

import com.warehouse.Item.ItemEnum;
import com.warehouse.Map.Pos;
import com.warehouse.Robot.Order;
import com.warehouse.Robot.Robot;
import com.warehouse.Storage.ItemStorageInterface;
import com.warehouse.Storage.infiniteStorageSize;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DecisionMaker {

    private List<Robot> ListRobot;
    private List<ItemStorageInterface> ListShelf;
    private List<Map<ItemEnum, Integer>> commandList;
    private List<List<Order>> orderList;
    private Pos storagePos;
    private Pos deliveryPos;
    private ItemStorageInterface deliveryStorage;

    public DecisionMaker(List<Robot> ListRobot, List<ItemStorageInterface> ListShelf, List<Map<ItemEnum, Integer>> commandList, Pos storagePos, Pos deliveryPos, ItemStorageInterface deliveryStorage) {
        this.ListRobot = ListRobot;
        this.ListShelf = ListShelf;
        this.commandList = commandList;
        this.storagePos = storagePos;
        this.deliveryPos = deliveryPos;
        this.deliveryStorage = deliveryStorage;
        this.orderList = new ArrayList<>();
    }

    private boolean askRobot(Robot robot){
        return true;
    }

    public void createListOrder(){
        
        for (Map<ItemEnum, Integer> command : commandList){
            List<Order> orderFromCommand = new ArrayList<>();
            for (ItemEnum item : command.keySet()){
                for (ItemStorageInterface shelf : ListShelf){
                    if(shelf.contains(item)){
                        orderFromCommand.add(new Order(item, shelf, deliveryStorage));
                        break;
                    }
                }
            }
            orderList.add(orderFromCommand);
        }
    }

    public void removeFirstElementFromOrderList(){
        orderList.remove(0);
    }

    public List<List<Order>> getOrderList(){
        return orderList;
    }



    
}
