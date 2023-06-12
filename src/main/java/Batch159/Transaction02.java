package Batch159;

import java.sql.*;

public class Transaction02 {

    public static void main(String[] args) throws Exception {

/*         Every operation we do in the database is performed in the transaction
           Transaction: The indestructible transaction in the DB, that is, the smallest transaction in the atomic structure.
           A single transaction can be created for multiple transactions.
           If all of these transactions are performed successfully, the transaction is committed and confirmed.
           In case of a problem in at least one of these transactions, all transactions in the transaction can be canceled with rollback.
           Sending money from one account to another is such a process, if there is a problem in one account, there will be no money transfer to the other account*/


        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "12345");
        con.setAutoCommit(false);  //Normalde transactionda otomatic commit islemi olur. yani otomatik onaylanir. bunu kaldirmak icin false yaptim, islemleri kendim tayin etmek istiyorum
        //bunun icin yapacagim islemleri try-cath blogu icine alacagim

        //pgAdminde hesaplar adinda bir tablo olusturdum, iki kaydimiz var.


        try {
            //hesap no:1234 olan hesaptan hesap no:5678 olan hesaba 1000dolar para transferi olsun

            String query = "UPDATE hesaplar SET bakiye=bakiye+? WHERE hesap_no=?";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setDouble(1, -1000);
            preparedStatement.setInt(2, 1234);
            preparedStatement.executeUpdate();

            //sistemsel hata olusturalim
            if (true) {       //once true ile denedim, rollback devreye girdi. mevcut bilgiler korundu.
                throw new Exception();      //uygulama burda duracak
            }
            //true durumunda, Fred'den para dusuldu, ancak Barnie'ye ulasmadi, bu istenmeyen durum, hata firlatti.
            //sonra false ile, yani sistemde herhangi bir hata yoksa islemler gerceklesti

            preparedStatement.setDouble(1, 1000);
            preparedStatement.setInt(2, 5678);
            preparedStatement.executeUpdate();

            con.commit();  //islemler basarili ise onayla
            preparedStatement.close();
            con.close();

        } catch (Exception e){
            con.rollback();     //yukarda hata olursa islemleri iptal et ve mevcut hale geri don.
            System.out.println(e.getMessage());
        }

        //commit'ten sonra rollback kullanilamaz, cunku commit ile degisiklikler onaylanmis oluyor. bu noktadan sonra geri donme islemi yapilamiyor.



    }
}
