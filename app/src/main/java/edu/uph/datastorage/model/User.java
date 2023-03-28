package edu.uph.datastorage.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

@RealmClass
public class User extends RealmObject {
    @Required
    private String nama;
    @PrimaryKey
    private String notlp;

    public User() {
    }

    public User(String nama, String notlp) {
        this.nama = nama;
        this.notlp = notlp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNotlp() {
        return notlp;
    }

    public void setNotlp(String notlp) {
        this.notlp = notlp;
    }
}
