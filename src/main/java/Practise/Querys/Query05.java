package Practise.Querys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Query05 {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        //Registration  to the driver
        Class.forName("org.postgresql.Driver");

        //Create a connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        //Create statement
        Statement st = con.createStatement();



        // SORU: İşçiler adında bir tablo oluşturunuz. id int, birim VARCHAR(10), maas int

        String sql = "create table isciler (id NUMERIC(3),\n" +
                "birim VARCHAR(30),\t\n" +
                "maas NUMERIC(7))";

        st.execute(sql);
        System.out.println("isciler tablosu olusturuldu");


        // SORU: isciler tablosuna yeni bir sütun ekleyin. (isim varcahar(20)
        st.execute("alter table isciler add isim varchar(20)");
        System.out.println("Sütun eklendi.");

        //SORU: isciler tablosuna soyisim varchar(20), sehir varchar(10) adında 2 tane sutun ekleyiniz.
        st.execute("alter table isciler add soyisim varchar(20)");
        st.execute("alter table isciler add sehir varchar(10)");
        System.out.println("2 sutun eklendi");


        // SORU: İŞCİLER tablosundaki sehir sütunun ismini ulke olarak değiştirin.

        st.execute("alter table isciler rename column sehir to ulke");
        System.out.println("isciler tablosundaki sehir sutunun ismini ulke olarak degistirildi");


        //Tablodan ulke sutununu silin
        st.execute("alter table isciler drop column ulke");
        System.out.println("isciler tablosundaki  ulke sutunu silindi.");

        //SORU: Tablonun ismini employee olarak değiştirin
        st.execute("alter table isciler rename to employee");
        System.out.println("tablonun ismi değişti");




        //SORU: ulke sütunun data türünü char(30) yapın -->hata veriyor!!!!
//        st.execute("alter table employee modify ulke char(30)");
//        System.out.println("ulke data type char(30)");


        // SORU: İşciler tablosunu siliniz.
        st.execute("drop table employee");
        System.out.println("employee tablosu silindi");

    }
}
