package Practise;

import java.sql.*;

public class CallableStatement02 {


    public static void main(String[] args) throws ClassNotFoundException, SQLException, SQLException {

        //Registration  to the driver
        Class.forName("org.postgresql.Driver");

        //Create a connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        //Create statement
        Statement st = con.createStatement();

        //1.Example: Create a procedure to find the minimum one of 2 numbers
        String sql1 = "CREATE OR REPLACE PROCEDURE findMinP(a IN NUMBER, b IN NUMBER, c OUT NUMBER) IS BEGIN  IF a<b THEN c:=a; ELSE c:=b; END IF; END;";

        st.execute(sql1);

        CallableStatement cst1 = con.prepareCall("{ call findMinP( ?, ?, ? ) }");

        cst1.setInt(1, 5);
        cst1.setInt(2, 7);
        cst1.registerOutParameter(3, Types.INTEGER);

        cst1.execute();

        System.out.println(cst1.getInt(3));//5

        con.close();
        st.close();
    }
}
