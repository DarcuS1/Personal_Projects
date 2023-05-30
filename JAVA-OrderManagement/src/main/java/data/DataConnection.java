package data;


import java.sql.*;
/**
 * The DataConnection class is responsible for managing the database connection and providing methods to establish,
 * close, and handle database connections.
 */
public class DataConnection {

    private static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/pt3db";
    private static final String dbUser = "postgres";
    private static final String dbPass = "0911";

    private static DataConnection newMultipleConn = new DataConnection();
    /**
     * Private constructor to prevent direct instantiation of the DataConnection class.
     * It initializes the JDBC driver by loading the driver class.
     */
    private DataConnection() {
        try {
            Class.forName(jdbcDriver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Retrieves a connection to the database by calling the connect() method.
     *
     * @return the Connection object representing the database connection
     */
    public static Connection upConn() {
        return connect();
    }
    /**
     * Establishes a connection to the database using the JDBC driver, URL, username, and password.
     *
     * @return the Connection object representing the database connection
     */
    private static Connection connect() {
        Connection newConn = null;
        try {
            newConn = DriverManager.getConnection(url, dbUser, dbPass);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newConn;
    }
    /**
     * Closes the database connection, statement, and result set.
     *
     * @param conn the Connection object representing the database connection
     * @param stat the Statement object representing the database statement
     * @param res  the ResultSet object representing the database result set
     */
    public static void close(Connection conn, Statement stat, ResultSet res) {
        try {
            conn.close();
            stat.close();
            res.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    /**
     * Closes the database connection and prepared statement.
     *
     * @param dbConnection the Connection object representing the database connection
     * @param statement    the PreparedStatement object representing the prepared statement
     */
    public static void close(Connection dbConnection, PreparedStatement statement) {
        try {
            dbConnection.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
