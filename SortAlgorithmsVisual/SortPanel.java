package SortAlgorithmsVisual;

import SortAlgorithmsVisual.draw.RandomIntSet;
import SortAlgorithmsVisual.draw.DrawRect;
import SortAlgorithmsVisual.draw.DrawText;
import SortAlgorithmsVisual.logs.*;
import SortAlgorithmsVisual.sortingAlgorithms.Bubble;
import SortAlgorithmsVisual.sortingAlgorithms.AlgorithmsList;
import javax.swing.*;
import java.awt.*;

public class SortPanel extends JPanel implements Runnable {

    // todo: Add more sorting algorithms.

    /** Change to chose array size for sorting:
     *  - SET_50
     *  - SET_100
     *  - SET_200
     *  - SET_500
     *  @see SortPanel int[] arr = RandomIntSet.SET_50; to change set size.
     *  @see RandomIntSet attention read documentation.
     *
     *  Change drawSpeed for faster or slower drawing speed.
     *  @see SortPanel double drawSpeed;
     *
     *  Change rect width and height for different size.
     *  @see SortPanel int rectWidth, rectHeight;
     *
     *  Change sorting algorithm
     *  @see SortPanel AlgorithmsList algorithmsList = AlgorithmsList.BUBBLE;
     *
     *  List of sorting algorithms:
     *  - BUBBLE
     *  @see AlgorithmsList
     *
     */

    // select sorting algorithm
    private final AlgorithmsList algorithmsList = AlgorithmsList.BUBBLE;

    // draw settings
    public static final int[] arr = RandomIntSet.SET_50;

    /**
     * Recommended drawSpeed is best to keep lower around 20 - 100 for small arrays (SET_50),
     * and for big set around 100 and 1000 (SET_200).
     * Also depending on what sort is being used.
     */
    private final double drawSpeed = 100;

    /**
     * Recommended rectWidth and rectHeight is best depending set size.
     * For small set (SET_50) rectWidth = 20, rectHeight = 20,
     * for bigger set (SET_200) rectWidth = 4, rectHeight = 4.
     * Attention dont go lower than rectWidth = 2, rectHeight = 2 to avoid visual bugs!
     */
    private final int rectWidth = 20;
    private final int rectHeight = 20;

    // rect array
    public final DrawRect[] drawRect = new DrawRect[arr.length];
    private boolean run;

    // algorithms
    private final Bubble bubble;

    // classes
    private final DrawText drawText;
    private final ConsoleLogs consoleLogs;
    private final TimerLogs timerLogs;

    // constructor
    public SortPanel() {
        new Frame(this);
        drawText = new DrawText(drawSpeed, rectWidth, rectHeight);
        bubble = new Bubble(this, arr);
        consoleLogs = new ConsoleLogs();
        timerLogs = new TimerLogs();
        consoleLogs.beforeSortLog();
        createArrRect();
        Thread thread = new Thread(this);
        thread.start();
        run = true;
    }

    // draw
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(run = true){
            drawArrRect(g);
            drawText.draw(g, algorithmsList, arr.length, timerLogs.getElapsedTime());
        }
    }

    // create unsorted array of rect
    private void createArrRect(){
        for (int i = 0; i < drawRect.length; i++) {
            drawRect[i] = new DrawRect(i, arr[i], maxScreenHeight(), rectWidth, rectHeight);
        }
    }

    // draw unsorted array of rect
    private int countDrawArrRect = 0;
    private void drawArrRect(Graphics g) {
        for (int i = 0; i < countDrawArrRect; i++) {
            drawRect[i].draw(g);
        }
    }

    // move array of rect
    private void updateArrRect() {
        for (int i = 0; i < drawRect.length; i++) {
            drawRect[i].setColumn(arr[i]);
        }
    }

    // change color of sorted array
    private int countColorSortedArrRect = 0;
    private void colorSortedArrRect() {
        if(countColorSortedArrRect == arr.length){
            end();
        } else {
            drawRect[countColorSortedArrRect].setColor(Color.GREEN);
            countColorSortedArrRect++;
        }
    }

    // thread run
    public void run(){
        long lastTime = System.nanoTime();
        double ns = 1000000000 / drawSpeed;
        double delta = 0;
        while(run){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                loop();
                repaint();
                delta--;
            }
        }
    }

    // select sort and repaint arrays while sorting
    private void loop() {
        if(countDrawArrRect < arr.length) {
            countDrawArrRect++;
        } else if (isSorted()) {
            colorSortedArrRect();
        } else {
            switch(algorithmsList){
                case BUBBLE:
                    bubble.bubbleSort();
                    break;
                case NULL:
                    break;
            }
            timerLogs.startTimeLog();
            updateArrRect();
        }
    }

    // arrays max value for calculating screen height
    private int maxScreenHeight() {
        int maximum = arr[0];
        for (int i=1; i<arr.length; i++) {
            if (arr[i] > maximum) {
                maximum = arr[i];
            }
        }
        return maximum * rectHeight;
    }

    // screen prefer size scaled by arrays sized
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(arr.length * rectWidth,maxScreenHeight());
    }

    // check if array sorted
    private boolean isSorted() {
        if (arr.length <= 1) {
            return true;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        timerLogs.getTimeLog();
        return true;
    }

    // running loop end and get console logs
    private void end(){
        run = false;
        consoleLogs.afterSortLog();
    }

}
