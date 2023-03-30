package project.wgusoftware2project.model;

public class ReportDivision {
    String state;
    int total;

    public ReportDivision(String state, int total) {
        this.state = state;
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public int getTotal() {
        return total;
    }
}
