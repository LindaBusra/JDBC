package Practise.Querys;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtils {


    //We are goin to create a method to handle registration part. I have handled my exceptions
    private static Connection connection;
    private static Statement statement;

    public static Connection connectToDatabase(String hostName, String dataBaseName, String userName, String password) {


        //1.Step Registration  to the driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        //2.Step Create a connection with database
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":5432/" + dataBaseName, userName, password);   //localhost-TechPro-postgres-password can cahnge
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Connection is successful!");
        return connection;

    }


    //3.Step Create statement
    public static Statement createStatement() {

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Statement created!");
        return statement;

    }


    //4.Execute the query

    public static boolean execute(String query) {

        boolean isExecuted = false;

        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Command executed succsesfully!");
        return isExecuted;
    }


    //5.Step: Close the connection and statement

    public static void closeConnectionAndStatement() {

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            if (connection.isClosed() && statement.isClosed()) {
                System.out.println("Connection and statement closed");
            } else {
                System.out.println("Connection and statement not closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    //Method for Drop table

    public static void dropTable(String tableName) {

        try {
            statement.execute("Drop Table " + tableName);
            System.out.println("Table " + tableName + " dropped!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //create a table by using createTable() method
    public static void createTable(String tableName, String... columnName_DataType) {

        StringBuilder columnName_DataTypeString = new StringBuilder("");

        for (String w : columnName_DataType) {

            columnName_DataTypeString.append(w).append(",");

        }

        columnName_DataTypeString.deleteCharAt(columnName_DataTypeString.lastIndexOf(","));

        try {
            statement.execute("CREATE TABLE " + tableName + "(" + columnName_DataTypeString + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Table created successfully!");

    }


    //INSERT INTO tablename  (columnName1, columnName2 ...) VALUES (value1, value2 ...)
    public static void insertDataIntoTable(String tablename, String... columnName_Values) {

        StringBuilder columnName = new StringBuilder("");
        StringBuilder value = new StringBuilder("");

        for (String w : columnName_Values) {

            columnName.append(w.split(" ")[0]).append(",");
            value.append(w.split(" ")[1]).append(",");
        }

        columnName.deleteCharAt(columnName.lastIndexOf(","));
        value.deleteCharAt(value.lastIndexOf(","));

        String command = "INSERT INTO " + tablename + "(" + columnName + ")" + "VALUES (" + value + ")";

        try {
            statement.execute(command);
            System.out.println("Data inserted successfully into " + tablename);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
