package Practise.Querys;

import java.sql.*;

public class Query08 {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        //Registration  to the driver
        Class.forName("org.postgresql.Driver");

        //Create a connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        //Create statement
        Statement st = con.createStatement();

        //SORU: Calisanlar tablosuna yeni bir kayit ((10005, 'Muhammet Yenice' , 15000)  ekleyelim ve eklenen kaydi teyit icin sorgulayalim.

        st.execute("CREATE TABLE calisanlar (id NUMERIC(4), ad_soyad VARCHAR(50), maas NUMERIC(7))");
        System.out.println("yeni tablo eklendi");

        String insertQuery = "INSERT INTO calisanlar VALUES (1001, 'Muhammet Yenice' , 15000)";
        int s1 = st.executeUpdate(insertQuery);
        System.out.println(s1 + " satir eklendi");

        //intelij de gorelim
        ResultSet rs = st.executeQuery("SELECT * from calisanlar");

        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t\t\t" + rs.getInt(3));
        }

        //SORU: Calisanlar tablosuna birden fazla yeni kayıt ekleyelim.

        // 1.YONTEM
        // Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin yavas yapilmasina yol acar.
        // 10000 tane veri kaydi yapildigi dusunuldugunde  bu kotu bir yaklasimdir.

        String[] queries = {"INSERT INTO calisanlar VALUES (1002, 'Ahmet Guzel' , 20000)",
                "INSERT INTO calisanlar VALUES (1003, 'Huseyin Bey' , 22000)",
                "INSERT INTO calisanlar VALUES (1004, 'Sevde Tarhan' , 25000)"};


        for (String each : queries
        ) {
           st.executeUpdate(each);
        }
        System.out.println(" data eklendi..");

        //tablonun son hali
        ResultSet rs1 = st.executeQuery("SELECT * from calisanlar");

        while (rs1.next()) {
            System.out.println(rs1.getInt(1) + "\t\t" + rs1.getString(2) + "\t\t\t\t" + rs1.getInt(3));
        }

        // 2.YONTEM (addBath ve excuteBatch() metotlari ile)
        // addBatch metodu ile SQL ifadeleri gruplandirilabilir ve
        // exucuteBatch()  metodu ile veritabanina bir kere gonderilebilir.
        // ***!!!!**** excuteBatch() metodu bir int [] dizi dondurur.
        // Bu dizi her bir ifade sonucunda etkilenen satir sayisini gosterir.


        String[] queries2 = {"INSERT INTO calisanlar VALUES (1005, 'Ahmet Yanar' , 20000)",
                "INSERT INTO calisanlar VALUES (1006, 'Mehmet Solmaz' , 18000)"};
        for (String each : queries2) {
            st.addBatch(each);
        }

        int[] satir = st.executeBatch(); ////satir bir array eleman sayisi (satir sayisi): length
        System.out.println(satir.length + " satir eklendi");

        //tablonun en son hali
        ResultSet rs2 = st.executeQuery("SELECT * from calisanlar");

        while (rs2.next()) {
            System.out.println(rs2.getInt(1) + "\t\t" + rs2.getString(2) + "\t\t\t\t" + rs2.getInt(3));
        }

    }
}