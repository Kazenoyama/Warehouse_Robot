package com.warehouse;

import com.warehouse.Item.ItemEnum;
import com.warehouse.Map.Pos;
import com.warehouse.Robot.Robot;
import com.warehouse.Storage.ItemStorageInterface;

import java.util.List;
import java.util.Map;

public class DecisionMaker {

    private List<Robot> ListRobot;
    private List<ItemStorageInterface> ListShelf;
    private List<Map<ItemEnum, Integer>> commandList;
    private Pos storagePos;

    public DecisionMaker(List<Robot> ListRobot, List<ItemStorageInterface> ListShelf, List<Map<ItemEnum, Integer>> commandList, Pos storagePos){
        this.ListRobot = ListRobot;
        this.ListShelf = ListShelf;
        this.commandList = commandList;
        this.storagePos = storagePos;
    }

    private boolean askRobot(Robot robot){
        //TODO ask the robot if he is available

        return true;
    }

    public void giveTask(){
        for (Robot robot : ListRobot) {
            if(askRobot(robot) && !commandList.isEmpty()){
                //TODO give the robot the first task in the list
            }
        }
    }



    
}
