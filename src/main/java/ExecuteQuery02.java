import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.Step : Registration  to the driver
        Class.forName("org.postgresql.Driver");

        //2.Step : Create a connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        //3.Step : Create statement
        Statement st = con.createStatement();


//1.Example: Find the company and number_of_employees whose number_of_employees is the second highest from the companies table

        String sql1 ="SELECT number_of_employees, company_name  \n" +
                "FROM companies \n" +
                "ORDER BY number_of_employees DESC\n" +
                "OFFSET 1 ROW\n" +
                "LIMIT 1";


        ResultSet result1 = st.executeQuery(sql1);

        while(result1.next()) {

            System.out.println(result1.getInt(1)  + " - " + result1.getString(2)   );
        }

        String sql2 = "SELECT number_of_employees, company_name  \n" +
                "FROM companies \n" +
                "WHERE number_of_employees=(SELECT MAX(number_of_employees) FROM companies WHERE number_of_employees <\n" +
                "\t\t\t\t\t\t  (SELECT MAX(number_of_employees) FROM companies))";

        ResultSet result2 = st.executeQuery(sql1);

        while(result2.next()) {

            System.out.println(result2.getInt(1)  + " - " + result2.getString(2)   );
        }



        //2.Example: Find the company names and number of employees whose number of employees is less than the average number of employees

        String sql3 = "SELECT company_name, number_of_employees FROM companies WHERE number_of_employees<(SELECT AVG(number_of_employees) from companies)";

        ResultSet result3 = st.executeQuery(sql3);

        while(result3.next()) {

            System.out.println(result3.getString(1) + " - " + result3.getInt(2)  );
        }



        con.close();
        st.close();


    }
}
