package SortAlgorithmsVisual;

import javax.swing.*;
import java.awt.*;

public class SortAlgorithmsVisual extends JPanel implements Runnable {

    // run program
    public static void main(String[] args) {
        SortAlgorithmsVisual sortAlgorithmsVisual = new SortAlgorithmsVisual();
        sortAlgorithmsVisual.frame(); // create JFrame
    }

    /** Change values of arr for different output.
     *  Change drawSpeed for faster or slower drawing speed.
     *  Change rect width and height for different size.
     */
    // todo: Add more sorting algorithms.
    // SELECT SORTING ALGORITHM
    private final int sortingAlgorithm = 1; // 0 = bubble, 1 = selection, 2 = insertion, 3 = cocktail
    // set of 200 numbers
    public static final int[] arr = {195, 45, 10, 116, 168, 37, 198, 65, 143, 174, 9, 96, 177, 193, 176, 118, 17, 14,
            156, 86, 171, 21, 149, 32, 98, 39, 121, 5, 53, 186, 189, 46, 6, 142, 66, 28, 128, 41, 188, 44, 15, 152, 24,
            27, 122, 105, 61, 154, 49, 167, 166, 12, 148, 48, 127, 58, 153, 40, 31, 172, 69, 35, 88, 173, 182, 2, 54,
            139, 179, 99, 103, 131, 161, 183, 87, 175, 59, 50, 18, 157, 162, 117, 110, 159, 197, 94, 123, 89, 126, 70,
            71, 181, 43, 138, 196, 11, 26, 47, 60, 132, 114, 184, 136, 120, 77, 178, 78, 23, 91, 113, 72, 192, 33, 150,
            151, 158, 3, 97, 75, 190, 101, 19, 16, 62, 185, 147, 22, 200, 73, 124, 38, 82, 51, 34, 180, 160, 67, 20, 191,
            95, 170, 85, 30, 80, 84, 4, 115, 130, 29, 155, 169, 164, 90, 55, 163, 108, 36, 64, 107, 74, 63, 42, 106, 134,
            104, 92, 79, 135, 8, 145, 109, 146, 13, 119, 93, 81, 68, 7, 112, 144, 57, 52, 137, 83, 1, 125, 100, 76, 111,
            129, 187, 141, 102, 56, 140, 165, 25, 199, 194, 133};
    // draw settings
    private final double drawSpeed = 25;
    private final int rectWidth = 5;
    private final int rectHeight = 5;

    // rect array
    private final ArrRect[] arrRect = new ArrRect[arr.length];

    // screen size
    private final int screenWidth = arr.length * rectWidth;
    private final int screenHeight = maxScreenHeight(arr) * rectHeight;

    // time check
    private long start;
    private long elapsedTime = 0;

    private final ConsoleLogs consoleLogs;
    private int arrayOrder = 0;
    private int loop = 0;
    private boolean run;
    private static String titleText = "";

    // constructor
    public SortAlgorithmsVisual() {
        consoleLogs = new ConsoleLogs();
        consoleLogs.beforeSortLog();
        createArrRect();
        Thread thread = new Thread(this);
        thread.start();
        run = true;
        // start time for calculating sort algorithm speed
        start = System.nanoTime();
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
            }
        }
    }

    // selection sorting algorithm
    private void selectionSort() {
        int n = arr.length;
        int min_idx = loop;
        for (int j = loop+1; j < n; j++) {
            if (arr[j] < arr[min_idx]) {
                min_idx = j;
            }
        }
        int temp = arr[min_idx];
        arr[min_idx] = arr[loop];
        arr[loop] = temp;
        loop++;
    }

    // insertion sorting algorithm
    private void insertionSort() {
        int key = arr[loop];
        int j = loop - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
        loop++;
    }

    // cocktail sorting algorithm
    private void cocktailSort() {
        int end = arr.length;
        for (int i = loop; i < end - 1; ++i)
        {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        end = end - 1;
        for (int i = end - 1; i >= loop; i--) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        loop = loop + 1;
    }

    // draw rect
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(run = true){
            for (int i = 0; i < arrayOrder; i++) {
                ArrRect object = arrRect[i];
                object.draw(g);
                drawText(g, titleText);
            }
        }
    }

    // draw text
    private void drawText(Graphics g, String titleText) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.setColor(Color.GRAY);
        g.drawString("Sort: " + titleText,20,20);
        g.drawString("Draw speed: "  + drawSpeed ,20,35);
        g.drawString("Rect size: width:"  + rectWidth + " | height:" + rectHeight,20,50);
        g.drawString("Sort time: " + elapsedTime + " ms", 20, 65);
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

    // select sort and repaint arrays while sorting
    private void moveArray() {
        if(arrayOrder < arr.length){
            arrayOrder++;
        } else {
            switch(sortingAlgorithm){
                case 0:
                    bubbleSort();
                    titleText = "bubble";
                    break;
                case 1:
                    selectionSort();
                    titleText = "selection";
                    break;
                case 2:
                    insertionSort();
                    titleText = "insertion";
                    break;
                case 3:
                    cocktailSort();
                    titleText = "cocktail";
                    break;
            }
            createArrRect();
            if(isSorted()){
                end();
            }
        }
    }

    // create unsorted array of rect
    private void createArrRect(){
        for (int i = 0; i < arrRect.length; i++) {
            arrRect[i] = new ArrRect(i, arr[i], screenHeight, rectWidth, rectHeight);
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

    // screen prefer size scaled by arrays sized
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(screenWidth,screenHeight);
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
        elapsedTime = (System.nanoTime() - start) / 1000000;
        run = false;
        consoleLogs.afterSortLog();
        System.out.println("\nArrays sum: " + consoleLogs.arraySumValue());
    }

}
