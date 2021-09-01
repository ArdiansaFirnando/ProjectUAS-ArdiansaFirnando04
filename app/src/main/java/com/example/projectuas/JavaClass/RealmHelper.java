package com.example.projectuas.JavaClass;

import android.util.Log;

import com.example.projectuas.model.DataModel;
import com.example.projectuas.model.ModelTeams;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    private Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //Function untuk menyimpan data
    public void Save(DataModel dataModel){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){

                    Number current_id = realm.where(DataModel.class).max("id");
                    int nextId;
                    if (current_id == null){
                        nextId = 1;
                    }
                    else {
                        nextId = current_id.intValue() + 1;
                    }

                    dataModel.setId(nextId);
                    DataModel model = realm.copyToRealm(dataModel);

                    Log.d("Create", "execute: Database telah dibuat");
                }
                else {
                    Log.d("Create", "execute: Database gagal dibuat");
                }
            }
        });
    }

    //Function untuk memanggil data
    public List<DataModel> getAllData(){
        RealmResults<DataModel> results = realm.where(DataModel.class).findAll();

        return results;
    }

    //Function untuk menghapus data
    public void Delete(int id){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<DataModel> results = realm.where(DataModel.class).equalTo("id", id).findAll();
                Log.i("pppppppppppppppp", "getAllData: "+results.size());
                results.deleteFromRealm(0);
            }
        });
    }
}
