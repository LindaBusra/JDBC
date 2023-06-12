package Batch159;

import java.sql.*;
import static org.junit.Assert.assertEquals;

public class MedunnaRoomDataBaseTest {

    public static void main(String[] args) throws SQLException {

        //1. Session JDBC RECAP Session(DT-NT) 11.06.2023
        //https://lms.techproeducation.com/mod/book/view.php?id=8813

        //1.step: Create Connection to get connected to DB
        Connection con = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2",
                "select_user", "Medunna_pass_@6");

        //2.step: Create Statement  -- to execute SQL queries
        Statement statement = con.createStatement();


        if(con!=null){
            System.out.println("Connection is successfully");
        } else {
            System.out.println("Connection filed");
        }


        //Select * from room where id = 60654;
        String sqlQuery = "SELECT * FROM room WHERE id = 60654";
        ResultSet resultSet = statement.executeQuery(sqlQuery);


        resultSet.next();

        String roomType = resultSet.getString("room_type");
        System.out.println("roomType = "  + roomType);

        int roomNumber = resultSet.getInt("room_number");
        System.out.println("roomNumber = " + roomNumber);

        int price = resultSet.getInt("price");
        System.out.println("price = " + price);

        String description = resultSet.getString("description");
        System.out.println("description = " + description);

        boolean status = resultSet.getBoolean("status");
        System.out.println("status = " + status);

        //get the created by value from the resultSet
        String created_by = resultSet.getString("created_by");
        System.out.println("created_by = " + created_by);


        //assertion is done for verification and by the help of junit dependency you can use assertEquals() to test if the
        //expected value is same with the existing value.

        assertEquals("TWIN", roomType);

        assertEquals(false,status);



    }
}
