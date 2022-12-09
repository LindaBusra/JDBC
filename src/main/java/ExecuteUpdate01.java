import java.sql.*;

public class ExecuteUpdate01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.Step : Registration  to the driver
        Class.forName("org.postgresql.Driver");

        //2.Step : Create a connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        //3.Step : Create statement
        Statement st = con.createStatement();


        //1.Example: Update the number of employees to 16000 if the number of employees is less than the average number of employees

        String sql = "UPDATE companies\n" +
                "SET number_of_employees=16000\n" +
                "WHERE number_of_employees<(SELECT AVG(number_of_employees) FROM companies)";


        int numOfRecordsUpdated = st.executeUpdate(sql);
        System.out.println("Updating records number is : " + numOfRecordsUpdated);

        String sql2 = "SELECT * FROM companies";
        ResultSet result = st.executeQuery(sql2);

        while (result.next()) {

            System.out.println(result.getInt(1) + "\t" + result.getString(2) + "\t" + result.getInt(3));
        }




        con.close();
        st.close();


    }
}
