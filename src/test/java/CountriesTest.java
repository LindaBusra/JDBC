import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountriesTest {
    /*
      Given
        User connects to the database

      When
        User sends the query to get the region ids from "countries" table

      Then
        Verify that the number of region ids greater than 1 is 17.

      And
        User closes the connection


        Gorkien languages-->given-when-then-and
     */
    @Test
    public void countryTest(){

        //User connects to the database
        JdbcUtils.connectToDatabase("localhost","TechPro","postgres","12345");
        JdbcUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
        List<Object> region_ids= JdbcUtils.getColumnList("region_id","countries");
        System.out.println("region_ids = " + region_ids);

        //Assert that the number of region ids greater than 1 is 17.
        int idsGreaterThan1 = region_ids.stream().filter(t->(Integer)t>1).collect(Collectors.toList()).size();

        //Verify that the number of region ids greater than 1 is 17.
        System.out.println("idsGreaterThan1 = " + idsGreaterThan1);
        assertEquals(17,idsGreaterThan1);

        //User closes the connection
        JdbcUtils.closeConnectionAndStatement();


    }
}