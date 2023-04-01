package project.wgusoftware2project.model;

/** Interface used for comparing state names. */
public interface GeneralInterface {
    /** Interface method to be implemented in Inventory class
     @param input1 String parameter containing state to be compared
     @param input2 String parameter compared to input1
     */
    boolean compareStates(String input1, String input2);
}
