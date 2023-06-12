package Batch159;

//3. Session JDBC Transaction, MiniProject 07.06.2023
//https://lms.techproeducation.com/mod/book/view.php?id=8525&chapterid=30942

import java.sql.*;

public class Transaction01 {

    public static void main(String[] args) throws Exception {

        //1.step: Create Connection to get connected to DB
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "12345");

        //2.step: Create Statement  -- to execute SQL queries
        Statement statement = con.createStatement();

        //Check if connection is successfully

        //We created accounts table in pgAdmin

        //TASK-1. Transfer amount of 1000 from account_nu:1234 to account_nu:5678

        //by default it is true
        con.setAutoCommit(false);       //Disable auto-commit mode
        Savepoint sp = null;

        try{

            /*  suppose we have other queries    */
            sp = con.setSavepoint("transfer_save");  //rollback will start from here

            String query = "UPDATE accounts SET amount = amount + ? WHERE account_nu = ?";

            //create prepared statement
            PreparedStatement prs = con.prepareStatement(query);

            //first update query starts here
            prs.setInt(1, -1000);   //subtract $1000 from Account Fred;
            prs.setInt(2, 1234);
            prs.executeUpdate();
            //first update query ends here

            //since it is always true, exception will be thrown
            //suppose we have some problem while updating second update query

            if(false){
                throw  new Exception();
            }

            //after exception this update will not run
            //second update query starts here
            prs.setInt(1, 1000);    //add $1000 to account Bernie
            prs.setInt(2, 5678);
            prs.executeUpdate();
            System.out.println("Transfer is Successfully ....");

            //second update query ends here
            con.commit();       //we have set this to false, now we are committing manually
            prs.close();
            statement.close();
            con.close();

        } catch(Exception e) {
            con.rollback(sp); //cancels all previous activities
            System.out.println("Transfer is failed .");
        }





    }
}