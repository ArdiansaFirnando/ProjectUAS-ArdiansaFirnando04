package com.example.projectuas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.projectuas.JavaClass.RealmHelper;
import com.example.projectuas.adapter.TeamsFavoriteAdapter;
import com.example.projectuas.model.DataModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TeamsFavoriteAdapter teamsFavoriteAdapter;
    private List<DataModel> dataModelList;

    private Realm realm;
    private RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView = findViewById(R.id.id_recyclerview_favorite);

        //Setup Realm
        realmHelper = new RealmHelper(realm);
        dataModelList = new ArrayList<>();

        //Mengisi data
        dataModelList = realmHelper.getAllData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        teamsFavoriteAdapter.notifyDataSetChanged();
        show();
    }

    public void show(){
        teamsFavoriteAdapter = new TeamsFavoriteAdapter(dataModelList, new TeamsFavoriteAdapter.Callback() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getApplicationContext(), DetailFavoriteActivity.class);
                intent.putExtra("name", dataModelList.get(position).getName());
                intent.putExtra("years", dataModelList.get(position).getYears());
                intent.putExtra("country", dataModelList.get(position).getCountry());
                intent.putExtra("description", dataModelList.get(position).getDescription());
                intent.putExtra("image", dataModelList.get(position).getImage());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(teamsFavoriteAdapter);
    }
}