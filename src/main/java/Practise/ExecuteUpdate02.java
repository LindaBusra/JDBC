package Practise;

import java.sql.*;

public class ExecuteUpdate02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        Statement st = con.createStatement();



        //Example : Update the company names to *****  if the number of employees is the highest. After updating
        //se updated table on the console.

        String sql2 = "UPDATE companies\n" +
                "SET company_name= '*****'\n" +
                "WHERE number_of_employees=(SELECT MAX(number_of_employees) FROM companies)";

        int numOfUpdateRecords2 = st.executeUpdate(sql2);
        System.out.println("\nnumOfUpdateRecords2 : " + numOfUpdateRecords2);



        //for to see updated table
        ResultSet result2 = st.executeQuery("SELECT * FROM companies");

        while(result2.next()) {

            System.out.println(result2.getInt(1) + "-" + result2.getString(2) + "-" + result2.getInt(3) );
        }





    }

}
