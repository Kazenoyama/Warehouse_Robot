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
        
        //game.start();




    } 
}
