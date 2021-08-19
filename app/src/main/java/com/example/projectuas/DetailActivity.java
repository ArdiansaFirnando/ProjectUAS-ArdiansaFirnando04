package com.example.projectuas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectuas.adapter.TeamsAdapter;
import com.example.projectuas.fragment.Fragment_home;
import com.example.projectuas.model.ModelTeams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private Bundle bundle;
    private TextView txt_name;
    private TextView txt_description;
    private ImageView btn_back;
    private ImageView img_image;
    private ImageView btn_saved;
    private Button btn_end;

    private String name;
    private String description;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txt_name = findViewById(R.id.txt_name_detail);
        txt_description = findViewById(R.id.txt_description_detail);
        img_image = findViewById(R.id.img_image_detail);
        btn_back = findViewById(R.id.btn_back_detail);
        btn_saved = findViewById(R.id.btn_saved_detail);
        btn_end = findViewById(R.id.btn_end_detal);

        bundle = getIntent().getExtras();
        if (bundle != null){
            name = bundle.getString("name");
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

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}