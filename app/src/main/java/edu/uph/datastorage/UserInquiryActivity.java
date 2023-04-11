package edu.uph.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import edu.uph.datastorage.adapter.UserAdapter;
import edu.uph.datastorage.model.User;
import io.realm.Realm;
import io.realm.RealmResults;

public class UserInquiryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_inquiry);

        //tarik data pengguna
        Realm realm = Realm.getDefaultInstance();
        //penarikan data
        RealmResults<User> users =
                realm.where(User.class).findAll();
        //menapilkan data
//        for(User user : users){
//            Log.d("TAG","Nama :"+user.getNama()
//                    + ", Nomor Telp"+ user.getNotlp());
//        }
        ArrayList<User> arrayofuser = new ArrayList<User>();
        arrayofuser.addAll(realm.copyFromRealm(users));
        realm.close();

        UserAdapter userAdapter = new UserAdapter(this,arrayofuser);
        ListView listView = (ListView) findViewById(R.id.listViewUser);
        listView.setAdapter(userAdapter);

    }
}