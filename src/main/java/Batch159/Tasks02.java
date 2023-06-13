package Batch159;

import java.sql.*;

public class Tasks02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");  //Class.forName()  when we write a classname in forName(), we can use all methods in this class.
        // We don't need create an object to  call the methods.
        //We can use all methods in org.postgresql in  Driver class

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "12345");
        //select database, properties --> owner-->dev_user

        Statement st = con.createStatement();

        //DDL --> Data Definition Language -->create, alter, drop, truncate
//        System.out.println(st.execute("CREATE TABLE myStudents (std_id INT, std_name VARCHAR(15), std_grade INT)"));       //returns false
//
//        String sql1 = "INSERT INTO myStudents VALUES(1, 'Busra', 100)";
//        System.out.println(st.execute(sql1));


//     //check if we can get data from database, so result is true
//     String sql2 = "SELECT * FROM myStudents WHERE std_name='Busra'"  ;
//     System.out.println(st.execute(sql2));  //true
//
//     //We have no data who name is "ali", but we check if we can get data from database, so result is true. otherwise we can get exception
//     String sql3 = "SELECT * FROM myStudents WHERE std_name='Ali'"  ;
//     System.out.println(st.execute(sql3));  //true


//        String sql3 = "UPDATE myStudents SET std_grade=80 WHERE std_name='Fatih'"  ;
//        int updatedRows = st.executeUpdate(sql3);
//        System.out.println("updatedRows: " + updatedRows);


        //Execute Query
        String sql4 = "SELECT * FROM myStudents WHERE std_grade>80";
        ResultSet resultSet = st.executeQuery(sql4);

        while(resultSet.next()) {   //we can use do also.

            System.out.println(resultSet.getInt("std_id") + " - " + resultSet.getString(2) + " - " + resultSet.getInt("std_grade"));   //2:"std_name"
        }



        //Execute Query
        String sql5 = "SELECT std_name, std_grade FROM myStudents WHERE std_grade>80";
        ResultSet resultSet1 = st.executeQuery(sql5);

        while(resultSet1.next()) {   //we can use do also.

            System.out.println(resultSet1.getString(1) + " - " + resultSet1.getInt("std_grade"));   //2:"std_name"
        }

        resultSet.close();
        resultSet1.close();
        st.close();
        con.close();

    }
}
