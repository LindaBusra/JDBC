import java.sql.*;

public class ExecuteQuery03 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //Registration  to the driver
        Class.forName("org.postgresql.Driver");

        //Create a connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        //Create statement
        Statement st = con.createStatement();

        //1.Example.  Find the company and number_of_employees whose number_of_employees is the second highest from the companies table

        System.out.println("\nBy using OFFSET-FETCH NEXT");
        String sql = "SELECT company, number_of_employees FROM companies\n" +
                "ORDER BY number_of_employees desc\n" +
                "OFFSET 1 ROW\n" +
                "FETCH NEXT 1 ROW ONLY";

        ResultSet result = st.executeQuery(sql);

        while(result.next()) {

            System.out.println(result.getString(1) + " - " + result.getInt(2));
        }




        System.out.println("\nBy using sub-query");
        String sql1 = "SELECT company, number_of_employees \n" +
                "FROM companies\n" +
                "WHERE number_of_employees=(SELECT  MAX(number_of_employees) FROM companies\n" +
                "\t\t\t\t\t\t  WHERE number_of_employees<\n" +
                "\t\t\t\t\t\t   (SELECT  MAX(number_of_employees) FROM companies))";

        ResultSet result1 = st.executeQuery(sql1);

        while(result1.next()) {

            System.out.println(result1.getString(1) + " - " + result1.getInt(2));
        }


        //2.Example :Find the company names and number_of_employees whose number is less than avarega number

        System.out.println("\nExample 2");
        String sql2 = "SELECT company, number_of_employees FROM companies\n" +
                "        WHERE number_of_employees<(SELECT  AVG(number_of_employees) FROM companies)";

        ResultSet result2 = st.executeQuery(sql2);

        while(result2.next()) {

            System.out.println(result2.getString(1) + " - " + result2.getInt(2));
        }


        con.close();
        st.close();


    }
}
