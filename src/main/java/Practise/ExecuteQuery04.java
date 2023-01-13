package Practise;

import java.sql.*;

public class ExecuteQuery04 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        Statement st = con.createStatement();


//---------------------------------------------------------------------------------------------------------------------------


        System.out.println("\nExample-1");

        //1.Example:Select the country names whose region id's are 1
        String sql1 ="SELECT country_name FROM countries WHERE region_id=1";


        //If you want to see there is any records use execute methods
        //if you use execute method you will get just true or false, but I want to see the records.
        boolean r1 = st.execute(sql1);       //it will return true, because I will get many records
        System.out.println("execute() method returned: " + r1 + " for select query");


        //To see the records we have another method which is executeQuery();
        ResultSet result1 = st.executeQuery(sql1);    //executeQuery() returning ResultSet -->Set means unique data.in Database every record must be unique. You can not have some record repeatedly.
                                    //to store unique data Set is best option
        //Note:inside the ResultSet, there is result1 container, and there is many records. I want to read every one. 1st, 2nd .. I should use loops or iteration

        while(result1.next()) {
            System.out.println( result1.getString("country_name")) ;    //we can use column number
        }

//---------------------------------------------------------------------------------------------------------------------------


        System.out.println("\nExample-2");

        //2.Example:Select the country_id and country_names from countries table
        ResultSet result2=st.executeQuery("SELECT * FROM countries");

        while(result2.next()){
            System.out.println(result2.getString(1)+" "+result2.getString(2));
        }

//---------------------------------------------------------------------------------------------------------------------------


        System.out.println("\nExample-3");


        //3.Example:Select the country_ids and country names whose region id's are greater than 2.
        String sql3 ="SELECT country_id, country_name FROM countries WHERE region_id>2";
        ResultSet result3 = st.executeQuery(sql3);       //I want to go result3 and read every record with loop

        while(result3.next()){
            System.out.println(result3.getString("country_id")+" -> "+result3.getString("country_name"));
        }

//---------------------------------------------------------------------------------------------------------------------------


        System.out.println("\nExample-4");


        //3.Example:Select the worker whose salary is the lowest from workers table
        String sql4 ="SELECT*FROM workers WHERE salary=( SELECT MIN(salary) from workers)";
        ResultSet result4 = st.executeQuery(sql4);       //I want to go result3 and read every record with loop

        while(result4.next()){
            System.out.println(result4.getString("id") + " - " + result4.getString("name") +" - "+  result4.getInt("salary") );
        }

//---------------------------------------------------------------------------------------------------------------------------


        System.out.println("\nExample-5");


        //3.Example:Select the company  whose number_of_employees is the greatest from companies table.
        String sql5 ="SELECT*FROM companies WHERE number_of_employees=( SELECT MAX(number_of_employees) from companies)";
        ResultSet result5 = st.executeQuery(sql5);       //I want to go result3 and read every record with loop

        //Both column names or column indexes can be used inside the get methods.
        while(result5.next()){
            System.out.println(result5.getString("company_name") +" - "+  result5.getInt("number_of_employees") );
        }

//---------------------------------------------------------------------------------------------------------------------------



        //close the connection object
        con.close();
        st.close();
        result1.close();
        result2.close();
        result3.close();
        result4.close();
        result5.close();

    }

}
