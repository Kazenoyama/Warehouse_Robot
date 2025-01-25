package com.warehouse;

import java.util.List;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameEngine {

    private WarehouseMap warehouseMap;
    private List<Robot> ListRobot;
    private  Integer[][] mapTiles;
    private Display display;
    private JFrame frame;

    public GameEngine(Integer[][] mapTiles){
        this.mapTiles = mapTiles;
        this.ListRobot = new ArrayList<>();
        
        createWarehouseMap();
        createJFramePanel_And_Display();
        giveTilesCorrectType();
        this.display.updateMapDisplayWithColor();
        
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
                        break;
                    case 2:
                        this.warehouseMap.changeTileType(i, j, TileEnum.WALL);
                        break;
                    case 3:
                        this.warehouseMap.changeTileType(i, j, TileEnum.STORAGE);
                        break;
                    case 4:
                        this.warehouseMap.changeTileType(i, j, TileEnum.DELIVERY);
                        break;
                    default:
                        this.warehouseMap.changeTileType(i, j, TileEnum.WALL);
                        break;
                }
            }
        }  
    }

    public void addRobot(Robot robot){
        this.ListRobot.add(robot);
        this.display.displayRobot(robot);
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


    
}
