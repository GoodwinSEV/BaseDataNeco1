package com.example.basedataneco1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Show_Activity extends AppCompatActivity {
    private TextView tvName, tvSecondName, tvEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);
        init();
        getIntentMain();
    }

    private void init()
    {
        tvName = findViewById(R.id.tvName);
        tvSecondName = findViewById(R.id.tvSecond_Name);
        tvEmail = findViewById(R.id.tvUserName);
    }

    private void getIntentMain () {
        Intent i = getIntent();
        if (i != null) {
            tvName.setText(i.getStringExtra("user_name"));
            tvSecondName.setText(i.getStringExtra("user_sec_name"));
            tvEmail.setText(i.getStringExtra("user_email"));

        }
    }
}
