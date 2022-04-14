package SortAlgorithmsVisual.sortingAlgorithms;

import SortAlgorithmsVisual.SortPanel;
import java.awt.*;

public class Bubble {

    private final int[] arr;
    private final SortPanel sortPanel;
    private int colorResetCount;

    // constructor
    public Bubble(SortPanel sortPanel, int[] arr){
        this.arr = arr;
        this.sortPanel = sortPanel;
        colorResetCount = arr.length;
    }

    // bubble sorting algorithm
    private int count = 1;
    public void bubbleSort() {
        resetColor();
        sortPanel.drawRect[count].setColor(Color.YELLOW);
        if(arr[count -1] > arr[count]){
            int temp = arr[count - 1];
            arr[count -1] = arr[count];
            arr[count] = temp;
        }
        count++;
        if(count == colorResetCount){
            count = 1;
            colorResetCount--;
        }
    }

    // resets color of changed arrRects
    private void resetColor() {
        for (int i = 0; i < colorResetCount; i++) {
            sortPanel.drawRect[i].setColor(Color.DARK_GRAY);
        }
    }


}
