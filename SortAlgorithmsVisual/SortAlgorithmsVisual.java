package SortAlgorithmsVisual;

import javax.swing.*;
import java.awt.*;

public class SortAlgorithmsVisual extends JPanel implements Runnable {

    /** Change values of arr for different output.
     *  Change drawSpeed for faster or slower drawing speed.
     *  Change rect Width and Height
     */
    private static final int[] arr ={10,16,5,8,19,4,17,11,21,9,20,12,7,13,14,2,18,3,15,1,6};
    private final double drawSpeed = 10;
    private final int rectWidth = 40;
    private final int rectHeight = 40;

    // rect array
    private final ArrRect[] arrRect = new ArrRect[arr.length];

    // screen size
    private final int screenWidth = arr.length * rectWidth;
    private final int screenHeight = maxScreenHeight(arr) * rectHeight;

    // counter
    private int arrayOrder = 0;
    private boolean run = true;

    // run program
    public static void main(String[] args) {
        SortAlgorithmsVisual sortAlgorithmsVisual = new SortAlgorithmsVisual();
        sortAlgorithmsVisual.frame(); // create JFrame
    }

    // constructor
    public SortAlgorithmsVisual() {
        createArrRect();
        Thread thread = new Thread(this);
        thread.start();
    }

    // create unsorted array of rect
    private void createArrRect(){
        for (int i = 0; i < arrRect.length; i++) {
            arrRect[i] = new ArrRect(i, arr[i], screenHeight, rectWidth, rectHeight);
        }
    }

    // bubble sorting algorithm
    private void bubbleSort() {
        int n = arr.length;
        int temp = 0;
        for(int j=1; j < (n); j++){
            if(arr[j-1] > arr[j]){
                temp = arr[j-1];
                arr[j-1] = arr[j];
                arr[j] = temp;
                break;
            }
        }
        // it stops run when array is sorted
        if(temp == 0){
            end();
        }
    }

    // screen prefer size scaled by arrays sized
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(screenWidth,screenHeight);
    }

    // draw rect
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(run = true){
            for (int i = 0; i < arrayOrder; i++) {
                ArrRect object = arrRect[i];
                object.draw(g);
            }
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
                moveArray();
                repaint();
                delta--;
            }
        }
    }

    // repaint arrays while sorting
    private void moveArray() {
        if(arrayOrder < arr.length){
            arrayOrder++;
        } else {
            bubbleSort();
            createArrRect();
        }
    }

    // frame
    private void frame(){
        JFrame frame = new JFrame("Sort Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // arrays max value for calculating screen height
    public static int maxScreenHeight(int[] t) {
        int maximum = t[0];
        for (int i=1; i<t.length; i++) {
            if (t[i] > maximum) {
                maximum = t[i];
            }
        }
        return maximum;
    }

    // arrays value sum console log
    private int arraySumValue(){
        int arrValue = 0;
        for (int value : arr) {
            arrValue += value;
        }
        return arrValue;
    }

    // unsorted array console log
    private void beforeSortLog() {
        System.out.println("Array Before Bubble Sort");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // sorted array console log
    private void afterSortLog() {
        System.out.println("Array After Bubble Sort");
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }

    // stop after array is sorted
    private void end(){
        run = false;
        beforeSortLog();
        afterSortLog();
        System.out.println("\nArrays sum: " + arraySumValue());
    }

}
