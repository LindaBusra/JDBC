


public class Runner {
    public static void main(String[] args) {
        //1. Step: Registration to the driver
        //2. Step: Create connection with database
        JdbcUtils.connectToDatabase("localhost","TechPro","postgres","12345");

        //3. Step: Create statement
        JdbcUtils.createStatement();

        //4. Step: Execute the query

//        JdbcUtils.createTable("Students","name VARCHAR(20)","id INT","address VARCHAR(50)","tel BIGINT");
//
//        JdbcUtils.insertDataIntoTable("Students","name 'John'");
//        JdbcUtils.insertDataIntoTable("Students","name 'Mark'", "id 123", "address 'Tokyo'", "tel 123456789");



        JdbcUtils.createTable("Schools","teachers VARCHAR(20)","name VARCHAR(50)","schoolId NUMERIC(3)");

        JdbcUtils.insertDataIntoTable("Schools","teachers 'Emily'", "name 'Emily Solvang'", "schoolId 132");
        JdbcUtils.insertDataIntoTable("Schools","teachers 'Tom'", "name 'Tom Hanks'", "schoolId 132");


        // JdbcUtils.dropTable("Students");

        //5. Step: Close the connection and statement
        JdbcUtils.closeConnectionAndStatement();




    }
}