package com.warehouse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class DisplayTest {

    @Test
    public void isTheDisplayShowing() throws InterruptedException {
        JFrame frame = new JFrame("Warehouse isTheDisplayShowing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Display display = new Display(5, 6);

        frame.add(display);
        frame.setSize(500, 500);
        frame.setVisible(true);

        assertTrue(display.isVisible());

        Thread.sleep(2000);

        
    }

    @Test
    public void isTheDisplayShowingWithColor() throws InterruptedException {
        JFrame frame = new JFrame("Warehouse isTheDisplayShowingWithColor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Display display = new Display(6, 6);

        frame.add(display);
        frame.setSize(500, 500);
        frame.setVisible(true);

        assertTrue(display.isVisible());

        display.setCellColor(2, 3, Color.RED);

        Thread.sleep(2000);

    }

    @Test
    public void isTheDisplayShowingWithColorAndClear() throws InterruptedException {
        JFrame frame = new JFrame("Warehouse isTheDisplayShowingWithColorAndClear");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Display display = new Display(6, 6);

        frame.add(display);
        frame.setSize(500, 500);
        frame.setVisible(true);

        assertTrue(display.isVisible());

        display.setCellColor(1, 4, Color.RED);
        display.setCellColor(3, 3, Color.BLUE);

        Thread.sleep(2000);

        display.setCellColor(4, 0, Color.BLACK);

        Thread.sleep(1000);

        display.clearCellColor(1, 4);
        display.clearCellColor(3, 3);
        display.clearCellColor(4, 0);

        Thread.sleep(2000);

    }
    
}
