package Batch159;

import java.sql.*;

public class ExecuteQuery02 {

    //2. Session JDBC Execute Update Prepared Statement 06.06.2023
    //https://lms.techproeducation.com/mod/book/view.php?id=8525&chapterid=30692

    public static void main(String[] args) throws SQLException {

    //1.step: Create Connection to get connected to DB
    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "12345");


    //3.step: Create Statement  -- to execute SQL queries
    Statement statement = con.createStatement();

    //Check if connection is successfully
//    System.out.println("Connected successfully");


    //Task-1: Print department name and grade of department which has second highest pass_grade
    String query1 = "SELECT department, pass_grade FROM departments ORDER BY pass_grade DESC LIMIT 1 OFFSET 1";

    ResultSet resultSet1 = statement.executeQuery(query1);

    while(resultSet1.next()) {

        System.out.println("Second max pass_grade : " + "Department: " + resultSet1.getString("department") + " -- " + resultSet1.getInt("pass_grade"));
    }


    //Task-2: Print department name and pass_grade of department which has second highest pass_grade using sub-query

    String query2 = "SELECT department, pass_grade FROM departments WHERE pass_grade= (SELECT MAX(pass_grade) FROM departments WHERE pass_grade<(SELECT MAX(pass_grade) FROM departments))";

    ResultSet resultSet2 = statement.executeQuery(query2);

    while(resultSet2.next()) {

            System.out.println("Second max pass_grade : " + resultSet2.getString("department") + "--" +  resultSet2.getInt("pass_grade"));
        }

        System.out.println("\n");


    //Task-3: List department name, campus and highest grades of students from every department

    String query3 ="SELECT department, campus, (SELECT MAX(grade) FROM students s WHERE d.department = s.department) AS max_grade FROM departments d";

    ResultSet resultSet3 = statement.executeQuery(query3);

    while(resultSet3.next()) {

        System.out.println(resultSet3.getString("department") + " -- " +
                resultSet3.getString("campus") + " -- " +
                resultSet3.getInt("max_grade"));
    }



    //4.step: close connection and statement
    statement.close();
    con.close();



    }

}
