package project.wgusoftware2project.model;

public class FirstLevelDivisions {
    int divisionId;
    String division;

    public FirstLevelDivisions(int divisionId, String division) {
        this.divisionId = divisionId;
        this.division = division;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
}
