package com.warehouse;

import com.warehouse.Item.ItemEnum;
import com.warehouse.Item.Item;
import com.warehouse.Robot.Order;
import com.warehouse.Robot.Robot;
import com.warehouse.Storage.ItemStorageInterface;
import com.warehouse.Storage.infiniteStorageSize;
import com.warehouse.Storage.ItemShelf;
import com.warehouse.Map.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecisionMakerTest {

    @Test
    public void whenGivenAListOfCommand_TransformItInAListOfOrder(){
        
        GameEngine gameEngine = new GameEngine(new Integer[][]{{1, 1, 1}, {1, 2, 1}, {1, 1, 1},{1, 3, 2}});
        List<ItemEnum> item = List.of(ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS);
        List<Integer> quantity = List.of(2, 3, 4);
        gameEngine.addCommand(item, quantity);

        List<ItemEnum> item2 = List.of(ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS);
        List<Integer> quantity2 = List.of(2, 3, 4);
        gameEngine.addCommand(item2, quantity2);
        gameEngine.getListShelf().get(0).addItem(new Item(ItemEnum.FOOD, 1,1));
        gameEngine.getListShelf().get(1).addItem(new Item(ItemEnum.DRINK, 1,1));
        gameEngine.getListShelf().get(2).addItem(new Item(ItemEnum.ELECTRONICS,1,1));

        DecisionMaker decisionMaker = new DecisionMaker(gameEngine);
        decisionMaker.createListOrder();

        List<Order> orderList = decisionMaker.getOrderList().get(0);
        System.out.println(orderList);
        assertEquals(3, orderList.size());
        assertEquals(2, decisionMaker.getOrderList().size());
    }

    @Test
    public void removeFirstElementFromOrderList(){

        GameEngine gameEngine = new GameEngine(new Integer[][]{{1, 1, 1}, {1, 2, 1}, {1, 1, 1},{1, 3, 2}});
        List<ItemEnum> item = List.of(ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS);
        List<Integer> quantity = List.of(2, 3, 4);
        gameEngine.addCommand(item, quantity);

        List<ItemEnum> item2 = List.of(ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS);
        List<Integer> quantity2 = List.of(2, 3, 4);
        gameEngine.addCommand(item2, quantity2);
        gameEngine.getListShelf().get(0).addItem(new Item(ItemEnum.FOOD, 1,1));
        gameEngine.getListShelf().get(1).addItem(new Item(ItemEnum.DRINK, 1,1));
        gameEngine.getListShelf().get(2).addItem(new Item(ItemEnum.ELECTRONICS,1,1));

        DecisionMaker decisionMaker = new DecisionMaker(gameEngine);
        decisionMaker.createListOrder();

        decisionMaker.removeFirstElementFromOrderList();
        assertEquals(1, decisionMaker.getOrderList().size());
        
    }

    @Test
    public void giveAListOfOrderToOneRobot_WhileDoingIt_ItCantTakeAnotherOrder(){
        
        GameEngine gameEngine = new GameEngine(new Integer[][]{{1, 1, 1}, {1, 2, 1}, {1, 1, 1},{1, 3, 2}});
        List<ItemEnum> item = List.of(ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS);
        List<Integer> quantity = List.of(2, 3, 4);
        gameEngine.addCommand(item, quantity);

        List<ItemEnum> item2 = List.of(ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS);
        List<Integer> quantity2 = List.of(2, 3, 4);
        gameEngine.addCommand(item2, quantity2);
        gameEngine.getListShelf().get(0).addItem(new Item(ItemEnum.FOOD, 1,1));
        gameEngine.getListShelf().get(1).addItem(new Item(ItemEnum.DRINK, 1,1));
        gameEngine.getListShelf().get(2).addItem(new Item(ItemEnum.ELECTRONICS,1,1));

        Robot robot = new Robot(new Pos(0,0), gameEngine.getWarehouseMap(), 10);
        gameEngine.getListRobot().add(robot);
        //gameEngine.getListRobot().add(robot2);

        DecisionMaker decisionMaker = new DecisionMaker(gameEngine);
        decisionMaker.createListOrder();

        decisionMaker.attributeOrderToRobot();
        assertFalse(decisionMaker.attributeOrderToRobot());

        decisionMaker.getPendingRobotOrder().remove(robot);
        assertTrue(decisionMaker.attributeOrderToRobot());
        assertEquals(0, decisionMaker.getOrderList().size());
    }

    @Test
    public void addNewCommandFromTheGameEngine_TransformItInOrderList(){
        
        GameEngine gameEngine = new GameEngine(new Integer[][]{{1, 1, 1}, {1, 2, 1}, {1, 1, 1},{1, 3, 2}});
        List<ItemEnum> item = List.of(ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS);
        List<Integer> quantity = List.of(2, 3, 4);
        gameEngine.addCommand(item, quantity);

        List<ItemEnum> item2 = List.of(ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS);
        List<Integer> quantity2 = List.of(2, 3, 4);
        gameEngine.addCommand(item2, quantity2);
        gameEngine.getListShelf().get(0).addItem(new Item(ItemEnum.FOOD, 1,1));
        gameEngine.getListShelf().get(1).addItem(new Item(ItemEnum.DRINK, 1,1));
        gameEngine.getListShelf().get(2).addItem(new Item(ItemEnum.ELECTRONICS,1,1));

        DecisionMaker decisionMaker = new DecisionMaker(gameEngine);
        decisionMaker.createListOrder();

        gameEngine.addCommand(List.of(ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS), List.of(2, 3, 4));
        decisionMaker.addToListOrderFromCommand(gameEngine.getCommandList().get(2));
        assertEquals(3, decisionMaker.getOrderList().size());
    }
    
}
