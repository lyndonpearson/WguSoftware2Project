package project.wgusoftware2project.model;

public class Countries {
    int countryId;
    String country;

    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    public int getCountryId() {
        return countryId;
    }


    @Override
    public String toString(){
        return (country);
    }
}
