package Batch159;

import java.sql.*;

public class ExecuteUpdate {

    //2. Session JDBC Execute Update Prepared Statement 06.06.2023
    //https://lms.techproeducation.com/mod/book/view.php?id=8525&chapterid=30692

    public static void main(String[] args) throws SQLException {

        //1.step: Create Connection to get connected to DB
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "12345");


        //3.step: Create Statement  -- to execute SQL queries
        Statement statement = con.createStatement();

        //Check if connection is successfully
//    System.out.println("Connected successfully");


        //TASK-1. Update salaries of developers whose salaries are less than average salary with average salary

        String query1 = "UPDATE developers SET salary = (SELECT AVG(salary) FROM developers) WHERE salary<(SELECT AVG(salary) FROM developers)";

        //when I execute this class, each time it will update the salaries
        int updatedRows = statement.executeUpdate(query1); //executeUpdate() return int value which number of updated records
        System.out.println("Updated rows: " + updatedRows);


        //to see all records
        String query2 = "SELECT name, salary FROM developers";
        ResultSet resultSet2 = statement.executeQuery(query2);

        while (resultSet2.next()) {
            System.out.println(resultSet2.getString("name") + " -- " + resultSet2.getInt("salary"));
        }

        System.out.println("\n");





        //TASK-2. Add new developer to developers table

        String query3 = "INSERT INTO developers (id, name, salary, prog_lang) VALUES (21, 'Emily', 6000, 'Python'), (22, 'Hanna', 8000, 'JavaScript')";
        int numInserted = statement.executeUpdate(query3);
        System.out.println("Inserted rows: "+numInserted);

        String query4 = "SELECT name, salary FROM developers";
        ResultSet resultSet4 = statement.executeQuery(query4);

        while (resultSet4.next()) {
            System.out.println(resultSet4.getString("name") + " -- " + resultSet4.getInt("salary"));
        }

        System.out.println("\n");




        //TASK-3. DELETE row which has id of 14
        String query5 = "DELETE FROM developers WHERE id =14";
        int deletedRows = statement.executeUpdate(query5);
        System.out.println("DeletedRows: " + deletedRows);
        System.out.println("\n");



        //TASK-4. DELETE rows from developers table if  prog_lang is “Ruby”
        String query6 = "DELETE FROM developers WHERE prog_lang  LIKE 'Ruby'";
        int deletedRows2 = statement.executeUpdate(query6);
        System.out.println("DeletedRows: " + deletedRows2);
        System.out.println("\n");


        //4.step: close connection and statement
        statement.close();
        con.close();

    }
}