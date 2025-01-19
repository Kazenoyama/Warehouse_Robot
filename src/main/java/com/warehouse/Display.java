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

    public Display(WarehouseMap map, int sizeOfCell) {
        this.row = map.getSizeX();
        this.col = map.getSizeY();
        this.cellColors = new HashMap<>();
        this.sizeOfCell = sizeOfCell;
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

    // public Color chooseColorDependingTile(Tile tile){
    //     switch(tile.getType()){
    //         case 0: 
    //             return Color.WHITE;
    //         case 1:
    //             return Color.GRAY;
    //         case 2:
    //             return Color.BLACK;
    //         case 3:
    //             return Color.YELLOW;
    //         case 4:
    //             return Color.GREEN;
    //         default:
    //             return Color.WHITE;
    //     }
    // }

}
