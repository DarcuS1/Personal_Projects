package model;

public class Teams {
    private String team_id;
    private String team_name;
    private String team_code;
    private String region_name;
    private String confederation_id;

    public Teams(String team_id, String team_name, String team_code, String region_name, String confederation_id) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_code = team_code;
        this.region_name = region_name;
        this.confederation_id = confederation_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_code() {
        return team_code;
    }

    public void setTeam_code(String team_code) {
        this.team_code = team_code;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getConfederation_id() {
        return confederation_id;
    }

    public void setConfederation_id(String confederation_id) {
        this.confederation_id = confederation_id;
    }
}
