package SortAlgorithmsVisual.draw;

import SortAlgorithmsVisual.sortingAlgorithms.AlgorithmsList;
import java.awt.*;

public class DrawText {

    private final double drawSpeed;
    private final int rectWidth, rectHeight;

    // constructor
    public DrawText(double drawSpeed, int rectWidth, int rectHeight) {
        this.drawSpeed = drawSpeed;
        this.rectWidth = rectWidth;
        this.rectHeight = rectHeight;
    }

    // draw text
    public void draw(Graphics g, AlgorithmsList algorithmsList, int arrayLength, long elapsedTime) {
        g.setFont(new Font("Ariel", Font.PLAIN, 20));
        g.setColor(Color.GRAY);
        g.drawString("Sort: " + algorithmsList,20,20);
        g.drawString("Array length: " + arrayLength,20,40);
        g.drawString("Draw speed: "  + drawSpeed ,20,60);
        g.drawString("Rect size: width: "  + rectWidth + " | height: " + rectHeight,20,80);
        g.drawString("Sort time: " + elapsedTime + " ms", 20, 100);
    }

}
