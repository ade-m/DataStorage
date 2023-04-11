package edu.uph.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.uph.datastorage.model.User;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //config Realm
        Realm.init(this);
        RealmConfiguration config =
                new RealmConfiguration.Builder()
                        .allowWritesOnUiThread(true)
                        .deleteRealmIfMigrationNeeded()
                        .build();
        Realm.setDefaultConfiguration(config);

        SharedPreferences sharedPreferences =
                PreferenceManager
                        .getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nama","Budi");
        editor.apply();

        Button btnAct1 = (Button) findViewById(R.id.btnAct1);
        btnAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity.this,
                                KeduaActivity.class);
                intent.putExtra("nama","Budi");
                startActivity(intent);
            }
        });

        Button btnAct2 = (Button) findViewById(R.id.btnAct2);
        btnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity.this,
                                KetigaActivity.class);
                startActivity(intent);
            }
        });
        Button btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahDataUser();
            }
        });
        Button bntCetak = (Button) findViewById(R.id.bntCetak);
        bntCetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cetakDataUser();
            }
        });

        Button btnInq = (Button) findViewById(R.id.btnInq);
        btnInq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity.this,
                                UserInquiryActivity.class);
                startActivity(intent);
            }
        });

        Button btnTambahUser = (Button) findViewById(R.id.btnTanmbahUser);
        btnTambahUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity.this,
                                formUserActivity.class);
                startActivity(intent);
            }
        });
    }

    public void tambahDataUser(){
        Realm realm = Realm.getDefaultInstance();
        //penyimpanan data
        realm.executeTransaction(new Realm.Transaction() {
                 @Override
                 public void execute(Realm realm) {
                     try {
                        realm.deleteAll();
                         User user1 = realm.createObject(User.class,"08221245454");
                         user1.setNama("Susi");
                         User user2 = realm.createObject(User.class,"082212485454");
                         user2.setNama("Susi");
                         User user13 = realm.createObject(User.class,"082212415454");
                         user13.setNama("Susi");
                         User user01 = new User();
                         user01.setNama("Budi");
                         user01.setNotlp("0822121212" +" ");

                         User user = realm.createObject(User.class,user01.getNotlp());
                         user.setNama("Budi");
//                      user.setNotlp("0822121212");


//                     user1.setNotlp("08221245454");
                     }catch (RealmPrimaryKeyConstraintException e){
                         Log.d("TAG","PrimaryKey Sudah ada + "
                                 +e.getMessage().toString());
                     }

                 }
             }
        );
        realm.close();
    }
    public void cetakDataUser(){
        Realm realm = Realm.getDefaultInstance();
        //penarikan data
        RealmResults<User> users =
                realm.where(User.class).findAll();
        //menapilkan data
        for(User user : users){
            Log.d("TAG","Nama :"+user.getNama()
            + ", Nomor Telp"+ user.getNotlp());
        }
        realm.close();
    }
}