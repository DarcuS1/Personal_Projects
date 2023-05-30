package data;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents an abstract Data Access Object (DAO) for a specific entity type.
 *
 * @param <T> the type of the entity
 */
public class AbstractDAO<T> {
    private static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    /**
     * Constructs an AbstractDAO instance and retrieves the class type of the objects managed by the DAO.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    /**
     * Finds an entity by its ID.
     *
     * @param id the ID of the entity to find
     * @return the found entity, or null if not found or an error occurs
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = selectByIdQuery(id);
        try {
            connection = DataConnection.upConn();
            statement = connection.prepareStatement(query);
            //  statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            DataConnection.close(connection, statement, resultSet);
        }
        return null;
    }
    /**
     * Finds all entities of the specified type.
     *
     * @return a list of all entities found, or an empty list if none found or an error occurs
     */
    public ArrayList<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<T> items = new ArrayList<>();

        try {
            connection = DataConnection.upConn();
            String query = selectAllQuery();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            items = new ArrayList<>(createObjects(resultSet));
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + " Data Access: findAll " + e.getMessage());
        } finally {
            DataConnection.close(connection, statement, resultSet);
        }

        return items;
    }
    /**
     * Generates the SELECT query for retrieving an object by ID.
     *
     * @param id the ID of the object to retrieve
     * @return the SELECT query as a string
     */
    private String selectByIdQuery(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        Field[] fields = type.getDeclaredFields();
        sb.append(" WHERE " + fields[0].getName() + " = " + id);

        return sb.toString();
    }

    /**
     * Generates the query for retrieving the number of entries in the database table.
     *
     * @return the COUNT query as a string
     */
    private String selectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());

        return sb.toString();
    }

    /**
     * Creates objects from the ResultSet.
     *
     * @param resultSet the ResultSet containing the data for the objects
     * @return a list of created objects
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();

                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);

                    // Set the field value using reflection
                    field.setAccessible(true);
                    field.set(instance, value);
                }

                list.add(instance);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException |
                 InvocationTargetException | NoSuchMethodException e) {
            LOGGER.log(Level.WARNING, "Data Access: createObjects " + e.getMessage());
        }

        return list;
    }
    /**
     * Generates the INSERT query for inserting a new object into the database.
     *
     * @return the INSERT query as a string
     */
    private String createInsertQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT ");
        queryBuilder.append("INTO ");
        queryBuilder.append(type.getSimpleName());
        queryBuilder.append(" VALUES (");
        return queryBuilder.toString();
    }
    /**
     * Verifies and formats the field value for the INSERT query.
     *
     * @param field the field being processed
     * @param value the value of the field
     * @return the formatted value as a string
     */
    public String generateInsertValueString(Field field, Object value) {
        String valueString = "";
        if (value instanceof String) {
            valueString = valueString + " '" + value.toString() + "',";
        } else {
            valueString = valueString + value.toString() + ",";
        }
        return valueString;
    }

    /**
     * Inserts a new entity into the database.
     *
     * @param t the entity to insert
     * @return the ID of the inserted entity, or 0 if insertion fails
     */
    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sb = createInsertQuery();
        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                sb = sb + generateInsertValueString(field, field.get(t));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb = sb.substring(0, sb.length() - 1) + ")";
        connection = DataConnection.upConn();
        try {
            statement = connection.prepareStatement(sb);
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DataConnection.close(connection, statement);
        }

        return 0;
    }
    /**
     * Generates the UPDATE query for updating an object in the database.
     *
     * @return the UPDATE query as a string
     */
    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        return sb.toString();
    }

    /**
     * Verifies and formats the field value for the UPDATE query.
     *
     * @param field the field being processed
     * @param value the value of the field
     * @return the formatted value as a string
     */
    public String verifUpdate(Field field, Object value) {
        String sb = "";
        if (value instanceof String) {
            sb = sb + field.getName() + "='" + value.toString() + "',";
        } else {
            sb = sb + field.getName() + "=" + value.toString() + ",";
        }
        return sb;
    }


    /**
     * Updates an entity in the database with new values.
     *
     * @param id    the ID of the entity to update
     * @param newT  the updated entity
     * @return true if the update is successful, false otherwise
     */
    public boolean update(int id, T newT) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sb = createUpdateQuery();
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getName().equals(fields[0].getName())) {

                    id = Integer.parseInt(field.get(newT).toString());

                } else {
                    sb = sb + verifUpdate(field, field.get(newT));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb = sb.substring(0, sb.length() - 1) + " WHERE " + fields[0].getName() + " = " + id;
        connection = DataConnection.upConn();
        try {
            statement = connection.prepareStatement(sb);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Data Access: update " + e.getMessage());
            return false;
        } finally {
            DataConnection.close(connection, statement);
        }
        return true;
    }

    /**
     * Deletes an entity from the database.
     *
     * @param id the ID of the entity to delete
     * @return true if the deletion is successful, false otherwise
     */
    public boolean delete(int id) {
        if (findById(id) == null) {
            return false;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataConnection.upConn();
            statement = connection.prepareStatement(generateDeleteQuery());
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Data Access: delete " + e.getMessage());
            return false;
        } finally {
            DataConnection.close(connection, statement);
        }
        return true;
    }
    /**
     * Generates the DELETE query for deleting an object from the database.
     *
     * @return the DELETE query as a string
     */
    private String generateDeleteQuery() {
        Field[] fields = type.getDeclaredFields();
        return "DELETE FROM " + type.getSimpleName() + " WHERE " + fields[0].getName() + " = ?";
    }
}
