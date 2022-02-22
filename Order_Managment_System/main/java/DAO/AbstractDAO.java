package DAO;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * The function executes a mysql query to select everything from a certain
     * table given some conditions
     * @param field string of the field whose elements are selected
     * @return the string of results of the query
     */

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM schooldb.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " like ");
        return sb.toString();
    }

    /**
     * The function goes first through the names of the table heads
     * recorded in the table to create the table heads and then taking all the
     * elements and adding them as rows
     * @param list list of elements from the table given
     * @return returns the table with the elements in the mysql table
     */

    public JTable findAll(List<T> list) {

        DefaultTableModel myModel = new DefaultTableModel();
        Vector<String> names = new Vector<String>(list.size());
        try{
            for(Field field: list.get(0).getClass().getDeclaredFields()){
                field.setAccessible(true);
                names.add(field.getName());
            }
            myModel.setColumnIdentifiers(names);
            for(Object o: list){
                Vector<String> rows = new Vector<String>(list.size());
                for(Field field: o.getClass().getDeclaredFields()){
                    field.setAccessible(true);
                    Object value;
                    value=field.get(o);
                    rows.add(value.toString());
                }
                myModel.addRow(rows);
            }
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        JTable myTable = new JTable(myModel);
        return myTable;
    }

    /**
     * The function executes a mysql query to find a object in the database that
     * has the given name or null otherwise
     * @param name string of the name that needs to be searched for
     * @return returns the object with the given name
     */

    public T findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("name");
        try {
            query = query + "'" + name + "'";
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Improper input format", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * The function executes a mysql query then creates for each an object and
     * adds it to the list
     * @param resultSet result of the executed query
     * @return returns a list of the created objects
     */

    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance,value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * The function executes a mysql query to insert the object given as parameter
     * into the mysql table
     * @param t the abstract parameter to be inserted into the mysql database table
     */

    public void insert(T t) {
        String nameTable=t.getClass().getSimpleName();
        String sb = null;
        sb = sb + "INSERT INTO schooldb." + nameTable + "(";
        try{
            for(Field field: t.getClass().getDeclaredFields()){
                field.setAccessible(true);
                sb = sb + field.getName()+"," + "";
            }
            sb = sb.substring(4, sb.length() - 1);
            sb = sb + ")"+"VALUES"+ "('";

            for(Field field: t.getClass().getDeclaredFields()){
                field.setAccessible(true);
                Object value;
                value = field.get(t);
                sb = sb + value + "'," + "'";
            }
            sb = sb.substring(0,sb.length() - 2);
            sb = sb + ");";
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        StringBuilder query = new StringBuilder();
        query.append(sb);
        connect(query);
    }

    /**
     * The function executes a mysql query to delete the object given as parameter
     * from the mysql table
     * @param t the abstract parameter to be deleted from the mysql database table
     */

    public void delete(T t){
        String nameTable=t.getClass().getSimpleName();
        String sb = null;
        try{
            for(Field field: t.getClass().getDeclaredFields()){
                field.setAccessible(true);
                Object value;
                if(field.getName().compareTo("id")==0){
                    value = field.get(t);
                    sb = sb + "DELETE FROM schooldb." + nameTable + " WHERE (id='" + value + "');" ;
                }
            }
            sb = sb.substring(4, sb.length() - 1);
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        StringBuilder query = new StringBuilder();
        query.append(sb);
        connect(query);
    }

    /**
     * The function executes a mysql query to update the object given as parameter
     * in the mysql table
     * @param t the abstract parameter to be updated in the mysql database table
     */

    public void update(T t) {
        String nameTable=t.getClass().getSimpleName();
        String sb = null;
        sb = sb + "UPDATE schooldb." + nameTable + " SET ";
        try{
            for(Field field: t.getClass().getDeclaredFields()){
                field.setAccessible(true);
                Object value;
                value=field.get(t);
                if(value.equals(0)==false && field.getName().compareTo("id")!=0){
                    sb = sb + field.getName()+"=" + "'"+value+"',";
                }
            }
            sb = sb.substring(4, sb.length() - 1); sb = sb + " WHERE " + "(";
            for(Field field: t.getClass().getDeclaredFields()){
                field.setAccessible(true);
                Object value;
                value = field.get(t);
                if(field.getName().compareTo("id")==0){
                    sb = sb + field.getName() + "=" + "'"+ value + "',";
                }
            }
            sb = sb.substring(0,sb.length() - 1); sb = sb + ");";
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        StringBuilder query = new StringBuilder();
        query.append(sb);
        connect(query);
    }

    /**
     * The function receives a query as a parameter and then executes the quert and closes the connection
     * @param query the query to be executed
     */

    public void connect(StringBuilder query){
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement prepInsertStatement = con.prepareStatement(query.toString());
            prepInsertStatement.executeUpdate();
            con.close();
            prepInsertStatement.close();
            JOptionPane.showMessageDialog(null, "Record updated succesfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception when executing insert query");
            e.printStackTrace();
        }
    }
}

