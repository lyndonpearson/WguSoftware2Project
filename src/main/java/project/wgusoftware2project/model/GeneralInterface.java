package project.wgusoftware2project.model;

/** Interface used for comparing state names. */
public interface GeneralInterface {
    /** Interface method to be implemented in Inventory class
     * <p>
     * LAMBDA - accepts two Strings as parameters.
     * Implemented in Inventory class to return true if the two parameters
     * are equal to one another.
     * This implementation is a clear concise way to run this check
     * in the body of the Inventory class. It's a clearly titled
     * Lambda function and saves space/lines of code.
     @param input1 String parameter containing state to be compared
     @param input2 String parameter compared to input1
     */
    boolean compareStates(String input1, String input2);
}
