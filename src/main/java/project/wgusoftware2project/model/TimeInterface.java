package project.wgusoftware2project.model;

/** Interface used for checking current and appointment times. */
public interface TimeInterface {
    /** Interface method to be implemented in Inventory class
     * <p>
     * LAMBDA - accepts both current and appointment time parameters.
     * Implemented in Inventory class to return true if an appointment
     * exists within 15 minutes of current time.
     * This implementation is a clear concise way to run this check
     * in the body of the Inventory class. It's a clearly titled
     * Lambda function and saves space/lines of code.
     @param hour1 Int parameter containing appointment hour
     @param minute1 Int parameter containing appointment minute
     @param hour2 Int parameter containing current hour
     @param minute2 Int parameter containing current minute
     */
    boolean timeCalculation(int hour1, int minute1, int hour2, int minute2);
}
