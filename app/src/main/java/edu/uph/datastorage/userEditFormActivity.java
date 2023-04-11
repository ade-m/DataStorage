package edu.uph.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.uph.datastorage.crud.userCRUD;

public class userEditFormActivity extends AppCompatActivity {

    EditText edtNamae;
    TextView edtNoTlpe;
    Button btnSimpanUsere;
    String Nama="";
    String NoTlp="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_form);

        edtNamae = (EditText) findViewById(R.id.edtNamae);
        edtNoTlpe = (TextView) findViewById(R.id.edtNoTlpe);
        btnSimpanUsere = (Button) findViewById(R.id.btnSimpanUsere);
        edtNoTlpe.setText(getIntent().getStringExtra("notlp"));
        edtNamae.setText( getIntent().getStringExtra("nama"));

        btnSimpanUsere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nama= (String) edtNamae.getText().toString();
                NoTlp= (String) edtNoTlpe.getText().toString();
                Log.d("TAG","Nama" + Nama + "NomorTlp" + NoTlp);
                userCRUD usercrud = new userCRUD();
                usercrud.updateDataUser(NoTlp,Nama);
                finish();
            }
        });
    }
}