package SortAlgorithmsVisual;

import javax.swing.*;

public class Frame {

    // constructor
    public Frame(SortPanel sortPanel) {
        JFrame frame = new JFrame("Sort Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(sortPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
