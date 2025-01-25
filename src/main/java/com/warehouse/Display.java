package com.warehouse;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;

public class Display extends JPanel {

    private int row;
    private int col;
    private Map<Point, Color> cellColors;
    private int sizeOfCell;
    private Map<Robot, Pos> robotPositions;
    private WarehouseMap map;

    public Display(WarehouseMap map, int sizeOfCell) {
        this.map = map;
        this.row = map.getSizeX();
        this.col = map.getSizeY();
        this.cellColors = new HashMap<>();
        this.sizeOfCell = sizeOfCell;
        this.robotPositions = new HashMap<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the grid
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                Color color = cellColors.get(new Point(j, i));
                if(color != null){
                    g.setColor(color);
                    g.fillRect(j * sizeOfCell, i * sizeOfCell, sizeOfCell, sizeOfCell);
                }
                g.setColor(Color.BLACK);
                g.drawRect(j * sizeOfCell, i * sizeOfCell, sizeOfCell, sizeOfCell);
            }
        }
    }

    public void setCellColor(int row, int col, Color color){
        cellColors.put(new Point(col, row), color);
        repaint();
    }

    public void clearCellColor(int row, int col){
        cellColors.remove(new Point(col, row));
        repaint();
    }

    public void displayRobot(Robot robot){
        Pos pos = new Pos(robot.getPosition().x, robot.getPosition().y);
        robotPositions.put(robot, pos);
        setCellColor(pos.x, pos.y, Color.YELLOW);
        repaint();
    }

    public void updateDisplayForRobotMoving(){
        for (Map.Entry<Robot, Pos> entry : robotPositions.entrySet()) {
            Pos pos = entry.getValue();
            clearCellColor(pos.x, pos.y);
            updateCellWithColorDependingOnMap(pos.x, pos.y);
            Pos newPos = new Pos(entry.getKey().getPosition().x, entry.getKey().getPosition().y);
            setCellColor(newPos.x, newPos.y, Color.YELLOW);
            entry.setValue(newPos);
        }
        
        repaint();
    }

    public void removeRobot(Robot robot){
        Pos pos = robotPositions.get(robot);
        clearCellColor(pos.x, pos.y);
        updateCellWithColorDependingOnMap(pos.x, pos.y);
        robotPositions.remove(robot);
        repaint();
    }

    public Map<Robot, Pos> getRobotPositions() {
        return robotPositions;
    }

    public void updateCellWithColorDependingOnMap(int x, int y){
        TileEnum type = this.map.getTileType(x, y);
        setCellColor(x, y, type.getColor());
    }

    public void updateMapDisplayWithColor(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                TileEnum type = this.map.getTileType(i, j);
                switch (type){
                    case PATH:
                        setCellColor(i, j, Color.WHITE);
                        break;
                    case SHELF:
                        setCellColor(i, j, Color.GRAY);
                        break;
                    case WALL:
                        setCellColor(i, j, Color.BLACK);
                        break;
                    case STORAGE:
                        setCellColor(i, j, Color.GREEN);
                        break;
                    case DELIVERY:
                        setCellColor(i, j, Color.RED);
                        break;
                }
            }
        }
    }


}
