package Practise.Querys;

public class Runner {

    public static void main(String[] args) {


        //1.Step Registration  to the Driver
        //2.Step Create connection with database

        JDBCUtils.connectToDatabase("localhost", "TechPro", "postgres", "12345");


        //3.Step Create statement
        JDBCUtils.createStatement();


        //4.Execute the query  --> create table
//        JDBCUtils.execute("CREATE TABLE workers(worker_id VARCHAR(10), worker_name VARCHAR(50), worker_salary INT)");
//        System.out.println("Table created");

        //4.Execute the query  --> createTable() method with parameters
//        JDBCUtils.createTable("Students","name VARCHAR(20)","id INT","address VARCHAR(50)","tel NUMERIC");
//
//        //Insert data into table
//        JDBCUtils.insertDataIntoTable("Students", "name 'Saly Helsen'", "id 212", "address 'Oslo-Norway'", "tel 123456");

        JDBCUtils.createTable("School", "teachers VARCHAR(50)", "name VARCHAR(80)", "schoolId NUMERIC(3)");

        JDBCUtils.insertDataIntoTable("School", "name 'Tom'");

        //Using dropTable() method
        JDBCUtils.dropTable("School");

        //5.Step: Close the connection and statement
        JDBCUtils.closeConnectionAndStatement();


    }
}
