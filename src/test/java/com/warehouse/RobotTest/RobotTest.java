package com.warehouse.RobotTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.warehouse.Item.Item;
import com.warehouse.Item.ItemEnum;
import com.warehouse.Map.Pos;
import com.warehouse.Map.WarehouseMap;
import com.warehouse.Robot.Robot;
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
}