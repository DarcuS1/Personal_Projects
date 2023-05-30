package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WorldCupDAO {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception {
        try {
           String url="jdbc:sqlite:C:/Users/gokuz/OneDrive/Desktop/worldcup.db";

            connect = DriverManager.getConnection(url);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query

            System.out.println("*** SELECT INITIAL ***");
            resultSet = statement.executeQuery("select * from awards");
            writeResultSet(resultSet);

            // PreparedStatements can use variables and are more efficient
            System.out.println("*** INSERT ***");
            preparedStatement = connect.prepareStatement("insert into awards values (?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, "3");
            preparedStatement.setString(2, "11111");
            preparedStatement.setString(3, "TestInsert");
            preparedStatement.setInt(4, 897);
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery("select * from awards");
            writeResultSet(resultSet);
            System.out.println("*** DELETE ***");

            preparedStatement = connect.prepareStatement("delete from awards where award_id = ? ");
            preparedStatement.setString(1,"3");
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery("select * from awards");
            writeResultSet(resultSet);

            System.out.println("*** COLUMNS NAMES ***");
            resultSet = statement.executeQuery("select * from awards");
            writeMetaData(resultSet);


            preparedStatement = connect.prepareStatement("update awards set award_id = 'A-2' where award_name = ? ");
            preparedStatement.setString(1,"Silver Ball");
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery("select * from awards");
            writeResultSet(resultSet);


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getString(2);
            String award_id = resultSet.getString("award_id");
            String award_name = resultSet.getString("award_name");
            String award_description = resultSet.getString("award_description");
            int year_introduced = resultSet.getInt("year_introduced");
            System.out.println("award_id: " + award_id);
            System.out.println("award_name: " + award_name);
            System.out.println("award_description: " + award_description);
            System.out.println("year_introduced: " + year_introduced);

        }
    }

    // You need to close the resultSet
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
}
