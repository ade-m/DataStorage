package edu.uph.datastorage.crud;

import android.util.Log;

import edu.uph.datastorage.model.User;
import io.realm.Realm;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class userCRUD {
    public void tambahDataUser(String Nama, String NoTlp){
        Realm realm = Realm.getDefaultInstance();
        //penyimpanan data
        realm.executeTransaction(new Realm.Transaction() {
                 @Override
                 public void execute(Realm realm) {
                     try {
                         Log.d("TAG","Nama" + Nama + "NomorTlp" + NoTlp);
                         User user1 = realm.createObject(User.class,NoTlp);
                         user1.setNama(Nama);
                     }catch (RealmPrimaryKeyConstraintException e){
                         Log.d("TAG","PrimaryKey Sudah ada + "
                                 +e.getMessage().toString());
                     }

                 }
             }
        );
        realm.close();
    }

    public void updateDataUser(String NoTlp,String Nama){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
                 @Override
                 public void execute(Realm realm) {
                     try {
                         Log.d("TAG","Nama" + Nama + "NomorTlp" + NoTlp);
                         User user1 = realm.where(User.class).equalTo("notlp",NoTlp).findFirst();
                         user1.setNama(Nama);
                     }catch (RealmPrimaryKeyConstraintException e){
                         Log.d("TAG","PrimaryKey Sudah ada + "
                                 +e.getMessage().toString());
                     }
                 }
             }
        );
        realm.close();
    }

    public void deleteDataUser(String NoTlp){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
                 @Override
                 public void execute(Realm realm) {
                     try {
                         Log.d("TAG", "NomorTlp" + NoTlp);
                         User user1 = realm.where(User.class).equalTo("notlp",NoTlp).findFirst();
                        user1.deleteFromRealm();
                     }catch (RealmPrimaryKeyConstraintException e){
                         Log.d("TAG","PrimaryKey Sudah ada + "
                                 +e.getMessage().toString());
                     }
                 }
             }
        );
        realm.close();
    }
}
