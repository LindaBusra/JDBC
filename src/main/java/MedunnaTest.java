


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MedunnaTest {
    /*
   Given
     User connects to the database
     (Host name: medunna.com, Database name: medunna_db, Usename: medunna_user, Password: medunna_pass_987))

   When
     User sends the query to get the names of created_by column from "room" table

   Then
     Verify that there are some rooms created by "john doe".

   And
     User closes the connection
  */

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;


    public static void createConnection() {
        String url = "jdbc:postgresql://medunna.com:5432/medunna_db";
        String username = "medunna_user";
        String password = "medunna_pass_987";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}

