package Batch159;

import java.sql.*;

public class PreparedStatement02 {

    //2. Session JDBC Execute Update Prepared Statement 06.06.2023
    //https://lms.techproeducation.com/mod/book/view.php?id=8525&chapterid=30692

    public static void main(String[] args) throws SQLException {


        //1.step: Create Connection to get connected to DB
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "12345");

        //3.step: Create Statement  -- to execute SQL queries
        Statement statement = con.createStatement();

        //Check if connection is successfully
//    System.out.println("Connected successfully");


    //TASK-1. Using prepared statement, delete students who are from Management department, from students table.

        String query = "DELETE FROM students WHERE department LIKE ?";
        PreparedStatement prs = con.prepareStatement(query);

        prs.setString(1, "Management");
        int deletedRows = prs.executeUpdate();
        System.out.println("DeletedRows: " + deletedRows);


    //TASK-2. Insert software engineering department using prepared statement into departments table.
    // (id = 5006, department =software engineering,  pass_grade=475, campus='South')

        String query1 = "INSERT INTO departments VALUES ( ?, ? , ?, ?)";
        PreparedStatement prs2 =  con.prepareStatement(query1);

        prs2.setInt(1, 5006);
        prs2.setString(2, "Software_engineering");
        prs2.setInt(3, 475);
        prs2.setString(4, "South");

        int insertedRows = prs2.executeUpdate();
        System.out.println("Inserted rows: "+ insertedRows);


        //to see all records
        String query2 = "SELECT* FROM departments";
        ResultSet resultSet = statement.executeQuery(query2);


        while(resultSet.next()) {

            System.out.println(resultSet.getInt("dept_id") + " -- " +
                    resultSet.getString("department") + " -- " +
                    resultSet.getInt("pass_grade") + " -- " +
                    resultSet.getString("campus") );
        }



        //4.step: close connection and statement
        prs.close();
        prs2.close();
        statement.close();
        con.close();





    }
}