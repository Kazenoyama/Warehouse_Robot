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
        // WarehouseMap map = new WarehouseMap(5, 5);
        // map.changeTileType(0, 0, TileEnum.SHELF);
        // map.changeTileType(0, 2, TileEnum.SHELF);
        // ItemStorageInterface storage = new infiniteStorageSize(new Pos(0, 0));
        // storage.addItem(new Item(ItemEnum.CLOTHES, 0, 5));
        // ItemStorageInterface storage2 = new infiniteStorageSize(new Pos(0, 2));
        // Robot robot = new Robot(new Pos(2, 0), map, 5);

        

        // JFrame frame = new JFrame("Warehouse isTheDisplayShowingTheRobot");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display display = new Display(map, 50);

        // frame.add(display);
        // frame.setSize(500, 500);
        // frame.setVisible(true);

        // display.updateMapDisplayWithColor();

        // display.displayRobot(robot);
        

        // try{Thread.sleep(1000);}
        // catch(InterruptedException e){System.out.println(e);}
        // RobotCommand command = new TransferItemCommand(robot, ItemEnum.CLOTHES, storage, storage2);
        // command.execute();
        // robot.update();
        // frame.dispose();

        Integer[][] mapTile = {
            {1,0,1,0,0},
            {0,0,0,0,0},
            {3,0,0,0,4},
            {0,0,0,0,0},
            {0,0,0,0,0}
        };
        GameEngine game = new GameEngine(mapTile);
        game.addRobot(new Robot(new Pos(0,2),game.getWarehouseMap(), 1));
        game.getListShelf().get(0).addItem(new Item(ItemEnum.CLOTHES, 0, 5));
        Robot robot = game.getListRobot().get(0);
        RobotCommand command = new TransferItemCommand(robot, ItemEnum.CLOTHES, game.getListShelf().get(0), game.getDeliveryStorage());
        command.execute();

        int i = 0;
        while (i < 10){
            try{Thread.sleep(1000);
                robot.update();
                game.updateRobotPosition();
                i++;
            }
            catch(InterruptedException e){System.out.println(e);}
        }




    } 
}
