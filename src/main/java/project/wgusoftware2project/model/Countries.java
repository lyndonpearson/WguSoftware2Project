package project.wgusoftware2project.model;

/** Countries class created to store rows extracted from
 * Country Table data from MySQL database.
 */
public class Countries {
    int countryId;
    String country;

    /** Countries constructor to store rows extracted from
     * Country Table data from MySQL database.
     @param countryId Integer storing country_ID column of MySQL table row.
     @param country String storing country column of MySQL table row.
     */
    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /** Method to return Countries object's countryId field
     @return countryId Integer storing countryId field.
     */
    public int getCountryId() {
        return countryId;
    }

    /** Override toString method to print/return
     * the country String to views instead of the Countries object.
     */
    @Override
    public String toString(){
        return (country);
    }
}
