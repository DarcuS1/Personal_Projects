package model;

public class Award {
    private String award_id;
    private String award_name;
    private String award_description;
    private int year_introduced;

    public Award(String award_id, String award_name, String award_description, int year_introduced) {
        this.award_id = award_id;
        this.award_name = award_name;
        this.award_description = award_description;
        this.year_introduced = year_introduced;
    }

    public String getAward_id() {
        return award_id;
    }

    public void setAward_id(String award_id) {
        this.award_id = award_id;
    }

    public String getAward_name() {
        return award_name;
    }

    public void setAward_name(String award_name) {
        this.award_name = award_name;
    }

    public String getAward_description() {
        return award_description;
    }

    public void setAward_description(String award_description) {
        this.award_description = award_description;
    }

    public int getYear_introduced() {
        return year_introduced;
    }

    public void setYear_introduced(int year_introduced) {
        this.year_introduced = year_introduced;
    }
}
