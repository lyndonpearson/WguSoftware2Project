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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
