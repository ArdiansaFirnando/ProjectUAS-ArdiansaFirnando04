package com.example.projectuas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectuas.JavaClass.RealmHelper;
import com.example.projectuas.adapter.TeamsAdapter;
import com.example.projectuas.fragment.Fragment_home;
import com.example.projectuas.model.DataModel;
import com.example.projectuas.model.ModelTeams;
import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    private Bundle bundle;
    private TextView txt_name;
    private TextView txt_description;
    private ImageView btn_back;
    private ImageView img_image;
    private ImageView btn_saved_favorite;
    private Button btn_end;
    private MaterialToolbar toolbar;

    private String name;
    private String years;
    private String country;
    private String description;
    private String image;

    private Realm realm;
    private RealmHelper realmHelper;
    private DataModel dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txt_name = findViewById(R.id.txt_name_detail);
        txt_description = findViewById(R.id.txt_description_detail);
        img_image = findViewById(R.id.img_image_detail);
        btn_back = findViewById(R.id.btn_back_detail);
        btn_saved_favorite = findViewById(R.id.btn_saved_favorite);
        btn_end = findViewById(R.id.btn_end_detal);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            years = bundle.getString("years");
            country = bundle.getString("country");
            description = bundle.getString("description");
            image = bundle.getString("image");
        }

        txt_name.setText(name);
        txt_description.setText(description);
        Picasso.get()
                .load(image)
                .placeholder(R.drawable.ic_logo_splash_screen)
                .error(R.drawable.ic_launcher_foreground)
                .into(img_image);

        //Setup realm
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        btn_back.setOnClickListener(this);
        btn_saved_favorite.setOnClickListener(this);
        btn_end.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        if (view.equals(btn_back)){
            finish();
        }

        //Button save
        if (view.equals(btn_saved_favorite)){

            bundle = getIntent().getExtras();
            if (bundle != null) {
                name = bundle.getString("name");
                years = bundle.getString("years");
                country = bundle.getString("country");
                description = bundle.getString("description");
                image = bundle.getString("image");
            }


            dataModel = new DataModel();
            dataModel.setName(name);
            dataModel.setYears(years);
            dataModel.setCountry(country);
            dataModel.setDescription(description);
            dataModel.setImage(image);


            realmHelper = new RealmHelper(realm);
            realmHelper.Save(dataModel);

            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

        if (view.equals(btn_end)){
            finish();
        }
    }
}