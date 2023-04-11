package edu.uph.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.uph.datastorage.model.User;
import io.realm.Realm;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class formUserActivity extends AppCompatActivity {

    EditText edtNama, edtNoTlp;
    Button btnSimpanUser;
    String Nama="";
    String NoTlp="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user);

        edtNama = (EditText) findViewById(R.id.edtNama);
        edtNoTlp = (EditText) findViewById(R.id.edtNoTlp);
        btnSimpanUser = (Button) findViewById(R.id.btnSimpanUser);



        btnSimpanUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nama= (String) edtNama.getText().toString();
                NoTlp= (String) edtNoTlp.getText().toString();
                Log.d("TAG","Nama" + Nama + "NomorTlp" + NoTlp);
                tambahDataUser(Nama,NoTlp);
            }
        });

    }
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
                                             finish();
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