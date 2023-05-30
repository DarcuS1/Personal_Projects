
package controller;

import model.*;

import java.io.IOException;
import java.sql.*;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;

/**
 * @author gokuz
 */
public class FXMLDocumentController  {

    //region declarations for users

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Connection connect = null;
    private Statement statement = null;

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    @FXML
    private TextField createAccountUserBTN;
    @FXML
    private PasswordField createAccountPasswordBTN;
    @FXML
    private PasswordField createAccountPasswordBTN2;
    @FXML
    private TextField loginTextField;
    @FXML
    private TextField loginPersonType;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private TableView<Award> tableViewAwardsUser;
    @FXML
    private TableColumn<Award,String> award_idColumn;
    @FXML
    private TableColumn<Award,String>  award_nameColumn;
    @FXML
    private TableColumn<Award,String>  award_descriptionColumn;
    @FXML
    private TableColumn<Award, Integer>  year_introducedColumn;


    @FXML
    private TableView<Player> tableViewPlayersUser;
    @FXML
    private TableColumn<Player,String> player_idColumn;
    @FXML
    private TableColumn<Player,String>  player_familynameColumn;
    @FXML
    private TableColumn<Player,String>  palyer_givennameColumn;
    @FXML
    private TableColumn<Player, Boolean>  goalkeeperColumn;
    @FXML
    private TableColumn<Player, Boolean>  defenderColumn;
    @FXML
    private TableColumn<Player, Boolean>  midfielderColumn;
    @FXML
    private TableColumn<Player, Boolean>  forwardColumn;


    @FXML
    private TableView<Match> tableViewMatchesUser;
    @FXML
    private TableColumn<Match,String> tournament_idColumn;
    @FXML
    private TableColumn<Match,String>  match_idColumn;
    @FXML
    private TableColumn<Match,String>  match_nameColumn;
    @FXML
    private TableColumn<Match,String>  stage_nameColumn;
    @FXML
    private TableColumn<Match,String>  scoreColumn;


    @FXML
    private TableView<Squad> tableViewSquadsUser;
    @FXML
    private TableColumn<Squad,String> tournament_idColumn2;
    @FXML
    private TableColumn<Squad,String>  team_idColumn;
    @FXML
    private TableColumn<Squad,String>  player_idColumn2;
    @FXML
    private TableColumn<Squad,String>  position_codeColumn;



    @FXML
    private TableView<Teams> tableViewTeamsUser;
    @FXML
    private TableColumn<Teams,String> team_idColumn2;
    @FXML
    private TableColumn<Teams,String>  team_nameColumn;
    @FXML
    private TableColumn<Teams,String>  team_codeColumn;
    @FXML
    private TableColumn<Teams,String>  region_nameColumn;
    @FXML
    private TableColumn<Teams,String>  confederation_idColumn;

    @FXML
    private TableView<Stadium> tableViewStadiumsUser;
    @FXML
    private TableColumn<Stadium,String> stadium_idColumn;
    @FXML
    private TableColumn<Stadium,String>  stadium_nameColumn;
    @FXML
    private TableColumn<Stadium,String>  city_nameColumn;
    @FXML
    private TableColumn<Stadium,Integer>  stadium_capacityColumn;
//endregion


    //region declarations for admin

    @FXML
    private TextField teams1;
    @FXML
    private TextField teams2;
    @FXML
    private TextField teams3;
    @FXML
    private TextField teams4;
    @FXML
    private TextField teams5;

    @FXML
    private TextField players1;
    @FXML
    private TextField players2;
    @FXML
    private TextField players3;
    @FXML
    private TextField players4;
    @FXML
    private TextField players5;
    @FXML
    private TextField players6;
    @FXML
    private TextField players7;

    @FXML
    private TextField stadiums1;
    @FXML
    private TextField stadiums2;
    @FXML
    private TextField stadiums3;
    @FXML
    private TextField stadiums4;

    @FXML
    private TextField awards1;
    @FXML
    private TextField awards2;
    @FXML
    private TextField awards3;
    @FXML
    private TextField awards4;

    //endregion


     //region methods for users
    public void stadiumsTablePopulate(MouseEvent event) throws Exception,SQLException {
        try{
            stadium_idColumn.setCellValueFactory((new PropertyValueFactory<Stadium,String>("stadium_id")));
            stadium_nameColumn.setCellValueFactory((new PropertyValueFactory<Stadium,String>("stadium_name")));
            city_nameColumn.setCellValueFactory((new PropertyValueFactory<Stadium,String>("city_name")));
            stadium_capacityColumn.setCellValueFactory((new PropertyValueFactory<Stadium,Integer>("stadium_capacity")));



            String url = "jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";
            connect = DriverManager.getConnection(url);
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from stadiums");

            while (resultSet.next()) {
                String stadium_id = resultSet.getString("stadium_id");
                String stadium_name = resultSet.getString("stadium_name");
                String city_name = resultSet.getString("city_name");
                Integer stadium_capacity = resultSet.getInt("stadium_capacity");



                Stadium stadium=new Stadium (stadium_id,stadium_name,city_name, stadium_capacity);
                ObservableList<Stadium> stadiums= tableViewStadiumsUser.getItems();
                stadiums.add(stadium);
                tableViewStadiumsUser.setItems(stadiums);

            }
        }catch(Exception e)
        {
            System.out.println("NO");
        }finally {
            close();
        }
    }

    public void stadiumsTablePopulate(ActionEvent event) throws Exception,SQLException {
        try{
            stadium_idColumn.setCellValueFactory((new PropertyValueFactory<Stadium,String>("stadium_id")));
            stadium_nameColumn.setCellValueFactory((new PropertyValueFactory<Stadium,String>("stadium_name")));
            city_nameColumn.setCellValueFactory((new PropertyValueFactory<Stadium,String>("city_name")));
            stadium_capacityColumn.setCellValueFactory((new PropertyValueFactory<Stadium,Integer>("stadium_capacity")));



            String url = "jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";
            connect = DriverManager.getConnection(url);
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select stadium_id, stadium_name, city_name, stadium_capacity from stadiums");

            while (resultSet.next()) {
                String stadium_id = resultSet.getString("stadium_id");
                String stadium_name = resultSet.getString("stadium_name");
                String city_name = resultSet.getString("city_name");
                Integer stadium_capacity = resultSet.getInt("stadium_capacity");



                Stadium stadium=new Stadium (stadium_id,stadium_name,city_name, stadium_capacity);
                ObservableList<Stadium> stadiums= tableViewStadiumsUser.getItems();
                stadiums.add(stadium);
                tableViewStadiumsUser.setItems(stadiums);

            }
        }catch(Exception e)
        {
            System.out.println("NO");
        }finally {
            close();
        }
    }

    public void teamsTablePopulate(MouseEvent event) throws Exception,SQLException {
        try{
            team_idColumn2.setCellValueFactory((new PropertyValueFactory<Teams,String>("team_id")));
            team_nameColumn.setCellValueFactory((new PropertyValueFactory<Teams,String>("team_name")));
            team_codeColumn.setCellValueFactory((new PropertyValueFactory<Teams,String>("team_code")));
            region_nameColumn.setCellValueFactory((new PropertyValueFactory<Teams,String>("region_name")));
            confederation_idColumn.setCellValueFactory((new PropertyValueFactory<Teams,String>("confederation_id")));


            String url = "jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";
            connect = DriverManager.getConnection(url);
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from teams");

            while (resultSet.next()) {
                String team_id = resultSet.getString("team_id");
                String team_name = resultSet.getString("team_name");
                String team_code = resultSet.getString("team_code");
                String region_name = resultSet.getString("region_name");
                String confederation_id = resultSet.getString("confederation_id");


                Teams team=new Teams(team_id,team_name,team_code, region_name,confederation_id);
                ObservableList<Teams> teams= tableViewTeamsUser.getItems();
                teams.add(team);
                tableViewTeamsUser.setItems(teams);

            }
        }catch(Exception e)
        {
            System.out.println("NO");
        }finally {
            close();
        }
    }


    public void squadsTablePopulate(MouseEvent event) throws Exception,SQLException {
        try{
            tournament_idColumn2.setCellValueFactory((new PropertyValueFactory<Squad,String>("tournament_id")));
            team_idColumn.setCellValueFactory((new PropertyValueFactory<Squad,String>("team_id")));
            player_idColumn2.setCellValueFactory((new PropertyValueFactory<Squad,String>("player_id")));
            position_codeColumn.setCellValueFactory((new PropertyValueFactory<Squad,String>("position_code")));


            String url = "jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";
            connect = DriverManager.getConnection(url);
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from squads");

            while (resultSet.next()) {
                String tournament_id = resultSet.getString("tournament_id");
                String team_id = resultSet.getString("team_id");
                String player_id = resultSet.getString("player_id");
                String position_code = resultSet.getString("position_code");


                Squad squad=new Squad (tournament_id,team_id,player_id, position_code);
                ObservableList<Squad> squads= tableViewSquadsUser.getItems();
                squads.add(squad);
                tableViewSquadsUser.setItems(squads);

            }
        }catch(Exception e)
        {
            System.out.println("NO");
        }finally {
            close();
        }
    }

    public void matchesTablePopulate(MouseEvent event) throws Exception,SQLException {
        try{
            tournament_idColumn.setCellValueFactory((new PropertyValueFactory<Match,String>("tournament_id")));
            match_idColumn.setCellValueFactory((new PropertyValueFactory<Match,String>("match_id")));
            match_nameColumn.setCellValueFactory((new PropertyValueFactory<Match,String>("match_name")));
            stage_nameColumn.setCellValueFactory((new PropertyValueFactory<Match,String>("stage_name")));
            scoreColumn.setCellValueFactory((new PropertyValueFactory<Match,String>("score")));

            String url = "jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";
            connect = DriverManager.getConnection(url);
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from matches");

            while (resultSet.next()) {
                String tournament_id = resultSet.getString("tournament_id");
                String match_id = resultSet.getString("match_id");
                String match_name = resultSet.getString("match_name");
                String stage_name = resultSet.getString("stage_name");
                String score = resultSet.getString("score");

                Match match=new Match (tournament_id,match_id,match_name, stage_name,score);
                ObservableList<Match> matches= tableViewMatchesUser.getItems();
                matches.add(match);
                tableViewMatchesUser.setItems(matches);

            }
        }catch(Exception e)
        {
            System.out.println("NO");
        }finally {
            close();
        }
    }
    public void playersTablePopulate(MouseEvent event) throws Exception,SQLException {
        try{
            player_idColumn.setCellValueFactory((new PropertyValueFactory<model.Player,String>("player_id")));
            player_familynameColumn.setCellValueFactory((new PropertyValueFactory<Player,String>("family_name")));
            palyer_givennameColumn.setCellValueFactory((new PropertyValueFactory<Player,String>("given_name")));
            goalkeeperColumn.setCellValueFactory((new PropertyValueFactory<Player,Boolean>("goal_keeper")));
            defenderColumn.setCellValueFactory((new PropertyValueFactory<Player,Boolean>("defender")));
            midfielderColumn.setCellValueFactory((new PropertyValueFactory<Player,Boolean>("midfielder")));
            forwardColumn.setCellValueFactory((new PropertyValueFactory<Player,Boolean>("forward")));

            String url = "jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";
            connect = DriverManager.getConnection(url);
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from players");

            while (resultSet.next()) {
                String player_id = resultSet.getString("player_id");
                String family_name = resultSet.getString("family_name");
                String given_name = resultSet.getString("given_name");
                Boolean goalkeeper = resultSet.getBoolean("goal_keeper");
                Boolean defender = resultSet.getBoolean("defender");
                Boolean midfielder = resultSet.getBoolean("midfielder");
                Boolean forward = resultSet.getBoolean("forward");

                Player player=new Player (player_id,family_name,given_name, goalkeeper,defender,midfielder,forward);
                ObservableList<Player> players= tableViewPlayersUser.getItems();
                players.add(player);
                tableViewPlayersUser.setItems(players);

            }
        }catch(Exception e)
        {
            System.out.println("NO");
       }finally {
            close();
        }
    }

    public void awardsTablePopulate(MouseEvent event) throws Exception ,SQLException{
        try{
        award_idColumn.setCellValueFactory((new PropertyValueFactory<Award,String>("award_id")));
        award_nameColumn.setCellValueFactory((new PropertyValueFactory<Award,String>("award_name")));
        award_descriptionColumn.setCellValueFactory((new PropertyValueFactory<Award,String>("award_description")));
        year_introducedColumn.setCellValueFactory((new PropertyValueFactory<Award,Integer>("year_introduced")));


            String url = "jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";
            connect = DriverManager.getConnection(url);
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from awards");

        while (resultSet.next()) {

            String award_id = resultSet.getString("award_id");
            String award_name = resultSet.getString("award_name");
            String award_description = resultSet.getString("award_description");
            int year_introduced = resultSet.getInt("year_introduced");

            Award award=new Award (award_id,award_name,award_description, year_introduced);
            ObservableList<Award> awards= tableViewAwardsUser.getItems();
            awards.add(award);
            tableViewAwardsUser.setItems(awards);

        }
        }catch(Exception e)
        {
            System.out.println("NO");
        }finally {
            close();
        }
    }

    @FXML
    public void handleClose(MouseEvent event) {
        System.exit(0);
    }
    @FXML
    public void handleSignup(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("src/view/scene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //  stage.close();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleExploreMore(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("view/scene5.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //  stage.close();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleBackToDash(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("view/scene5.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //  stage.close();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleBackSignup(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("view/FXMLDocument.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleLogin(MouseEvent event) throws IOException {


        String username=loginTextField.getText();
        String password=loginPasswordField.getText();
        String loginType2=loginPersonType.getText();
        System.out.println(loginType2);
        user=getAuthenticatedUser(username,password,loginType2);

        if (user != null && user.userLoginType.equals("Admin")) {
            root = FXMLLoader.load(getClass().getResource("view/scene3.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //  stage.close();
             scene = new Scene(root);
             stage.setScene(scene);
            stage.show();
        }
        else {
            if (user != null && user.userLoginType.equals("User")){
                root = FXMLLoader.load(getClass().getResource("view/scene5.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //  stage.close();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
            else {
                givePopUp2(event);
            }
        }

    }
    public User user;
    private User getAuthenticatedUser(String username, String password,String loginType){
        User user = null;
        try{
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProjectAuthAcc", "postgres", "0911");
            // Connected to database successfully...

            if(loginType.equals("User")) {
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM users WHERE username=? AND password=?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    user = new User();
                    user.name = resultSet.getString("username");
                    user.password = resultSet.getString("password");
                    user.userLoginType=loginType;
                }
                resultSet.close();
                stmt.close();
                conn.close();
            }
            else{
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM admins WHERE username=? AND password=?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    user = new User();
                    user.name = resultSet.getString("username");
                    user.password = resultSet.getString("password");
                    user.userLoginType=loginType;
                }
                resultSet.close();
                stmt.close();
                conn.close();
            }

        }catch(Exception e){
            e.printStackTrace();
        }


        return user;
    }
    @FXML
    public void handleCreateAccount(MouseEvent event) throws IOException {
        if(registerUser(event)) {
            root = FXMLLoader.load(getClass().getResource("view/scene5.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //  stage.close();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            System.out.println("Nahh");
        }
    }
    @FXML
    private boolean registerUser(MouseEvent event ) throws IOException {

        String username = createAccountUserBTN.getText();
        String password = createAccountPasswordBTN.getText();
        String passwordCheck = createAccountPasswordBTN2.getText();
        if (password.equals(passwordCheck) && !password.equals("")) {
            user = addUserToDatabase(event,username, password);
            if (user != null) {
                return true;

            } else {
                return false;
            }
            // System.out.println(username);
            //System.out.println(password);
        }
        else{
            givePopUp(event);
            return false;
        }
    }
    private User addUserToDatabase(MouseEvent event,String username, String password) throws IOException {

        User user = null;
        final String DB_URL = "jdbc:postgresql://localhost/postgres";
        final String USERNAME = "postgres";
        final String PASSWORD = "0911";
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProjectAuthAcc", "postgres", "0911");


            stmt = conn.createStatement();
            String sql = "INSERT INTO users VALUES (?, ?)";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            //Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = username;
                user.password = password;
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            //e.printStackTrace();
            givePopUp3(event);
        }

        return user;
    }
    @FXML
    public void handleBTNScene6(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("view/scene6.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //  stage.close();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleBTNScene7(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("view/scene7.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //  stage.close();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleBTNScene8(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("view/scene8.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //  stage.close();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleBTNScene9(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("view/scene9.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //  stage.close();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleBTNScene10(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("view/scene10.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //  stage.close();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleBTNScene11(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("view/scene11.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //  stage.close();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleBackToSignup(MouseEvent event) throws IOException {

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }
    @FXML
    public void givePopUp(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("view/passwordsPop.fxml"));
        stage= new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();
    }
    @FXML
    public void givePopUp2(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("view/incorrectPersonPop.fxml"));
        stage= new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();
    }
    @FXML
    public void givePopUp3(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("view/userCreationError.fxml"));
        stage= new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    public void clearHandle(MouseEvent event) throws IOException{

    }
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

    //endregion


    //region methods for admin

    @FXML
    public void handleClear(MouseEvent event)throws IOException{
        teams1.clear();
        teams2.clear();
        teams3.clear();
        teams4.clear();
        teams5.clear();

        for ( int i = 0; i<tableViewAwardsUser.getItems().size(); i++) {
            tableViewAwardsUser.getItems().clear();
        }
        for ( int i = 0; i<tableViewPlayersUser.getItems().size(); i++) {
            tableViewPlayersUser.getItems().clear();
        }
        for ( int i = 0; i<tableViewTeamsUser.getItems().size(); i++) {
            tableViewTeamsUser.getItems().clear();
        }
        for ( int i = 0; i<tableViewStadiumsUser.getItems().size(); i++) {
            tableViewStadiumsUser.getItems().clear();
        }

        players1.clear();
        players2.clear();
        players3.clear();
        players4.clear();
        players5.clear();
        players6.clear();
        players7.clear();

        stadiums1.clear();
        stadiums2.clear();
        stadiums3.clear();
        stadiums4.clear();

        awards1.clear();
        awards2.clear();
        awards3.clear();
        awards4.clear();
    }

    public void addAwards(MouseEvent event) throws IOException, SQLException {
        try {
            String award1 = awards1.getText();
            String award2 = awards2.getText();
            String award3= awards3.getText();
            Integer award4 = Integer.parseInt(awards4.getText());

            String url="jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";

            connect = DriverManager.getConnection(url);


            statement = connect.createStatement();


            System.out.println("*** INSERT ***");
            preparedStatement = connect.prepareStatement("insert into awards values (?, ?, ?, ? )");

            // Parameters start with 1
            preparedStatement.setString(1, award1);
            preparedStatement.setString(2, award2);
            preparedStatement.setString(3, award3);
            preparedStatement.setInt(3, award4);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    public void deleteAwards(MouseEvent event) throws IOException, SQLException {
        try {
            String award1 = awards1.getText();
           // String award2 = awards2.getText();
          //  String award3= awards3.getText();
           // Integer award4 = Integer.parseInt(awards4.getText());

            String url="jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";

            connect = DriverManager.getConnection(url);

            statement = connect.createStatement();

            System.out.println("*** DELETE ***");

            preparedStatement = connect.prepareStatement("delete from awards where award_id = ? ");
            preparedStatement.setString(1,award1);
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    public void updateAwards(MouseEvent event) throws IOException, SQLException {
        try {
            String award1 = awards1.getText();
            String award2 = awards2.getText();
           // String award3= awards3.getText();
          //  Integer award4 = Integer.parseInt(awards4.getText());

            String url="jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";

            connect = DriverManager.getConnection(url);

            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("update awards set award_name = ? where award_id = ? ");
            preparedStatement.setString(1,award2);
            preparedStatement.setString(2,award1);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }


    public void addPlayers(MouseEvent event) throws IOException, SQLException {
        try {
            String player1 = players1.getText();
            String player2 = players2.getText();
            String player3 = players3.getText();
            String player4 = players4.getText();
            String player5 = players5.getText();
            String player6 = players6.getText();
            String player7 = players7.getText();


            String url="jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";

            connect = DriverManager.getConnection(url);


            statement = connect.createStatement();


            System.out.println("*** INSERT ***");
            preparedStatement = connect.prepareStatement("insert into players values (?, ?, ?, ? ,?,?,?,?,?,?,?)");

            // Parameters start with 1
            preparedStatement.setString(1, player1);
            preparedStatement.setString(2, player2);
            preparedStatement.setString(3, player3);
            preparedStatement.setString(4, player4);
            preparedStatement.setString(5, player5);
            preparedStatement.setString(6, player6);
            preparedStatement.setString(7, player7);
            preparedStatement.setString(8, "null");
            preparedStatement.setString(9, "null");
            preparedStatement.setString(10, "null");
            preparedStatement.setString(11, "null");


            preparedStatement.executeUpdate();


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    public void deletePlayers(MouseEvent event) throws IOException, SQLException {
        try {
            String player1 = players1.getText();

            String url="jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";

            connect = DriverManager.getConnection(url);

            statement = connect.createStatement();

            System.out.println("*** DELETE ***");

            preparedStatement = connect.prepareStatement("delete from players where player_id = ? ");
            preparedStatement.setString(1,player1);
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    public void updatePlayers(MouseEvent event) throws IOException, SQLException {
        try {
            String player1 = players1.getText();
            String player2 = players2.getText();

            String url="jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";

            connect = DriverManager.getConnection(url);

            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("update players set family_name = ? where player_id = ? ");
            preparedStatement.setString(1,player2);
            preparedStatement.setString(2,player1);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }


    public void addTeams(MouseEvent event) throws IOException, SQLException {
        try {
            String team1 = teams1.getText();
            String team2 = teams2.getText();
            String team3= teams3.getText();
            String team4= teams4.getText();
            String team5= teams5.getText();


            String url="jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";

            connect = DriverManager.getConnection(url);


            statement = connect.createStatement();


            System.out.println("*** INSERT ***");
            preparedStatement = connect.prepareStatement("insert into teams values (?, ?, ?, ? ,?,?,?,?)");

            // Parameters start with 1
            preparedStatement.setString(1, team1);
            preparedStatement.setString(2, team2);
            preparedStatement.setString(3, team3);
            preparedStatement.setString(4, team4);
            preparedStatement.setString(5, team5);
            preparedStatement.setString(6, "null");
            preparedStatement.setString(7, "null");
            preparedStatement.setString(8, "null");


            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    public void deleteTeams(MouseEvent event) throws IOException, SQLException {
        try {
            String team1 = teams1.getText();


            String url="jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";

            connect = DriverManager.getConnection(url);

            statement = connect.createStatement();

            System.out.println("*** DELETE ***");

            preparedStatement = connect.prepareStatement("delete from teams where team_id = ? ");
            preparedStatement.setString(1,team1);
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    public void updateTeams(MouseEvent event) throws IOException, SQLException {
        try {
            String team1 = teams1.getText();
            String team2 = teams2.getText();

            String url="jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";

            connect = DriverManager.getConnection(url);

            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("update teams set team_name = ? where team_id = ? ");
            preparedStatement.setString(1,team2);
            preparedStatement.setString(2,team1);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void addStadiums(MouseEvent event) throws IOException, SQLException {
        try {
            String stadium1 = stadiums1.getText();
            String stadium2 = stadiums2.getText();
            String stadium3= stadiums3.getText();
            Integer stadium4 = Integer.parseInt(stadiums4.getText());

            String url="jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";

            connect = DriverManager.getConnection(url);


            statement = connect.createStatement();


            System.out.println("*** INSERT ***");
            preparedStatement = connect.prepareStatement("insert into stadiums values (?, ?, ?, ? ,?,?,?)");

            // Parameters start with 1
            preparedStatement.setString(1, stadium1);
            preparedStatement.setString(2, stadium2);
            preparedStatement.setString(3, stadium3);
            preparedStatement.setInt(4, stadium4);
            preparedStatement.setString(5, "null");
            preparedStatement.setString(6, "null");
            preparedStatement.setString(7, "null");

            preparedStatement.executeUpdate();


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    public void deleteStadiums(MouseEvent event) throws IOException, SQLException {
        try {
            String stadium1 = stadiums1.getText();
            String stadium2 = stadiums2.getText();

            String url="jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";

            connect = DriverManager.getConnection(url);

            statement = connect.createStatement();

            System.out.println("*** DELETE ***");

            preparedStatement = connect.prepareStatement("delete from stadiums where stadium_id = ? ");
            preparedStatement.setString(1,stadium1);
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    public void updateSatdiums(MouseEvent event) throws IOException, SQLException {
        try {
            String stadium1 = stadiums1.getText();
            String stadium2 = stadiums2.getText();

            String url="jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";

            connect = DriverManager.getConnection(url);

            statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("update satdiums set stadium_name = ? where stadium_id = ? ");
            preparedStatement.setString(1, stadium2);
            preparedStatement.setString(2, stadium1);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    //endregion

}
