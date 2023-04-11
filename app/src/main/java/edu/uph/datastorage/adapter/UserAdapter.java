package edu.uph.datastorage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import edu.uph.datastorage.MainActivity;
import edu.uph.datastorage.R;
import edu.uph.datastorage.crud.userCRUD;
import edu.uph.datastorage.formUserActivity;
import edu.uph.datastorage.model.User;
import edu.uph.datastorage.userEditFormActivity;

public class UserAdapter extends ArrayAdapter<User> {

    public UserAdapter(Context context,  List<User> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.layout_listviewuser,
                            parent,false);
        }
        TextView txvNamaUser = (TextView)
                convertView.findViewById(R.id.txvNamaUser);
        TextView txvNoTlp = (TextView)
                convertView.findViewById(R.id.txvNoTlp);
        txvNamaUser.setText(user.getNama());
        txvNoTlp.setText(user.getNotlp());

        ImageButton btnEdit = (ImageButton) convertView.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(getContext(),
                                userEditFormActivity.class);
                intent.putExtra("notlp",user.getNotlp());
                intent.putExtra("nama",user.getNama());
                getContext().startActivity(intent);
            }
        });

        ImageButton btnDelete = (ImageButton) convertView.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userCRUD usercrud = new userCRUD();
                usercrud.deleteDataUser(user.getNotlp());
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
