package Batch159;

import java.sql.*;

public class PreparedStatement01 {
    //2. Session JDBC Execute Update Prepared Statement 06.06.2023
    //https://lms.techproeducation.com/mod/book/view.php?id=8525&chapterid=30692

    public static void main(String[] args) throws SQLException {


        //1.step: Create Connection to get connected to DB
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "12345");

        //3.step: Create Statement  -- to execute SQL queries
        Statement statement = con.createStatement();

        //Check if connection is successfully
//    System.out.println("Connected successfully");



        //TASK-1. Update pass_grade to 475 of Mathematics department (use PreparedStatement)
        //normal string query
        //String query1 = "UPDATE departments SET pass_grade = 475 WHERE department LIKE 'Mathematics'";

        //parameterized query
        String query1 = "UPDATE departments SET pass_grade=? WHERE department LIKE ?";

        //create prepared statement
        PreparedStatement prs = con.prepareStatement(query1);

        //set values for the parameters
        prs.setInt(1,475);
        prs.setString(2,"Mathematics");

        //execute the prepared statement
        int numOfUpdatedRows = prs.executeUpdate();
        System.out.println("numOfUpdatedRows: " + numOfUpdatedRows );




        //TASK-2. Update pass_grade to 455 of Literature department (use PreparedStatement)

        prs.setInt(1, 455);
        prs.setString(2, "Literature");

        int numOfUpdatedRows2 = prs.executeUpdate();
        System.out.println("numOfUpdatedRows2: " + numOfUpdatedRows2);



        //4.step: close connection and statement
        prs.close();
        statement.close();
        con.close();


    }
}
