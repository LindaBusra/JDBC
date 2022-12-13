import java.sql.*;

public class CallableStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {



        /*
        If you create a function, and if you call and use it again and again, it is called "Callable Statement"

        In Java, if the return type of method is "any data type" or "void", we name all as methods.
        But in SQL, if something returns any data it is called "function", if something is not return any data it is called "procedure".
         */

        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        Statement st = con.createStatement();


        //1.Example: Create a function which uses 2 parameters and returns the sum of the parameters.
        //Function return data... Procedure-->it returns nothing.


        //1.Step: Type code to create function in SQL               //(variable name, data type) -->in SQL we have NUMBER, CHAR, BLOOP, DATE data types.
        String sql1 ="CREATE OR REPLACE FUNCTION additionF(x NUMERIC, y NUMERIC)\n" +
                "RETURNS NUMERIC \n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";

        //sql language
        //I created an add function. if I used add() and if I put e new record--> If we use  just create function, and if we have same function when we save it, it says you have this function
        //actually I just made update, I dont create a new function. but SQL will think it is a new function.
        //"There is an add function. Give another name" -->it will give me error. you have to save same
        //function with different name.
        //Create or Replace-->use the last version, yes it is same function but it is last version, save it.


        //2.Step: Execute sql1 to create the addf() function
        boolean result1 = st.execute(sql1);     //This is just creation, so we need just execute(),  execute() will return false
        System.out.println("execute(sql1) returns " + result1);


        //How to call a function
        //3.Step: Prepare calleble statement - Call the function and use it
        CallableStatement cst1 = con.prepareCall("{?=call additionF(?,?)}");          // ?:result   ?:num1, ?:num2


        //4.Step: Do assignment for ?.   Use registerOutParameter() method for result container, and use set() methods for parameters.
        cst1.registerOutParameter(1, Types.NUMERIC);            //java language
        cst1.setInt(2, 6);          //call a created function
        cst1.setInt(3, 4);

        //5.Step:Use execute method() to get result for the specific values,  Execute the function
        cst1.execute();

        //6.Step: To see result on the console use syso
        System.out.println("result is : " + cst1.getObject(1));


 //-----------------------------------------------------------------------------------------------------------------------------



        //2.Example: Create a function which calculates the volume of cone

        String sql2 ="CREATE OR REPLACE FUNCTION volumeOfConeF(r NUMERIC, h NUMERIC)\n" +
                "RETURNS NUMERIC \n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;\n" +
                "\n" +
                "END\n" +
                "$$";

        st.execute(sql2);

        CallableStatement cst2 = con.prepareCall("{?=call volumeOfConeF(?,?)}");          // ?:result   ?:num1, ?:num2


        //4.Step: Do assignment for ?.   Use registerOutParameter() method for result container, and use set() methods for parameters.
        cst2.registerOutParameter(1, Types.NUMERIC);            //java language
        cst2.setInt(2, 2);
        cst2.setInt(3, 5);

        //5.Step:Use execute method() to get result for the specific values,  Execute the function
        cst2.execute();

        //6.Step: To see result on the console use syso
        System.out.printf("%.2f" , cst2.getObject(1));



        con.close();
        st.close();
        cst2.close();
        cst1.close();

    }
}
