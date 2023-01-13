package Practise.Querys;

import java.sql.*;

public class Query04 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        /*
        CREATE TABLE, DROP TABLE, ALTER TABLE gibi DLL (Data Defination Language – Veri Tanımlama Dili)
        ifadeleri için sonuç (ResultSet) dondurmeyen metotlar kullanılmalıdır.
            1) execute(): boolean bir değer döndürür.
            2) executeUpdate(): INSERT, UPDATE gibi DML (Data Manipulation Language – Veri İşleme Dili )
            işlemlerinde kullanılır. Bu işlemlerde, işlemden etkilenen satır sayısını döndürür.
        */

        //Registration  to the driver
        Class.forName("org.postgresql.Driver");

        //Create a connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        //Create statement
        Statement st = con.createStatement();



        // SORU1: Bölümler tablosunda yeni bir kayıt ekleyin ve eklenen satir sayisini konsola getirin (50, 'ARGE', 'Johanna Dely', 14000)
        System.out.println("Question-1");

        int s1 = st.executeUpdate("insert into bolumler values (50, 'ARGE', 'Johanna Dely', 14000)");
        System.out.println(s1 + " satir eklendi.");



        // SORU2; Bölümler tablosuna birden fazla kayıt ekleyelim.

        // 1. YOL
        System.out.println("\n\nQuestion-2  1st way");
        String [] veri1 = { "insert into bolumler values(60, 'YEMEKHANE', 'Martin Solberk', 9500)",
                "insert into bolumler values(50, 'ARGE', 'Selina Pitt', 10200)",
                "insert into bolumler values(20, 'IT', 'Martine Holsen', 11400)"};
        int count = 0;
        for(String each:veri1){
            count = count + st.executeUpdate(each);
        }
        System.out.println(count + " data eklendi.");



        // 2. YOL
        // addBatch(): SQL sorgularımızı grupluyor
        // executeBatch(): Veriyi database gönderiyor
        System.out.println("\n\nQuestion-2  2nd way");

        String [] veri2 = {"insert into bolumler values(10, 'MUHASEBE', 'Martin Holheim', 10500)",
                "insert into bolumler values(50, 'ARGE', 'Amalie Viber', 13200)",
                "insert into bolumler values(10, 'MUHASEBE', 'Elisa Olbakke', 15400)"};


        for(String each:veri2){
            st.addBatch(each);  //Yukarıdaki verileri bir arada grupladık.
        }

        st.executeBatch();  //Verileri tek seferde database'e gonderir.
        System.out.println("Veriler Database'e eklendi.");


        //3-Bolumler tablosunun son halini listeleyiniz
        System.out.println("\n\nQuestion-3");
        ResultSet tablo1 = st.executeQuery("select * from bolumler");

        while (tablo1.next()) {

            System.out.println(tablo1.getInt(1) + " " + tablo1.getString(2) + " " + tablo1.getString(3) + " " +
                    tablo1.getInt(4) );
        }

    }
}
