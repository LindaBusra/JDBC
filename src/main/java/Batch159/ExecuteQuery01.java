package Batch159;

import java.sql.*;

public class ExecuteQuery01 {

    //1. Session JDBC Intro Execute ExecuteQuery 05.06.2023
    //https://lms.techproeducation.com/mod/book/view.php?id=8525&chapterid=30534


    public static void main(String[] args) throws SQLException {

        //all table are in the file JDBC_05.06.2023,  in pgAdmin

    //1.step: Create Connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "12345");

    //2.step: Create Statement  -- to execute SQL queries
        Statement statement = con.createStatement();


    //TASK-1. GET/SELECT  "country_name" from countries table with ID between 5 and 10

    String query1 = "SELECT country_name FROM countries WHERE id BETWEEN 5 AND 10";
    Boolean sql1 = statement.execute(query1);
    System.out.println("sql1: " + sql1);        //if we get some data it returns true, in previous lesson, when we created table it returned false.

    //for get the data
    ResultSet resultSet = statement.executeQuery(query1);   //a list of result


    while (resultSet.next()) {
        System.out.println(resultSet.getString("country_name"));    //get column by columnLabel
        // System.out.println(resultSet.getString(1)); //get column by index number.
    }

    System.out.println("\n");

    //ResultSet has method such first(), last(), next()
    //There is no method to iterate backward



    //TASK - 2: Get "phone_code" and "country_name" from countries table,
    // whose phone code is greater than 200

        String query2 = "SELECT phone_code,country_name FROM countries WHERE phone_code>200";

        ResultSet resultSet2 = statement.executeQuery(query2);

        while(resultSet2.next()) {
            System.out.println(resultSet2.getInt("phone_code") + " -- " +
                    resultSet2.getString("country_name"));
        }
        System.out.println("\n");




    //TASK-3. Get all information about the developers whose salary is lowest
        String query3 = "SELECT * FROM developers WHERE salary = (SELECT MIN(salary) FROM developers)";

        ResultSet resultSet3 = statement.executeQuery(query3);
        while(resultSet3.next()) {
            System.out.println(resultSet3.getInt("id")+ "--" + resultSet3.getString("name")
            + "--" + resultSet3.getDouble("salary") + "--" + resultSet3.getString("prog_lang"));
        }

        System.out.println("\n");



        //TASK - 4 : Display students' name and grade whose grades are higher than average passing grade of departments.

        String query4 = "SELECT name, grade FROM students WHERE grade>(SELECT AVG(pass_grade) FROM  departments)";


        ResultSet resultSet4 = statement.executeQuery(query4);

        while(resultSet4.next()) {
            System.out.println(resultSet4.getString("name") + "--" + resultSet4.getInt("grade"));
        }


        //close the connection and statement
        statement.close();
        con.close();



    }
}
