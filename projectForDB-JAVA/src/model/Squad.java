package model;

public class Squad {
    private String tournament_id;
    private String team_id;
    private String player_id;
    private String position_code;

    public Squad(String tournament_id, String team_id, String player_id, String position_code) {
        this.tournament_id = tournament_id;
        this.team_id = team_id;
        this.player_id = player_id;
        this.position_code = position_code;
    }

    public String getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(String tournament_id) {
        this.tournament_id = tournament_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public String getPosition_code() {
        return position_code;
    }

    public void setPosition_code(String position_code) {
        this.position_code = position_code;
    }
}
