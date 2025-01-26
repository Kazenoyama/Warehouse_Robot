// package com.warehouse;

// import org.junit.jupiter.api.Test;

// import com.warehouse.Map.Pos;
// import com.warehouse.Robot.Robot;
// import com.warehouse.Item.*;

// import static org.junit.jupiter.api.Assertions.*;

// import java.util.List;

// public class GameEngineTest {

//     @Test
//     public void givenAMapTilesArrayWhenGameEngineIsCreatedThenTheWarehouseMapIsCreated_AndDisplay() throws InterruptedException {
//         Integer[][] mapTiles = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1},{1, 3, 2}};
//         GameEngine gameEngine = new GameEngine(mapTiles);
//         assertNotNull(gameEngine.getWarehouseMap());

//         //Thread.sleep(2000);
//         gameEngine.disposeOfJFrame();
        
//     }

//     @Test
//     public void shouldWeHadARobotInListRobot_itAppearOnJPanel_ThenWeDeleteIt() throws InterruptedException {
//         Integer[][] mapTiles = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1},{1, 3, 2}};
//         GameEngine gameEngine = new GameEngine(mapTiles);
//         Robot robot = new Robot(new Pos(0, 0), gameEngine.getWarehouseMap(), 100);
//         gameEngine.addRobot(robot);
//         assertNotNull(gameEngine.getListRobot());
//         //Thread.sleep(2000);
//         robot.getPosition().x -= 1;
//         robot.getPosition().y -= 0;
//         gameEngine.updateRobotPosition();

//         // gameEngine.removeRobot(robot);
//         // assertEquals(0, gameEngine.getListRobot().size());
//         //Thread.sleep(2000);
//         gameEngine.disposeOfJFrame();
//     }

//     @Test
//     public void robotMovingAroundTheWarehouse() throws InterruptedException {
//         Integer[][] mapTiles = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1},{1, 3, 2}};
//         GameEngine gameEngine = new GameEngine(mapTiles);
//         Robot robot = new Robot(new Pos(0, 0), gameEngine.getWarehouseMap(), 100);
//         gameEngine.addRobot(robot);
//         assertNotNull(gameEngine.getListRobot());

//         //Thread.sleep(1000);
//         robot.getPosition().x = 0;
//         robot.getPosition().y = 1;
//         gameEngine.updateRobotPosition();
//         //Thread.sleep(1000);
//         robot.getPosition().x = 0;
//         robot.getPosition().y = 2;
//         gameEngine.updateRobotPosition();
//         //Thread.sleep(1000);
//         robot.getPosition().x = 1;
//         robot.getPosition().y = 2;
//         gameEngine.updateRobotPosition();
//         //Thread.sleep(1000);
//         robot.getPosition().x = 2;
//         robot.getPosition().y = 2;
//         gameEngine.updateRobotPosition();
//         //Thread.sleep(1000);
//         gameEngine.disposeOfJFrame();
//     }

//     @Test
//     public void cantAddMoreThanOneRobotOnTheSamePosition() throws InterruptedException {
//         Integer[][] mapTiles = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1},{1, 3, 2}};
//         GameEngine gameEngine = new GameEngine(mapTiles);
//         Robot robot = new Robot(new Pos(0, 0), gameEngine.getWarehouseMap(), 100);
//         gameEngine.addRobot(robot);
//         assertNotNull(gameEngine.getListRobot());

//         Robot robot2 = new Robot(new Pos(0, 0), gameEngine.getWarehouseMap(), 100);
//         assertFalse(gameEngine.addRobot(robot2));
//         assertEquals(1, gameEngine.getListRobot().size());
//         //Thread.sleep(2000);
//         gameEngine.disposeOfJFrame();
//     }

//     @Test
//     public void createListOfShelf(){
//         Integer[][] mapTiles = {{1, 1, 1}, {1, 2, 4}, {1, 1, 1},{1, 3, 2}};
//         GameEngine gameEngine = new GameEngine(mapTiles);
//         assertEquals(10, gameEngine.getListShelf().size());
//         assertEquals(new Pos(0,0), gameEngine.getListShelf().get(0).getPosition());
//         assertEquals(new Pos(0,1), gameEngine.getListShelf().get(1).getPosition());
//         assertEquals(new Pos(0,2), gameEngine.getListShelf().get(2).getPosition());
//         assertEquals(new Pos(1,0), gameEngine.getListShelf().get(3).getPosition());
//         assertEquals(new Pos(1,2), gameEngine.getListShelf().get(4).getPosition());
//         assertEquals(new Pos(2,0), gameEngine.getListShelf().get(5).getPosition());
//         assertEquals(new Pos(2,1), gameEngine.getListShelf().get(6).getPosition());
//         assertEquals(new Pos(2,2), gameEngine.getListShelf().get(7).getPosition());
//         assertEquals(new Pos(3,0), gameEngine.getListShelf().get(8).getPosition());
//         assertEquals(new Pos(3,1), gameEngine.getListShelf().get(9).getPosition());
//     }

//     @Test
//     public void createCommandList_StoreItInGameEngine(){
//         Integer[][] mapTiles = {{1, 1, 1}, {1, 2, 4}, {1, 1, 1},{1, 3, 2}};
//         GameEngine gameEngine = new GameEngine(mapTiles);

//         List<ItemEnum> item = List.of(ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS);
//         List<Integer> quantity = List.of(2, 3, 4);
//         gameEngine.addCommand(item, quantity);
//         assertEquals(2, gameEngine.getCommandList().get(0).get(ItemEnum.FOOD));
//         assertEquals(3, gameEngine.getCommandList().get(0).get(ItemEnum.DRINK));
//         assertEquals(4, gameEngine.getCommandList().get(0).get(ItemEnum.ELECTRONICS));
//     }

//     @Test
//     public void addMultipleCommand_removeOneOfThemInParticular(){
//         Integer[][] mapTiles = {{1, 1, 1}, {1, 2, 4}, {1, 1, 1},{1, 3, 2}};
//         GameEngine gameEngine = new GameEngine(mapTiles);

//         List<ItemEnum> item = List.of(ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS);
//         List<Integer> quantity = List.of(2, 3, 4);
//         gameEngine.addCommand(item, quantity);

//         List<ItemEnum> item2 = List.of(ItemEnum.FOOD, ItemEnum.DRINK, ItemEnum.ELECTRONICS);
//         List<Integer> quantity2 = List.of(2, 3, 4);
//         gameEngine.addCommand(item2, quantity2);

//         assertEquals(2, gameEngine.getCommandList().get(0).get(ItemEnum.FOOD));
//         assertEquals(3, gameEngine.getCommandList().get(0).get(ItemEnum.DRINK));
//         assertEquals(4, gameEngine.getCommandList().get(0).get(ItemEnum.ELECTRONICS));

//         assertEquals(2, gameEngine.getCommandList().get(1).get(ItemEnum.FOOD));
//         assertEquals(3, gameEngine.getCommandList().get(1).get(ItemEnum.DRINK));
//         assertEquals(4, gameEngine.getCommandList().get(1).get(ItemEnum.ELECTRONICS));

//         gameEngine.removeCommand(0);

//         assertEquals(1, gameEngine.getCommandList().size());
//         assertEquals(2, gameEngine.getCommandList().get(0).get(ItemEnum.FOOD));
//         assertEquals(3, gameEngine.getCommandList().get(0).get(ItemEnum.DRINK));
//         assertEquals(4, gameEngine.getCommandList().get(0).get(ItemEnum.ELECTRONICS));
//     }



    
// }
