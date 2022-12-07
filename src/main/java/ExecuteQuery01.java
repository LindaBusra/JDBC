import java.sql.*;
import java.util.HashMap;

public class ExecuteQuery01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.Step : Registration  to the driver
        Class.forName("org.postgresql.Driver");    //what if I can not find it-->it throws exception

        //2.Step : Create a connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");
        //throws exception-->what if I can not find connection
        //3.Step : Create statement
        Statement st = con.createStatement();



        //1.Example: Select the country names whose region id's are 1
        String sql1 = "SELECT country_name FROM countries WHERE region_id=1";

        //If you use execute() method, you will get true or false as return. But I want to see records.
        boolean result = st.execute(sql1);
        System.out.println(result);     //true

        //To see the records we have another method which is "executeQuery()"
        ResultSet result1 = st.executeQuery(sql1);

        while(result1.next()) {
            System.out.println(result1.getString(1) );      //or column name : country_name
        }



        //2.Example: Select the country_id and country_name whose region_id's are greater than 2.
        String sql2 = "SELECT country_id, country_name FROM countries WHERE region_id>2";

        ResultSet result2 = st. executeQuery(sql2);  //executeQuery() return us ResultSet

        while(result2.next()) {

            System.out.println(result2.getString("country_id") + " - " + result2.getString("country_name"));
        }


        //3.Example: Select the company whose number_of_employees is the lowest from companies table
        String sql3 = "SELECT company FROM companies WHERE number_of_employees=(SELECT MIN(number_of_employees) FROM companies)";

        ResultSet result3 = st.executeQuery(sql3);

        result3.next();     //I dont need while, because I have just a record.
        System.out.println(result3.getString("company"));


        //or for to take all columns
        //Same Example: Select all columns whose number_of_employees is the lowest from companies table
        String sql4 = "SELECT * FROM companies WHERE number_of_employees=(SELECT MIN(number_of_employees) FROM companies)";

        ResultSet result4 = st.executeQuery(sql4);

        while(result4.next()) {

            System.out.println(result4.getInt(1) + "\t" + result4.getString(2) + "\t" + result4.getInt(1) );
        }


        st.close();
        con.close();
        result1.close();
        result2.close();
        result3.close();
        result4.close();


    }
}
