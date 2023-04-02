package project.wgusoftware2project.model;

/** Interface used for checking current and appointment times. */
public interface TimeInterface {
    /** Interface method to be implemented in Inventory class
     @param hour1 Int parameter containing appointment hour
     @param minute1 Int parameter containing appointment minute
     @param hour2 Int parameter containing current hour
     @param minute2 Int parameter containing current minute
     */
    boolean timeCalculation(int hour1, int minute1, int hour2, int minute2);
}
