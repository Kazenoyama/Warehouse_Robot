package com.warehouse.RobotTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.warehouse.Item.Item;
import com.warehouse.Item.ItemEnum;
import com.warehouse.Map.Pos;
import com.warehouse.Map.TileEnum;
import com.warehouse.Map.WarehouseMap;
import com.warehouse.Robot.Order;
import com.warehouse.Robot.Robot;
import com.warehouse.Robot.RobotCommand;
import com.warehouse.Robot.TransferItemCommand;
import com.warehouse.Storage.ItemShelf;
import com.warehouse.Storage.ItemStorageInterface;
import com.warehouse.Storage.infiniteStorageSize;

public class RobotTest {
    @Test
    public void atInstanciationRobot_ShouldBePlacedAtCoordinatesAndKnowAboutTheMap_ShouldAlsoNotHaveItem() {
        WarehouseMap map = new WarehouseMap(5, 5);
        Robot robot = new Robot(new Pos(0, 0), map, 0);
        Pos expectedPos = new Pos(0, 0);
        assertTrue(expectedPos.equals(robot.getPosition()));

        robot = new Robot(new Pos(1, 1), map, 0);
        expectedPos = new Pos(1, 1);
        assertTrue(expectedPos.equals(robot.getPosition()));
        assertNull(robot.getItemInHand());
    }

    @Test
    public void whenExchangingItemBetweenRobotAndShelf_ShouldBeAbleToPickAndDropItemWithTheGivenCapacity() {
        int robotCapacity = 1;
        Robot robot = new Robot(new Pos(0, 0), new WarehouseMap(5, 5), robotCapacity);
        ItemStorageInterface storage = new infiniteStorageSize(new Pos(0, 1));
        storage.addItem(new Item(ItemEnum.CLOTHES, 0, 2));
        robot.pickItemInStorage(ItemEnum.CLOTHES, storage);

        assertEquals(1, storage.getNumberOfItemInStorage(ItemEnum.CLOTHES));
        assertEquals(ItemEnum.CLOTHES, robot.getItemInHand().getItemEnum());
        assertEquals(1, robot.getItemInHand().getVolume());

        robot.dropItemInHandInStorage(storage);
        assertEquals(2, storage.getNumberOfItemInStorage(ItemEnum.CLOTHES));
        assertNull(robot.getItemInHand());
    }

    @Test
    public void whenExchangingItemBetweenRobotAndShelf_RobotShouldTakeAsMuschItemAsPossible(){
        int robotCapacity = 5;
        Robot robot = new Robot(new Pos(0, 0), new WarehouseMap(5, 5), robotCapacity);
        ItemStorageInterface storage = new infiniteStorageSize(new Pos(0, 1));
        storage.addItem(new Item(ItemEnum.CLOTHES, 0, 10));
        robot.pickItemInStorage(ItemEnum.CLOTHES, storage);

        assertEquals(5, storage.getNumberOfItemInStorage(ItemEnum.CLOTHES));
        assertEquals(ItemEnum.CLOTHES, robot.getItemInHand().getItemEnum());
        assertEquals(5, robot.getItemInHand().getVolume());
    }

    @Test
    public void eachRobotCanOnlyCarryOneTypeOfItem(){
        int robotCapacity = 5;
        Robot robot = new Robot(new Pos(0, 0), new WarehouseMap(5, 5), robotCapacity);
        ItemStorageInterface storage = new infiniteStorageSize(new Pos(0, 1));
        storage.addItem(new Item(ItemEnum.CLOTHES, 0, 1));
        storage.addItem(new Item(ItemEnum.FOOD, 0, 1));
        robot.pickItemInStorage(ItemEnum.CLOTHES, storage);

        assertThrows(IllegalArgumentException.class, () -> {
            robot.pickItemInStorage(ItemEnum.FOOD, storage);
        });
    }

    @Test
    public void WhenARobotDropInAShelf_ShouldOnlyPutWhatTheShelfCanHold(){
        int robotCapacity = 5;
        Robot robot = new Robot(new Pos(0, 0), new WarehouseMap(5, 5), robotCapacity);
        ItemStorageInterface storage = new ItemShelf(new Pos(0, 1), 5);
        storage.addItem(new Item(ItemEnum.CLOTHES, 0, 5));
        robot.pickItemInStorage(ItemEnum.CLOTHES, storage);

        ItemStorageInterface storage2 = new ItemShelf(new Pos(0, 1), 1);
        robot.dropItemInHandInStorage(storage2);
        assertEquals(1, storage2.getNumberOfItemInStorage(ItemEnum.CLOTHES));
    }

    @Test
    public void WhenStorageIsTooFar_ShouldThrowAnException(){
        int robotCapacity = 5;
        Robot robot = new Robot(new Pos(0, 0), new WarehouseMap(5, 5), robotCapacity);
        ItemStorageInterface storage = new infiniteStorageSize(new Pos(0, 2));
        storage.addItem(new Item(ItemEnum.CLOTHES, 0, 5));

        assertThrows(IllegalStateException.class, () -> {
            robot.pickItemInStorage(ItemEnum.CLOTHES, storage);
        });

        assertThrows(IllegalStateException.class, () -> {
            robot.dropItemInHandInStorage(storage);
        });
    }

    @Test
    public void testRobotCanPickAndDropItems_WhenShelfAreBothInRangeAtInit(){
        WarehouseMap map = new WarehouseMap(5, 5);
        map.changeTileType(0, 0, TileEnum.SHELF);
        map.changeTileType(0, 2, TileEnum.SHELF);
        ItemStorageInterface storage = new infiniteStorageSize(new Pos(0, 0));
        storage.addItem(new Item(ItemEnum.CLOTHES, 0, 5));
        ItemStorageInterface storage2 = new infiniteStorageSize(new Pos(0, 2));
        Robot robot = new Robot(new Pos(0, 1), map, 5);

        robot.giveOrder(new Order(ItemEnum.CLOTHES, storage, storage2));
        robot.update();
        assertEquals(5, robot.getItemInHand().getVolume());
        assertEquals(0, storage.getNumberOfItemInStorage(ItemEnum.CLOTHES));
        robot.update();
        assertEquals(5, storage2.getNumberOfItemInStorage(ItemEnum.CLOTHES));
    }

    @Test
    public void testRobotCanPickAndDropItems_WhenShelfAreNotInRangeAtInit(){
        WarehouseMap map = new WarehouseMap(6, 6);
        map.changeTileType(0, 0, TileEnum.SHELF);
        map.changeTileType(0, 5, TileEnum.SHELF);
        ItemStorageInterface storage = new infiniteStorageSize(new Pos(0, 0));
        storage.addItem(new Item(ItemEnum.CLOTHES, 0, 5));
        ItemStorageInterface storage2 = new infiniteStorageSize(new Pos(0, 5));
        Robot robot = new Robot(new Pos(0, 2), map, 5);

        robot.giveOrder(new Order(ItemEnum.CLOTHES, storage, storage2));
        
        //Robot is moving toward the first storage
        robot.update();
        assertEquals(new Pos(0, 1), robot.getPosition());
        

        //robot is picking the item
        robot.update();
        assertEquals(5, robot.getItemInHand().getVolume());
        assertEquals(0, storage.getNumberOfItemInStorage(ItemEnum.CLOTHES));
        
        //robot is moving toward the second storage
        robot.update();
        assertEquals(new Pos(0, 2), robot.getPosition());
        robot.update();
        assertEquals(new Pos(0, 3), robot.getPosition());
        robot.update();
        assertEquals(new Pos(0, 4), robot.getPosition());

        //robot is dropping the item
        robot.update();
        assertEquals(5, storage2.getNumberOfItemInStorage(ItemEnum.CLOTHES));
        assertEquals(null, robot.getItemInHand());

        //robot is moving back to the initial position
        robot.update();
        assertEquals(new Pos(0, 3), robot.getPosition());

        //robot is at the initial position and stay until the next order
        robot.update();
        assertEquals(new Pos(0, 2), robot.getPosition());
        robot.update();
        assertEquals(new Pos(0, 2), robot.getPosition());

        robot.giveOrder(new Order(ItemEnum.CLOTHES, storage2, storage));

        //Robot is moving toward the second storage
        robot.update();
        assertEquals(new Pos(0, 3), robot.getPosition());
        robot.update();
        assertEquals(new Pos(0, 4), robot.getPosition());
        

        //robot is picking the item
        robot.update();
        assertEquals(5, robot.getItemInHand().getVolume());
        assertEquals(0, storage2.getNumberOfItemInStorage(ItemEnum.CLOTHES));
        
        //robot is moving toward the first storage
        robot.update();
        assertEquals(new Pos(0, 3), robot.getPosition());
        robot.update();
        assertEquals(new Pos(0, 2), robot.getPosition());
        robot.update();
        assertEquals(new Pos(0, 1), robot.getPosition());

        //robot is dropping the item
        robot.update();
        assertEquals(5, storage.getNumberOfItemInStorage(ItemEnum.CLOTHES));
        assertEquals(null, robot.getItemInHand());
    }

    @Test
    public void testCommandInterface(){
        //Map creation
        WarehouseMap map = new WarehouseMap(5, 5);
        map.changeTileType(0, 0, TileEnum.SHELF);
        map.changeTileType(0, 2, TileEnum.SHELF);

        //storage creation
        ItemStorageInterface originStorage = new infiniteStorageSize(new Pos(0, 0));
        originStorage.addItem(new Item(ItemEnum.CLOTHES, 0, 5));
        //target is an empty storage
        ItemStorageInterface targetStorage = new infiniteStorageSize(new Pos(0, 2));

        Robot robot = new Robot(new Pos(0, 1), map, 5);

        //command 1 bring the item from the origin storage to the target storage
        RobotCommand command = new TransferItemCommand(robot, ItemEnum.CLOTHES, originStorage, targetStorage);

        //command 2 bring the item from the target storage to the origin storage
        RobotCommand command2 = new TransferItemCommand(robot, ItemEnum.CLOTHES, targetStorage, originStorage);

        assertTrue(command.execute()); //robot is available
        assertFalse(command2.execute()); //robot is busy and doesnt accept the task
        multipleRobotUpdate(robot, 2);
        assertEquals(5, targetStorage.getNumberOfItemInStorage(ItemEnum.CLOTHES)); //robot as unloaded the item

        assertTrue(command2.execute()); //robot is now available and accept the task
        multipleRobotUpdate(robot, 2);
        assertEquals(0, targetStorage.getNumberOfItemInStorage(ItemEnum.CLOTHES)); //no item left in the target storage
        assertEquals(5, originStorage.getNumberOfItemInStorage(ItemEnum.CLOTHES)); //robot as unloaded the item
    }

    private void multipleRobotUpdate(Robot robot, int numberOfUpdate){
        for(int i = 0; i < numberOfUpdate; i++){
            robot.update();
        }
    }

}