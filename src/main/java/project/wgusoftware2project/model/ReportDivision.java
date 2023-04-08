package project.wgusoftware2project.model;

/** ReportDivision class created to store data grouped by
 * state.
 */
public class ReportDivision {
    String state;
    int total;

    /** ReportDivision constructor.
     @param state String storing state of Customer.
     @param total Integer storing total Customers from a.
     given state.
     */
    public ReportDivision(String state, int total) {
        this.state = state;
        this.total = total;
    }

    /** Method to return state of Customer for Report
     @return state String storing state field.
     */
    public String getState() {
        return state;
    }

    /** Method to assign state field of Customer for Report
     @param state String assigned to state field.
     */
    public void setState(String state) {
        this.state = state;
    }

    /** Method to return count of Customers in a state for Report
     @return total Integer storing total field
     */
    public int getTotal() {
        return total;
    }

    /** Method to assign total field of Customer for Report
     @param total String assigned to total field.
     */
    public void setTotal(int total) {
        this.total = total;
    }
}
