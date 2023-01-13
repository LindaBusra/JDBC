package Practise.Querys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Query07 {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        //Registration  to the driver
        Class.forName("org.postgresql.Driver");

        //Create a connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        //Create statement
        Statement st = con.createStatement();



        //SORU: markalar adında bir tablo oluşturunuz. marka_id int, marka_isim VARCHAR(15), calisan_sayisi int

        String query = "create table markalar (marka_id int, marka_isim VARCHAR(15), calisan_sayisi int)";


        //1.yontem : execute () methodu ile

        boolean s1 = st.execute(query);
        System.out.println("markalar tablosu olusturuldu ->" +s1); //markalar tablosu olusturuldu ->false



        // SORU : markalar tablosuna yeni bir sutun {sube_sayisi int} ekleyiniz

            String alterQuery = "ALTER table markalar ADD sube_sayisi int";
            st.execute(alterQuery);
            System.out.println("sube sayisi kolonu eklendi");


        // SORU : markalar tablosuna bir satirlik veri girisi yapin.   degerler sirasiyla: ( 1, 'apple', 1000, 400)
            st.execute("insert into markalar values(1, 'Apple', 1000, 400)");
            System.out.println("ilk deger atamasi yapildi");


        // SORU : markalar tablosuna colu satirli veri girisi yapin.
        List<String> records = new ArrayList<>();

        records.add("insert into markalar values(2, 'IBM', 1500, 500)");
        records.add("insert into markalar values(3, 'Google', 1200, 450)");
        records.add("insert into markalar values(2, 'Asos', 1800, 520)");
        records.add("insert into markalar values(3, 'HP', 1600, 480)");

        for(String w: records) {

            st.execute(w);
        }
        System.out.println("yeni " + records.size() + " deger girildi");

        // SORU : markalar tablosunda marka_isim sutununu markanin_ismi olarak degistiriniz
        String alterQuery4 = "ALTER table markalar RENAME column marka_isim to markanin_ismi";
        st.execute(alterQuery4);
        System.out.println("marka_isim sutunun ismi,  markanin_ismi olarak degistirildi");



//        // SORU : markalar tablosunun adini  brands olarak degistiriniz.

        String sql ="ALTER TABLE markalar RENAME TO brands";
        st.execute(sql);
        System.out.println("tablonun ismi değişti");


//
//        // SORU : markalar tablosunda marka_ismi sutununun data type ini char(20) olarak degistiriniz. !!!!!!hata veriyor!!!!

//        st.execute("alter table markalar modify marka_ismi char(20)");
//        System.out.println("marka ismi data type char(20) oldu");

//
//        // SORU: markalar tablosunu siliniz
//
//        String dropQuery = "Drop table markalar";
//        st.execute(dropQuery);
//        System.out.println("markalar tablosu silindi...");

    }
}