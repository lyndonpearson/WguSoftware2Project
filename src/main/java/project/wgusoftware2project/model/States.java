package project.wgusoftware2project.model;

/** States class created to store rows extracted from
 * State Table data from MySQL database.
 */
public class States {
    int divisionId;
    String division;
    int countryId;

    /** States constructor to store rows extracted from
     * State Table data from MySQL database.
     @param divisionId Integer storing division_Id column of MySQL table row.
     @param division String storing division column of MySQL table row.
     @param countryId Integer storing country_Id column of MySQL table row.
     */
    public States(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /** Method to return States object's divisionId field
     @return divisionId Integer storing divisionId field.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /** Method to return States object's division field
     @return division String storing division field.
     */
    public String getDivision() {
        return division;
    }

    /** Method to return States object's countryId field
     @return countryId Integer storing countryId field.
     */
    public int getCountryId() {
        return countryId;
    }

    /** Override toString method to print/return
     * the division String to views instead of the States object.
     */
    @Override
    public String toString(){
        return (division);
    }
}
