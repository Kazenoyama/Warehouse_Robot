package com.warehouse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class DisplayTest {

    // @Test
    // public void isTheDisplayShowing() throws InterruptedException {
    //     JFrame frame = new JFrame("Warehouse isTheDisplayShowing");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     WarehouseMap map = new WarehouseMap(5, 5);

    //     Display display = new Display(map, 50);

    //     frame.add(display);
    //     frame.setSize(500, 500);
    //     frame.setVisible(true);

    //     assertTrue(display.isVisible());

    //     Thread.sleep(1000);
    //     frame.dispose();
    // }

    // @Test
    // public void isTheDisplayShowingWithColor() throws InterruptedException {
    //     JFrame frame = new JFrame("Warehouse isTheDisplayShowingWithColor");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     WarehouseMap map = new WarehouseMap(5, 5);

    //     Display display = new Display(map, 50);

    //     frame.add(display);
    //     frame.setSize(500, 500);
    //     frame.setVisible(true);

    //     assertTrue(display.isVisible());

    //     display.setCellColor(1, 1, Color.RED);
    //     display.setCellColor(2, 2, Color.BLUE);
    //     display.setCellColor(3, 3, Color.GREEN);

    //     Thread.sleep(1000);
    //     frame.dispose();
    // }

    // @Test
    // public void isTheDisplayClearingColorFromDisplay() throws InterruptedException {
    //     JFrame frame = new JFrame("Warehouse isTheDisplayClearingColorFromDisplay");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     WarehouseMap map = new WarehouseMap(5, 5);

    //     Display display = new Display(map, 50);

    //     frame.add(display);
    //     frame.setSize(500, 500);
    //     frame.setVisible(true);

    //     assertTrue(display.isVisible());

    //     display.setCellColor(1, 1, Color.RED);
    //     display.setCellColor(2, 2, Color.BLUE);
    //     display.setCellColor(3, 3, Color.GREEN);

    //     Thread.sleep(1000);

    //     display.clearCellColor(1, 1);
    //     display.clearCellColor(2, 2);
    //     display.clearCellColor(3, 3);

    //     Thread.sleep(1000);
    //     frame.dispose();
    // }

    // @Test
    // public void isTheDisplayShowingTheRobot() throws InterruptedException {
    //     JFrame frame = new JFrame("Warehouse isTheDisplayShowingTheRobot");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     WarehouseMap map = new WarehouseMap(5, 5);
    //     Robot robot = new Robot(new Pos(2, 2), map);

    //     Display display = new Display(map, 50);

    //     frame.add(display);
    //     frame.setSize(500, 500);
    //     frame.setVisible(true);

    //     assertTrue(display.isVisible());

    //     display.displayRobot(robot);

    //     Thread.sleep(1000);
    //     frame.dispose();
    // }

    // @Test
    // public void isTheDisplayShowingTheRobotMoving() throws InterruptedException {
    //     JFrame frame = new JFrame("Warehouse isTheDisplayShowingTheRobotMoving");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     WarehouseMap map = new WarehouseMap(5, 5);
    //     Robot robot = new Robot(new Pos(2, 2), map);

    //     Display display = new Display(map, 50);

    //     frame.add(display);
    //     frame.setSize(500, 500);
    //     frame.setVisible(true);

    //     assertTrue(display.isVisible());

    //     display.displayRobot(robot);

    //     Thread.sleep(1000);

    //     robot.getPosition().x = 0;
    //     robot.getPosition().y = 0;

    //     display.updateDisplayForRobotMoving();

    //     Thread.sleep(1000);
    //     frame.dispose();
    // }

    @Test
    public void DisplayingMultipleRobotMovingInTheSquare() throws InterruptedException{
        JFrame frame = new JFrame("Warehouse DisplayingMultipleRobotMovingInTheSquare");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WarehouseMap map = new WarehouseMap(5, 5);
        Robot robot1 = new Robot(new Pos(2, 2), map);
        Robot robot2 = new Robot(new Pos(3, 3), map);
        Robot robot3 = new Robot(new Pos(4, 4), map);

        Display display = new Display(map, 50);

        frame.add(display);
        frame.setSize(500, 500);
        frame.setVisible(true);

        assertTrue(display.isVisible());

        display.displayRobot(robot1);
        display.displayRobot(robot2);
        display.displayRobot(robot3);

        Thread.sleep(1000);

        robot1.getPosition().x = 0;
        robot1.getPosition().y = 4;

        robot2.getPosition().x = 3;
        robot2.getPosition().y = 1;

        robot3.getPosition().x = 1;
        robot3.getPosition().y = 4;

        display.updateDisplayForRobotMoving();

        Thread.sleep(1000);
        frame.dispose();
    }

    // @Test
    // public void isTheDisplayShowingTheImage() throws InterruptedException {
    //     JFrame frame = new JFrame("Warehouse isTheDisplayShowingTheImage");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     WarehouseMap map = new WarehouseMap(5, 5);
    //     Display display = new Display(map, 50);
    //     frame.add(display);
    //     frame.setSize(500, 500);
    //     frame.setVisible(true);
    //     assertTrue(display.isVisible());
    //     Image image = new ImageIcon("robot.png").getImage();
    //     display.addImageToPanel(image);
    //     Thread.sleep(2000);
    //     frame.dispose();
    // }

}
