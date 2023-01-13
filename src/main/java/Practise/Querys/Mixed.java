package Practise.Querys;

import java.sql.*;

public class Mixed {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //Registration  to the driver
        Class.forName("org.postgresql.Driver");

        //Create a connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TechPro","postgres","12345");

        //Create statement
        Statement st = con.createStatement();


        //SORU1: Bölümler tablosundan tüm kayıtları listeleyiniz.
        ResultSet tablo1 = st.executeQuery("select * from bolumler");

        //SORU01: ÖĞRENCİLER TABLOSUNDAKİ ERKEK ÖĞRENCİ BİLGİLERİ GETİRİN
        ResultSet veri = st.executeQuery("select * from ogrenciler where cinsiyet ='E'");

        //SORU02: ÖĞRENCİLER TABLOSUNDAKİ  9 SINIF KIZ ÖĞRENCİ BİLGİLERİ GETİRİN
        ResultSet veri2 = st.executeQuery("select * from ogrenciler where sinif=9 AND cinsiyet='K'");


        // SORU2:SATIS ve MUHASEBE bolumlerinde calişan personelin isimlerini ve maaşlarını,
        // maaş ters sıralı listeleyiniz.

        ResultSet tablo2 = st.executeQuery("select personel_isim, maas from personel " +
                "where bolum_id in(10,30) " +
                "order by maas desc");

        while(tablo2.next()){
            System.out.println(tablo2.getString(1)  + "\t"+ tablo2.getInt(2));


            // SORU4: Talebeler tablosunda notu 70 ile 90 arasindaki kisilerin isimlerini listeleyiniz.
                 ResultSet kayitlarnot7090 = st.executeQuery("SELECT isim,yazili_notu from talebeler where yazili_notu Between '70' and '90'");


            // SORU5: Talebeler tablosunda isminin 2. harfi e olan kisilerin veli_isimleri ile birlikte listeleyiniz.

            ResultSet isim2E = st.executeQuery("SELECT isim,veli_isim from talebeler where isim LIKE '_e%'");

            //SORU2: Talebeler tablosunda notları 90 uzeri olan kayitları listeleyiniz.
            //      ResultSet kayitlarBuyuk90 = st.executeQuery("SELECT * FROM talebeler where yazili_notu>90");

        }
    }
}
