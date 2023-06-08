package Batch159;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    //1. Session JDBC Intro Execute ExecuteQuery 05.06.2023
    //https://lms.techproeducation.com/mod/book/view.php?id=8525&chapterid=30534

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        //1.step: Register Driver - optional
        Class.forName("org.postgresql.Driver"); //if it can not find the driver, it throws exception


        //2.step: Create Connection to get connected to DB
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "12345");
                                    //which database we are going to connect, 5432 is port number, database name:dev_user, password:12345, I created database in pgAdmin

        //3.step: Create Statement  -- to execute SQL queries
        Statement statement = con.createStatement();


        //to test if we have created connection to DB
//        System.out.println("Connected successfully");


        //4.step: Execute SQL queries
        //TASK:Create a  table named "employee" with column names of: employee_id, employee_name, salary

        Boolean sql1 = statement.execute("CREATE TABLE employee(employee_id INT, employee_name VARCHAR(50),salary real)");
        System.out.println("sql1: " + sql1);


        /*execute() returns boolean value and can be used for DDL (Data Definition Language) or
        // DQL (Data Query Language)
        --if it is used with DDL it return false
        --if it is used with DQL it return true        */


        //TASK 2:add Varchar(20) column name "city" to employee table
        String query = "ALTER TABLE employee ADD COLUMN city VARCHAR(20)";
        Boolean sql2 = statement.execute(query);

        System.out.println("sql2: " + sql2);


        //TASK 3:Delete employee table from SCHEMA
        Boolean sql3 = statement.execute("DROP TABLE employee");
        System.out.println("sql3: " +  sql3);


        //5.step: close connection and statement
        statement.close();
        con.close();














    }
}
