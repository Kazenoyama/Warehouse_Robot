package com.warehouse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {

    @Test
    public void givenAMapTilesArrayWhenGameEngineIsCreatedThenTheWarehouseMapIsCreated_AndDisplay() throws InterruptedException {
        Integer[][] mapTiles = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1},{1, 3, 2}};
        GameEngine gameEngine = new GameEngine(mapTiles);
        assertNotNull(gameEngine.getWarehouseMap());

        //Thread.sleep(2000);
        gameEngine.disposeOfJFrame();
        
    }

    @Test
    public void shouldWeHadARobotInListRobot_itAppearOnJPanel_ThenWeDeleteIt() throws InterruptedException {
        Integer[][] mapTiles = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1},{1, 3, 2}};
        GameEngine gameEngine = new GameEngine(mapTiles);
        Robot robot = new Robot(new Pos(0, 0), gameEngine.getWarehouseMap());
        gameEngine.addRobot(robot);
        assertNotNull(gameEngine.getListRobot());
        //Thread.sleep(2000);
        gameEngine.removeRobot(robot);
        assertEquals(0, gameEngine.getListRobot().size());
        //Thread.sleep(2000);
        gameEngine.disposeOfJFrame();
    }

    @Test
    public void robotMovingAroundTheWarehouse() throws InterruptedException {
        Integer[][] mapTiles = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1},{1, 3, 2}};
        GameEngine gameEngine = new GameEngine(mapTiles);
        Robot robot = new Robot(new Pos(0, 0), gameEngine.getWarehouseMap());
        gameEngine.addRobot(robot);
        assertNotNull(gameEngine.getListRobot());

        //Thread.sleep(1000);
        robot.getPosition().x = 0;
        robot.getPosition().y = 1;
        gameEngine.updateRobotPosition();
        //Thread.sleep(1000);
        robot.getPosition().x = 0;
        robot.getPosition().y = 2;
        gameEngine.updateRobotPosition();
        //Thread.sleep(1000);
        robot.getPosition().x = 1;
        robot.getPosition().y = 2;
        gameEngine.updateRobotPosition();
        //Thread.sleep(1000);
        robot.getPosition().x = 2;
        robot.getPosition().y = 2;
        gameEngine.updateRobotPosition();
        //Thread.sleep(1000);
        gameEngine.disposeOfJFrame();
    }

    

    
}
