package model;

public class Stadium {
    private String stadium_id;
    private String stadium_name;
    private String city_name;
    private Integer stadium_capacity;

    public Stadium(String stadium_id, String stadium_name, String city_name, Integer stadium_capacity) {
        this.stadium_id = stadium_id;
        this.stadium_name = stadium_name;
        this.city_name = city_name;
        this.stadium_capacity = stadium_capacity;
    }

    public String getStadium_id() {
        return stadium_id;
    }

    public void setStadium_id(String stadium_id) {
        this.stadium_id = stadium_id;
    }

    public String getStadium_name() {
        return stadium_name;
    }

    public void setStadium_name(String stadium_name) {
        this.stadium_name = stadium_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public Integer getStadium_capacity() {
        return stadium_capacity;
    }

    public void setStadium_capacity(Integer stadium_capacity) {
        this.stadium_capacity = stadium_capacity;
    }
}
