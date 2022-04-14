package SortAlgorithmsVisual.draw;

import java.awt.*;

public class DrawRect {

    // rects x and y position
    private final int x;
    private final int y;

    // rects width and height
    private final int rectWidth;
    private final int rectHeight;

    // amount of rects needed to be created in column
    private int column;

    // rect color
    private Color color;

    // constructor
    public DrawRect(int x, int column, int y, int rectWidth, int rectHeight) {
        this.x = x * rectWidth;
        this.y = y - rectHeight;
        this.column = column;
        this.rectWidth = rectWidth;
        this.rectHeight = rectHeight;
        this.color = Color.darkGray;
    }

    // sets location for rect column
    public void draw(Graphics g){
        for (int j = 0; j < column; j++) {
            drawRect(g, x, y - (j * rectHeight));
        }
    }

    // draws and colors new rect
    private void drawRect(Graphics g, int x, int y){
        g.setColor(color);
        g.fillRect(x, y, rectWidth, rectHeight);
        g.setColor(Color.red);
        g.drawRect(x, y, rectWidth, rectHeight);
    }

    // setters
    public void setColor(Color color) { this.color = color; }
    public void setColumn(int column) { this.column = column; }

}
