package com.warehouse;

import com.warehouse.Item.ItemEnum;
import com.warehouse.Item.Item;
import com.warehouse.Robot.Order;
import com.warehouse.Robot.Robot;
import com.warehouse.Storage.ItemStorageInterface;
import com.warehouse.Storage.infiniteStorageSize;
import com.warehouse.Storage.ItemShelf;
import com.warehouse.Map.Pos;
import com.warehouse.Map.WarehouseMap;
import com.warehouse.GameEngine;    

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecisionMakerTest {

    @Test
    public void atInitialisation(){

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
        DecisionMaker decisionMaker = new DecisionMaker(game);
        assertNotNull(decisionMaker);
        assertEquals(game, decisionMaker.getGame());
    }

    @Test
    public void createRobotTest(){
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
        DecisionMaker decisionMaker = new DecisionMaker(game);
        decisionMaker.createRobot();
        assertEquals(1, game.getListRobot().size());
    }

    @Test
    public void giveTaskDeleveryTest(){
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
        DecisionMaker decisionMaker = new DecisionMaker(game);

        game.instantiateShelfWithItem();
        game.randomOrder();
        decisionMaker.giveTaskDelevery();

        assertEquals(1, game.getListRobot().size());
        assertEquals(1, game.pendingRobotOrder.size());
    }

    
    
}
