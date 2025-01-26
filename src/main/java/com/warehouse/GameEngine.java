package com.warehouse;

import java.util.List;
import javax.swing.JFrame;
import java.util.Map;
import java.util.HashMap;


import com.warehouse.Item.*;
import com.warehouse.Map.Pos;
import com.warehouse.Map.TileEnum;
import com.warehouse.Map.WarehouseMap;
import com.warehouse.Robot.Robot;
import com.warehouse.Storage.ItemStorageInterface;
import com.warehouse.Storage.infiniteStorageSize;
import com.warehouse.Storage.ItemShelf; 


import java.util.ArrayList;

public class GameEngine {

    private WarehouseMap warehouseMap;
    private List<Robot> ListRobot;
    private Integer[][] mapTiles;
    private List<ItemStorageInterface> ListShelf;
    private List<Map<ItemEnum, Integer>> commandList;
    private Display display;
    private JFrame frame;
    private Pos storagePos;
    private Pos deliveryPos;

    public GameEngine(Integer[][] mapTiles){
        initVariables(mapTiles);
        
        createWarehouseMap();
        createJFramePanel_And_Display();
        giveTilesCorrectType();
        this.display.updateMapDisplayWithColor();     
    }

    private void initVariables(Integer[][] mapTiles){
        this.mapTiles = mapTiles;
        this.ListRobot = new ArrayList<>();
        this.ListShelf = new ArrayList<>();
        this.commandList = new ArrayList<>();
    }

    private void createWarehouseMap(){
        int li = mapTiles.length;
        int col = mapTiles[0].length;
        this.warehouseMap = new WarehouseMap(li, col);
    }

    private void createJFramePanel_And_Display(){
        this.frame = new JFrame("Warehouse");
        this.display = new Display(warehouseMap, 50);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.add(this.display);
        this.frame.setSize(warehouseMap.getSizeY() * 75, warehouseMap.getSizeX() * 75);
        this.frame.setVisible(true);
    }

    private void giveTilesCorrectType(){
        for (int i = 0; i < this.warehouseMap.getSizeX(); i++){
            for (int j = 0; j < this.warehouseMap.getSizeY(); j++){
                switch (this.mapTiles[i][j]){
                    case 0:
                        this.warehouseMap.changeTileType(i, j, TileEnum.PATH);
                        break;
                    case 1:
                        this.warehouseMap.changeTileType(i, j, TileEnum.SHELF);
                        ItemStorageInterface shelf = new ItemShelf(new Pos(i,j), 10);
                        this.ListShelf.add(shelf);
                        break;
                    case 2:
                        this.warehouseMap.changeTileType(i, j, TileEnum.WALL);
                        break;
                    case 3:
                        this.warehouseMap.changeTileType(i, j, TileEnum.STORAGE);
                        ItemStorageInterface storage = new infiniteStorageSize(new Pos(i, j));
                        this.ListShelf.add(storage);
                        this.storagePos = new Pos(i, j);
                        break;
                    case 4:
                        this.warehouseMap.changeTileType(i, j, TileEnum.DELIVERY);
                        ItemStorageInterface delivery = new infiniteStorageSize(new Pos(i, j));
                        this.ListShelf.add(delivery);
                        this.deliveryPos = new Pos(i, j);
                        break;
                    default:
                        this.warehouseMap.changeTileType(i, j, TileEnum.WALL);
                        break;
                }
            }
        }  
    }

    public boolean addRobot(Robot robot){
        if(robot.getPosition() != this.storagePos){
            robot.getPosition().x = this.storagePos.x;
            robot.getPosition().y = this.storagePos.y;
        }

        for (Robot r : this.ListRobot){
            System.out.println(r.getPosition());
            if(r.getPosition().x == robot.getPosition().x && r.getPosition().y == robot.getPosition().y){
                return false;
            }
        }

        this.ListRobot.add(robot);
        this.display.displayRobot(robot);

        return true;
    }

    public void removeRobot(Robot robot){
        this.ListRobot.remove(robot);
        this.display.removeRobot(robot);
    }

    public void updateRobotPosition(){
        this.display.updateDisplayForRobotMoving();
    }

    public WarehouseMap getWarehouseMap(){
        return this.warehouseMap;
    }

    public List<Robot> getListRobot(){
        return this.ListRobot;
    }

    public boolean disposeOfJFrame(){
        this.frame.dispose();
        return true;
    }

    public void addCommand(List<ItemEnum> item, List<Integer> quantity){
        commandList.add(ItemCommandList.ItemCommandListCreate(item, quantity));
    }

    public List<Map<ItemEnum, Integer>> getCommandList(){
        return this.commandList;
    }

    public void removeCommand(int index){
        this.commandList.remove(index);
    }

    public List<ItemStorageInterface> getListShelf(){
        return this.ListShelf;
    }

    public Pos getStoragePos(){
        return this.storagePos;
    }

    public Pos getDeliveryPos(){
        return this.deliveryPos;
    }


    
}
