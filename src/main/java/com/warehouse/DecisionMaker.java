package com.warehouse;

import com.warehouse.Map.Pos;
import com.warehouse.Robot.Order;
import com.warehouse.Robot.Robot;
import com.warehouse.Robot.RobotCommand;
import com.warehouse.Robot.TransferItemCommand;

import java.util.List;
import java.util.Map;

public class DecisionMaker {

    private GameEngine game;

    public DecisionMaker(GameEngine game){
        this.game = game;   
    }

    public void giveTaskDelevery(){
        List<Robot> ListRobot = game.getListRobot();
        Map<Robot, List<Order>> pendingRobotOrder = game.pendingRobotOrder;
        List<List<Order>> orderList = game.orderList;
        createRobot();
        if(orderList.isEmpty()){
            return;
        }
        else{
            for (Robot robot : ListRobot){
                if(!pendingRobotOrder.containsKey(robot) && !orderList.isEmpty()){
                    game.pendingRobotOrder.put(robot, orderList.get(0));
                    RobotCommand command = new TransferItemCommand(robot, orderList.get(0).get(0));
                    command.execute();
                    orderList.remove(0);    
                }
            }
        }
    }

    public void createRobot(){
        if(game.getListRobot().size() < game.maximumRobot){
            Robot robot = new Robot(new Pos(1,1), game.getWarehouseMap(), 1);
            game.addRobot(robot);
        }
    }

    public GameEngine getGame(){
        return game;
    }

}
