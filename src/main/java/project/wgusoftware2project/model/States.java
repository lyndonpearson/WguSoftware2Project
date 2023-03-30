package project.wgusoftware2project.model;

public class States {
    int divisionId;
    String division;
    int countryId;

    public States(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public String getDivision() {
        return division;
    }


    public int getCountryId() {
        return countryId;
    }


    @Override
    public String toString(){
        return (division);
    }
}
