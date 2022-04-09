package SortAlgorithmsVisual;

import javax.swing.*;
import java.awt.*;

public class SortAlgorithmsVisual extends JPanel implements Runnable {

    /** Change values of arr for different output.
     *  Change drawSpeed for faster or slower drawing speed.
     *  Change rect Width and Height
     *  Change realSort to true to disable visual break down of every action and display real bubble sorting
     */
    // SELECT SORTING ALGORITHM
    private final int sortingAlgorithm = 0; // 0 = bubble, 1 = selection

    private static final int[] arr = {45, 88, 69, 31, 35, 19, 58, 100, 67, 47, 12, 2, 3, 81, 98, 39, 49, 50, 56, 87, 89,
            59, 82, 21, 28, 17, 66, 70, 20, 13, 36, 68, 71, 10, 62, 25, 37, 1, 92, 91, 96, 64, 4, 65, 33, 7, 74, 97, 53,
            99, 30, 6, 48, 9, 83, 43, 94, 86, 22, 93, 18, 42, 29, 95, 54, 76, 90, 16, 84, 79, 8, 24, 61, 63, 38, 77, 34,
            72, 44, 14, 27, 52, 75, 51, 55, 26, 23, 57, 40, 85, 78, 46, 5, 11, 15, 41, 80, 60, 73, 32};
    private final double drawSpeed = 20;
    private final boolean realSort = true;
    private final int rectWidth = 10;
    private final int rectHeight = 10;

    // rect array
    private final ArrRect[] arrRect = new ArrRect[arr.length];

    // screen size
    private final int screenWidth = arr.length * rectWidth;
    private final int screenHeight = maxScreenHeight(arr) * rectHeight;

    // counter
    private int arrayOrder = 0;
    private boolean run = true;

    // constructor
    public SortAlgorithmsVisual() {
        beforeSortLog();
        createArrRect();
        Thread thread = new Thread(this);
        thread.start();
    }

    // run program
    public static void main(String[] args) {
        SortAlgorithmsVisual sortAlgorithmsVisual = new SortAlgorithmsVisual();
        sortAlgorithmsVisual.frame(); // create JFrame
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
                if(!realSort){
                    break;
                }
            }
        }
        // it stops run when array is done sorting
        if(isSorted()){
            end();
        }
    }

    // selection sorting algorithm
    private void selectionSort() {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                    break;
                }
            }
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        if(isSorted()){
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

    // Thread run
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
            switch(sortingAlgorithm){
                case 0:
                    bubbleSort();
                    break;
                case 1:
                    selectionSort();
                    break;
            }
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
    private static int maxScreenHeight(int[] t) {
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
        System.out.println("Array Before Sort");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // sorted array console log
    private void afterSortLog() {
        System.out.println("Array After Sort");
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }

    // check if array sorted
    private static boolean isSorted() {
        if (arr.length <= 1) {
            return true;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // stop after array is sorted
    private void end(){
        run = false;
        afterSortLog();
        System.out.println("\nArrays sum: " + arraySumValue());
    }

}
