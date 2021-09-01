package com.example.projectuas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectuas.JavaClass.RealmHelper;
import com.example.projectuas.model.DataModel;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailFavoriteActivity extends AppCompatActivity implements View.OnClickListener {

    private Bundle bundle;
    private TextView txt_name;
    private TextView txt_description;
    private ImageView img_image;
    private ImageView btn_back;
    private Button btn_delete;

    private int id;
    private String name;
    private String description;
    private String image;

    private Realm realm;
    private RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite);

        txt_name = findViewById(R.id.txt_name_detail_favorite);
        txt_description = findViewById(R.id.txt_description_detail_favorite);
        img_image = findViewById(R.id.img_image_detail_favorite);
        btn_back = findViewById(R.id.btn_back_detail_favorite);
        btn_delete = findViewById(R.id.btn_delete_detal_favorite);

        //Setup Realm
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(realmConfiguration);
        realmHelper = new RealmHelper(realm);

        bundle = getIntent().getExtras();
        if (bundle != null){
            id = bundle.getInt("id", 0);
            name = bundle.getString("name");
            description = bundle.getString("description");
            image = bundle.getString("image");
        }

        Log.i("ppppppppppppppp", "onCreate: "+id);

        txt_name.setText(name);
        txt_description.setText(description);
        Picasso.get()
                .load(image)
                .placeholder(R.drawable.ic_logo_splash_screen)
                .error(R.drawable.ic_launcher_foreground)
                .into(img_image);


        btn_back.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btn_back)){
            finish();
        }

        //Button Delete
        if (view.equals(btn_delete)){
            realmHelper.Delete(id);
            Toast.makeText(getApplicationContext(), "Delete success!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}