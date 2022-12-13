import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

    //We will prepare a query and use it again and again-->resubulity
    //For make our query dynamic we use preparedStatement.

        //Registration  to the driver
        Class.forName("org.postgresql.Driver");

        //Create a connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        //Create statement
        Statement st = con.createStatement();


        //1. Example : Update  the number of employees to 9999 if the company name is IBM by using prepared statement.
        //1.Step-Create prepared statement query
        String sql1= "UPDATE companies SET number_of_employees = ? WHERE company_name = ?";      //for make my query dynamic, like a container in java

        //normally we were using statement object for normal query, men if it is Prepared Statement Query we use con object.

        //2.Step-Create PreparedStatement object
        PreparedStatement pst1 = con.prepareStatement(sql1);


        //3.Step-Assign the values by using "setInt(), setString() .." methods.   Set the values for ? marks
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");


        //4.Step-Execute the query
        int numOfUpdateRecords = pst1.executeUpdate();  //I used executeUpdate, because my query is update query
        System.out.println( numOfUpdateRecords + " rows updated");

        //for to see updated table
        String sql2 = "SELECT * FROM companies";
        ResultSet result2 = st.executeQuery(sql2);

        while(result2.next()) {
            System.out.println(result2.getInt(1) + " - " + result2.getString(2) + " - " + result2.getInt(3));

        }



        //2.Example: Update the number of employees to 5555 if the company name is GOOGLE by using prepared statement

        pst1.setInt(1,5555);
        pst1.setString(2, "GOOGLE");

        int numOfUpdateRecords1 = pst1.executeUpdate();
        System.out.println("numOfUpdateRecords: " + numOfUpdateRecords1);

        //for to see table
        String sql3 = "SELECT * FROM companies";
        ResultSet result3 = st.executeQuery(sql3);

        while(result3.next()) {
            System.out.println(result3.getInt(1) + " - " + result3.getString(2) + " - " + result3.getInt(3));

        }

        /*
        Note: ? mark in prepared statements can be used just for columns, it can not be used for tables name.!!!
         */

        result2.close();
        result3.close();
        pst1.close();
        con.close();
        st.close();


    }
}
