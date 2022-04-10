package SortAlgorithmsVisual;

import java.awt.*;

public class ArrRect {

    // rects x and y position
    private final int x;
    private final int y;

    // rects width and height
    private final int rectWidth;
    private final int rectHeight;

    // amount of rects needed to be created in column
    private final int column;

    // constructor
    public ArrRect(int x, int column, int y, int rectWidth, int rectHeight) {
        this.x = x * rectWidth;
        this.y = y - rectHeight;
        this.column = column;
        this.rectWidth = rectWidth;
        this.rectHeight = rectHeight;
    }

    // sets location for rect column
    public void draw(Graphics g){
        for (int j = 0; j < column; j++) {
            drawRect(g, x, y - (j * rectHeight));
        }
    }

    // draws and colors new rect
    private void drawRect(Graphics g, int x, int y){
        g.setColor(Color.darkGray);
        g.fillRect(x, y, rectWidth, rectHeight);
        g.setColor(Color.red);
        g.drawRect(x, y, rectWidth, rectHeight);
    }

}
