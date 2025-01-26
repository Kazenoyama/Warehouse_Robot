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

import java.util.HashMap;

public class DecisionMaker {

    private List<Robot> ListRobot;
    private List<ItemStorageInterface> ListShelf;
    private List<Map<ItemEnum, Integer>> commandList;
    private List<List<Order>> orderList;
    private Map<Robot, List<Order>> pendingRobotOrder;
    private Pos storagePos;
    private Pos deliveryPos;
    private ItemStorageInterface deliveryStorage;
    private int maxRobotCapacity;
    private GameEngine game;

    public DecisionMaker(GameEngine game){
        this.game = game;
        this.ListRobot = game.getListRobot();
        this.ListShelf = game.getListShelf();
        this.commandList = game.getCommandList();
        this.storagePos = game.getStoragePos();
        this.deliveryPos = game.getDeliveryPos();
        this.deliveryStorage = game.getDeliveryStorage();
        this.orderList = new ArrayList<>();
        this.pendingRobotOrder = new HashMap<>();
        this.maxRobotCapacity = 3;
    }

    private boolean askRobot(Robot robot){
        if(pendingRobotOrder.containsKey(robot)){
            return false;
        }
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

    public void addToListOrderFromCommand(Map<ItemEnum, Integer> command){
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

    public void removeFirstElementFromOrderList(){
        orderList.remove(0);
    }

    public boolean attributeOrderToRobot(){
        if(orderList.isEmpty()){
            return false;
        }
        if(ListRobot == null || ListRobot.isEmpty()){
            Robot robot = new Robot(new Pos(0,0), game.getWarehouseMap(), 10);
            game.addRobot(robot);
            ListRobot = game.getListRobot();
        }

        for (Robot robot : ListRobot){
            if(askRobot(robot)){
                pendingRobotOrder.put(robot, orderList.get(0));
                removeFirstElementFromOrderList();
                return true;
            }
        }
        
        if(ListRobot.size() < maxRobotCapacity){
            Robot robot = new Robot(new Pos(0,0), game.getWarehouseMap(), 10);
            game.addRobot(robot);
            ListRobot = game.getListRobot();
            pendingRobotOrder.put(robot, orderList.get(0));
            removeFirstElementFromOrderList();
            return true;
        }
        return false;
    }

    public List<Robot> getListRobot(){
        return ListRobot;
    }

    public List<List<Order>> getOrderList(){
        return orderList;
    }

    public Map<Robot, List<Order>> getPendingRobotOrder(){
        return pendingRobotOrder;
    }
    
}
