package com.warehouse.Robot;

import java.util.List;

import com.warehouse.Item.Item;
import com.warehouse.Item.ItemEnum;
import com.warehouse.Map.Pos;
import com.warehouse.Map.WarehouseMap;
import com.warehouse.Storage.ItemStorageInterface;

public class Robot {

    private Pos position;
    private Item itemInHand = null;
    private int capacity;
    private final WarehouseMap map;
    private StateEnum currentState = StateEnum.IDLE;
    private Order currentOrder = null;
    private PathFinding pathFinding;
    private final Pos creationPosition;
    List<Pos> path;

    public Robot(Pos position, WarehouseMap map, int capacity) {
        this.map = map;
        this.position = position;
        this.capacity = capacity;
        this.pathFinding = new PathFinding(map);
        this.creationPosition = position;
    }

    public boolean giveOrder(Order order){
        if(currentState == StateEnum.IDLE)
        {
            this.currentOrder = order;
            this.currentState = StateEnum.PICKING;
            this.path = computeShortestPathAvecDecalage(currentOrder.storage.getPosition());
            return true;
        }
        return false;
    }

    public void update(){
        if(this.currentState == StateEnum.PICKING){
            if(isRobotInRangeOfShelf(currentOrder.storage)){
                pickItemInStorage(currentOrder.item, currentOrder.storage);
                this.currentState = StateEnum.DROPPING;
                Pos pos = currentOrder.delivery.getPosition();
                this.path = computeShortestPathAvecDecalage(currentOrder.delivery.getPosition());
                return;
            }else{
                moveTowards();
            }
        }

        if(this.currentState == StateEnum.DROPPING){
            if(isRobotInRangeOfShelf(currentOrder.delivery)){
                dropItemInHandInStorage(currentOrder.delivery);
                this.currentState = StateEnum.IDLE;
                this.currentOrder = null;
                this.path = pathFinding.computeShortestPath(this.position, creationPosition);
                return;
            }else{
                moveTowards();
            }
        }

        if(this.currentState == StateEnum.IDLE){
            moveTowards();
        }     
    }

    private List<Pos>computeShortestPathAvecDecalage(Pos target){
        Pos pos = null;
        Pos[] possiblePositions = {
            new Pos(target.x, target.y-1),
            new Pos(target.x, target.y+1),
            new Pos(target.x-1, target.y),
            new Pos(target.x+1, target.y)
        };

        for (Pos possiblePos : possiblePositions) {
            if (map.isMapPositionWalkable(possiblePos)) {
                pos = possiblePos;
                break;
            }
        }
        if(pos == null)
            throw new IllegalStateException("No walkable position found to reach target");
        return pathFinding.computeShortestPath(this.position, pos);
    }

    private void moveTowards(){
        if(path != null && path.size() > 1)
            this.position = path.remove(1); //1 because the first element is the current position in case we cannot move
    }

    public Pos getPosition() {
        return this.position;
    }

    public Item getItemInHand() {
        return this.itemInHand;
    }

    public void pickItemInStorage(ItemEnum itemName, ItemStorageInterface storage) {
        assertSingleItemType(itemName);
        ensureRobotInStorageRange(storage);
        int numberOfItemToRemove = Math.min(this.capacity, storage.getNumberOfItemInStorage(itemName));
        storage.removeItem(itemName, numberOfItemToRemove);
        this.itemInHand = new Item(itemName, 0, this.capacity);
    }

    public void dropItemInHandInStorage(ItemStorageInterface storage){
        ensureRobotInStorageRange(storage);
        int numberOfItemToDrop = Math.min(this.itemInHand.getVolume(), storage.getRemainingCapacity());

        if(numberOfItemToDrop == this.itemInHand.getVolume())
            transferAllItemsToStorage(storage);
        else
            transferItemVolume(storage, numberOfItemToDrop);
    }

    private void transferAllItemsToStorage(ItemStorageInterface storage) {
        storage.addItem(this.itemInHand);
        this.itemInHand = null;
    }

    private void transferItemVolume(ItemStorageInterface storage, int numberOfItemToDrop) {
        storage.addItem(new Item(this.itemInHand.getItemEnum(), 0, numberOfItemToDrop));
        this.itemInHand.setVolume(this.itemInHand.getVolume() - numberOfItemToDrop);
    }

    private void assertSingleItemType(ItemEnum itemName) {
        if(this.itemInHand != null && this.itemInHand.getItemEnum() != itemName){
            throw new IllegalArgumentException("Robot can only carry one type of item at a time");
        }
    }

    private boolean isRobotInRangeOfShelf(ItemStorageInterface storage){
        return Math.abs(this.position.x - storage.getPosition().x) <= 1 && 
               Math.abs(this.position.y - storage.getPosition().y) <= 1;
    }

    private void ensureRobotInStorageRange(ItemStorageInterface storage) {
        if(!isRobotInRangeOfShelf(storage)){
            throw new IllegalStateException("Robot is not in range of the shelf");
        }
    }
}
