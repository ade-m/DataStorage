package edu.uph.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class KetigaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketiga);

        SharedPreferences sharedPreferences =
                PreferenceManager
                        .getDefaultSharedPreferences(getApplicationContext());
        String nama = sharedPreferences.getString("nama","");
        Bundle extras = getIntent().getExtras();
        TextView txvNama = (TextView) findViewById(R.id.txvNama2);
        txvNama.setText(nama);
    }
}