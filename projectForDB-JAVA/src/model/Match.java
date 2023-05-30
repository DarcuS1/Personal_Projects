package model;

public class Match {
    private String tournament_id;
    private String match_id;
    private String match_name;
    private String stage_name;
    private String score;

    public Match(String tournament_id, String match_id, String match_name, String stage_name, String score) {
        this.tournament_id = tournament_id;
        this.match_id = match_id;
        this.match_name = match_name;
        this.stage_name = stage_name;
        this.score = score;
    }

    public String getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(String tournament_id) {
        this.tournament_id = tournament_id;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getMatch_name() {
        return match_name;
    }

    public void setMatch_name(String match_name) {
        this.match_name = match_name;
    }

    public String getStage_name() {
        return stage_name;
    }

    public void setStage_name(String stage_name) {
        this.stage_name = stage_name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

