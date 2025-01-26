package com.warehouse;

import javax.swing.JFrame;

import com.warehouse.Item.Item;
import com.warehouse.Item.ItemEnum;
import com.warehouse.Map.Pos;
import com.warehouse.Map.TileEnum;
import com.warehouse.Map.WarehouseMap;
import com.warehouse.Robot.Robot;
import com.warehouse.Robot.RobotCommand;
import com.warehouse.Robot.TransferItemCommand;
import com.warehouse.Storage.ItemStorageInterface;
import com.warehouse.Storage.infiniteStorageSize;

public class Main {
    public static void main(String[] args) {
        
        Integer[][] mapTile = {
            {2,2,2,2,2,2,2,2,2,2},
            {2,0,0,0,0,0,0,0,0,4},
            {2,1,1,0,0,0,2,0,2,2},
            {2,2,2,1,0,1,2,0,1,2},
            {2,1,1,0,0,1,2,0,1,2},
            {2,0,0,0,0,0,2,0,0,2},
            {2,0,0,0,0,0,0,0,1,2},
            {2,1,0,0,1,2,0,0,0,2},
            {2,1,0,0,1,2,0,0,0,3},
            {2,2,2,2,2,2,2,2,2,2}
        };
        GameEngine game = new GameEngine(mapTile);
        game.addRobot(new Robot(new Pos(0,2),game.getWarehouseMap(), 1));
        game.addRobot(new Robot(new Pos(0,2),game.getWarehouseMap(), 1));
        game.getListShelf().get(3).addItem(new Item(ItemEnum.CLOTHES, 0, 5));
        game.getListShelf().get(6).addItem(new Item(ItemEnum.FOOD, 0, 5));
        Robot robot = game.getListRobot().get(0);
        Robot robot2 = game.getListRobot().get(1);
        RobotCommand command = new TransferItemCommand(robot, ItemEnum.CLOTHES, game.getListShelf().get(3), game.getDeliveryStorage());
        RobotCommand command2 = new TransferItemCommand(robot2, ItemEnum.FOOD, game.getListShelf().get(6), game.getDeliveryStorage());
        command.execute();
        command2.execute();

        int i = 0;
        while (i < 50){
            try{Thread.sleep(200);
                robot.update();
                robot2.update();
                game.updateRobotPosition();
                i++;
            }
            catch(InterruptedException e){System.out.println(e);}
        }




    } 
}
