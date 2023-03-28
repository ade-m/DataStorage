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
    }

    public void tambahDataUser(){
        Realm realm = Realm.getDefaultInstance();
        //penyimpanan data
        realm.executeTransaction(new Realm.Transaction() {
                 @Override
                 public void execute(Realm realm) {
                     User user = realm.createObject(User.class);
                     user.setNama("Budi");
                     user.setNotlp("0822121212");


                     User user1 = realm.createObject(User.class);
                     user1.setNama("Susi");
                     user1.setNotlp("08221245454");
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