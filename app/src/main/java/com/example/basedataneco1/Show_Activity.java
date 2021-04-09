package com.example.basedataneco1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Show_Activity extends AppCompatActivity {
    private TextView tvName, tvSecondName, tvEmail;
    private ImageView imBD;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);
        init();
        getIntentMain();
    }

    private void init()
    {
        imBD = findViewById(R.id.imBD);
        tvName = findViewById(R.id.tvUserName);
        tvSecondName = findViewById(R.id.tvSecond_Name);
        tvEmail = findViewById(R.id.tvEmail);
    }

    private void getIntentMain () {
        Intent i = getIntent();
        if (i != null)
        {
            Picasso.get().load(i.getStringExtra("user_image_id")).into(imBD);
            tvName.setText(i.getStringExtra("user_name"));
            tvSecondName.setText(i.getStringExtra("user_sec_name"));
            tvEmail.setText(i.getStringExtra("user_email"));

        }
    }
}
