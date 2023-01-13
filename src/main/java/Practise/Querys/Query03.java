package Practise.Querys;

import java.sql.*;

public class Query03 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //Registration  to the driver
        Class.forName("org.postgresql.Driver");

        //Create a connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro", "postgres", "12345");

        //Create statement
        Statement st = con.createStatement();



        //1-Bolumler tablosundan tum kayitlari listeleyiniz
        System.out.println("Question-1");
        ResultSet tablo1 = st.executeQuery("select * from bolumler");

        while (tablo1.next()) {

            System.out.println(tablo1.getInt(1) + " " + tablo1.getString(2) + " " + tablo1.getString(3) + " " +
                    tablo1.getInt(4) );
        }





        //2-Muhasebe ve Bakim Onarim bolumlerinde calisan personelin isimlerini ve maaslarini maas ters sirali listeleyiniz
        System.out.println("\n\nQuestion-2");

        String sql = "SELECT personel_isim, maas FROM bolumler\n" +
                "WHERE bolum_id IN(10,30)\n" +
                "ORDER BY maas DESC";

        ResultSet result = st.executeQuery(sql);

        while (result.next()) {
            System.out.println( result.getString(1) +  " " + result.getInt(2) );

        }





        //3-Tum bolumlerde calisan personel isimlerini bolum isimlerini ve maaslarini, bolum ve maas sirali listeleyiniz

        System.out.println("\n\nQuestion-3");

        String sql1 ="SELECT personel_isim, bolum_isim, maas FROM bolumler\n" +
                "ORDER BY bolum_isim, maas";

        ResultSet result1 = st.executeQuery(sql1);


        while (result1.next()) {
            System.out.println(result1.getString(1) + "--" + result1.getString(2) + "--" +
                    result1.getInt(3)  );

        }





        //4- Maasi en yuksek 5 kisinin bolumunu, adini ve maasini listeleyiniz.

        System.out.println("\n\nQuestion-4");

        String sql2 = "SELECT bolum_isim, personel_isim, maas FROM bolumler\n" +
                "ORDER BY maas DESC\n" +
                "LIMIT 5";

        ResultSet result2 = st.executeQuery(sql2);


        while (result2.next()) {
            System.out.println(result2.getString(1) + "\t" + result2.getString(2) + "\t" +
                    result2.getInt(3));
        }
    }
}