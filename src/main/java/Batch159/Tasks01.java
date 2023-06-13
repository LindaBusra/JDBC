package Batch159;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Tasks01 {

    //1. Session JDBC RECAP Session(DT-NT) 11.06.2023
    //https://lms.techproeducation.com/mod/book/view.php?id=8813

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        //1.step: Create Connection to get connected to DB
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "12345");
        //which database we are going to connect, 5432 is port number, database name:dev_user, password:12345, I created database in pgAdmin

        //2.step: Create Statement  -- to execute SQL queries
        Statement statement = con.createStatement();


        //Task 1:Create workers table
        String query ="CREATE TABLE workers(worker_id INT, worker_name VARCHAR(80),worker_address varchar(80))" ;

        boolean querySQL = statement.execute(query);  //it give me false

        System.out.println("querySQL =  " +  querySQL);

        //I use JDBC to test my data in backend side
        //we create this code one time and after that Jenkins will run them in the cloud services (AWS) with specific frequency?

    }
}
