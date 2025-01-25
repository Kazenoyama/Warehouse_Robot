package com.warehouse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RobotTest {
    @Test
    public void atInstanciationRobot_ShouldBePlacedAtCoordinatesAndKnowAboutTheMap() {
        Map map = new Map(5, 5);
        Robot robot = new Robot(new Pos(0, 0), map);
        Pos expectedPos = new Pos(0, 0);
        assertTrue(expectedPos.equals(robot.getPosition()));

        robot = new Robot(new Pos(1, 1), map);
        expectedPos = new Pos(1, 1);
        assertTrue(expectedPos.equals(robot.getPosition()));
    }
}