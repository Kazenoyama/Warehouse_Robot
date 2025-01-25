package com.warehouse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {

    @Test
    public void givenAMapTilesArrayWhenGameEngineIsCreatedThenTheWarehouseMapIsCreated_AndDisplay() throws InterruptedException {
        Integer[][] mapTiles = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1},{1, 3, 2}};
        GameEngine gameEngine = new GameEngine(mapTiles);
        assertNotNull(gameEngine.getWarehouseMap());

        Thread.sleep(2000);
        gameEngine.disposeOfJFrame();
        
    }

    @Test
    public void shouldWeHadARobotInListRobot_itAppearOnJPanel() throws InterruptedException {
        Integer[][] mapTiles = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1},{1, 3, 2}};
        GameEngine gameEngine = new GameEngine(mapTiles);
        Robot robot = new Robot(new Pos(0, 0), gameEngine.getWarehouseMap());
        gameEngine.addRobot(robot);
        assertNotNull(gameEngine.getListRobot());

        Thread.sleep(2000);
        gameEngine.disposeOfJFrame();
    }

    
}
