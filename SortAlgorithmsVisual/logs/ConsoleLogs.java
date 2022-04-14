package SortAlgorithmsVisual.logs;

import SortAlgorithmsVisual.SortPanel;

public class ConsoleLogs {

    // arrays value sum console log
    public int arraySumValue(){
        int arrValue = 0;
        for (int value : SortPanel.arr) {
            arrValue += value;
        }
        return arrValue;
    }

    // unsorted array console log
    public void beforeSortLog() {
        System.out.println("Array Before Sort");
        for (int value : SortPanel.arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // sorted array console log
    public void afterSortLog() {
        System.out.println("Array After Sort");
        for (int value : SortPanel.arr) {
            System.out.print(value + " ");
        }
        System.out.println("\nArrays sum: " + arraySumValue());
    }

}
