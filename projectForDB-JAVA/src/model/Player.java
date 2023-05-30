package model;

public class Player {
    private String player_id;
    private String family_name;
    private String given_name;
    private Boolean goal_keeper;
    private Boolean defender;
    private Boolean midfielder;
    private Boolean forward;

    public Player(String player_id, String family_name, String given_name, Boolean goalkeeper, Boolean defender, Boolean midfielder, Boolean forward) {
        this.player_id = player_id;
        this.family_name = family_name;
        this.given_name = given_name;

        this.goal_keeper = goalkeeper;
        this.defender = defender;
        this.midfielder = midfielder;
        this.forward = forward;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public Boolean getGoalkeeper() {
        return goal_keeper;
    }

    public void setGoalkeeper(Boolean goalkeeper) {
        this.goal_keeper = goalkeeper;
    }

    public Boolean getDefender() {
        return defender;
    }

    public void setDefender(Boolean defender) {
        this.defender = defender;
    }

    public Boolean getMidfielder() {
        return midfielder;
    }

    public void setMidfielder(Boolean midfielder) {
        this.midfielder = midfielder;
    }

    public Boolean getForward() {
        return forward;
    }

    public void setForward(Boolean forward) {
        this.forward = forward;
    }
}
