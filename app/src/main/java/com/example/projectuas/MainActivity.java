package com.example.projectuas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.editText_email);
        password = findViewById(R.id.editText_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();

                if (str_email.equalsIgnoreCase("ardiansa@gmail.com")
                        && str_password.equalsIgnoreCase("ardiansa")){
                    Toast.makeText(getApplicationContext(), "Login berhasil!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    intent.putExtra("email", str_email);
                    startActivity(intent);
                    finish();
                }
                else if(str_email.isEmpty() && str_password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan Email dan Password!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Email atau Password Anda salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}