package Practise;

import java.sql.*;

public class ExecuteQuery05 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {



      //Registration  to the driver
      Class.forName("org.postgresql.Driver");

      //Create a connection with database
      Connection con =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

      //Create statement  -->this method returns statement object, by using con object, I access to createStatement(),
      //and createStatement() creates Statement object.
      Statement st = con.createStatement();


        //just execute -->DDL : Data Definition Language  -->create and modify object
        //executeQuery -->DQL : Data Query Language  -->for read (SELECT)
        //execute update -->DML :Data Manipulation Language -->for create, update, insert



       //Example 1 - Find the name and the salary of the worker whose salary is the third highest from the workers table

      String sql2 = "SELECT name, salary FROM workers ORDER BY salary DESC OFFSET 2 ROW FETCH NEXT 1 ROW ONLY";

        //Or
//        String sql2 = "SELECT name, salary FROM workers ORDER BY salary DESC LIMIT 1 OFFSET 2";
        System.out.println("\nExample2");
        ResultSet result2 = st.executeQuery(sql2);

        while(result2.next()) {         //asking to pointer, if there is an element after pointer

            System.out.println(result2.getString("name") + " - " + result2.getInt("salary"));
        }




      con.close();
      st.close();

    }
}
