package Practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Create_Alter_DropTable {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //Registration  to the driver
        Class.forName("org.postgresql.Driver");

        //Create a connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        //Create statement
        Statement st = con.createStatement();


        //1.example - Create a worker table with the columns worker_id, worker_name, worker_salary
        String sql1 = "CREATE TABLE workers (worker_id CHAR(3), worker_name VARCHAR(50), worker_salary NUMERIC(7,2))";
        boolean result1 = st.execute(sql1);    //for create table -->st.execute(sql);
        System.out.println("execute() method returned: " + result1 + " for table creation");   //I got false from here, it means no record returned


        //2.example-Alter table by adding worker_address column into the workers table
        String sql2 = "ALTER TABLE workers ADD worker_address VARCHAR(50)";
        boolean result2 = st.execute(sql2);    //it will give me false
        System.out.println("execute() method returned: " + result2 + " for table altering");


        //3.example-Drop workers table
//        String sql3 = "DROP TABLE workers";
//        boolean result3 = st.execute(sql3);    //it will give me false
//        System.out.println("execute() method returned: " + result3 + " for table dropping");

        //Close the con and st
        con.close();
        st.close();


    }
}
