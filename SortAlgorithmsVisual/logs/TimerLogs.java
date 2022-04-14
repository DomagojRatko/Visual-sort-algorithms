package SortAlgorithmsVisual.logs;

public class TimerLogs {

    // time log
    private long start = 0;
    private boolean running = false;

    // start time log
    public void startTimeLog() {
        if(!running){
            start = System.nanoTime();
            running = true;
        }
    }

    // get time log in milliseconds
    public void getTimeLog() {
        running = false;
    }

    // elapsed time getter
    public long getElapsedTime() { return (System.nanoTime() - start) / 1000000; }

}
