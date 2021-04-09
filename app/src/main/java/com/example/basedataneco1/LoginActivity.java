package com.example.basedataneco1;

import android.content.Intent;
import android.os.Bundle;
import android.os.CpuUsageInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText edLogin, edPassword;
    private FirebaseAuth mAuth;
    private Button btnStart, btnSignUp, btnSignIn, btnSignOut;
    private TextView tvUserName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_lauout);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser != null) {
            showSigned();

            String userName = "Вы вошли как :" + cUser.getEmail();
            tvUserName.setText(userName);

            Log.d("signInWithCustomToken:success", "sssss");
            Toast.makeText(this, "User not null", Toast.LENGTH_SHORT);
        } else {
            notSigned();

            Toast.makeText(this, "User null", Toast.LENGTH_SHORT);

        }
    }

    public void onClickSignOut(View view) {
        FirebaseAuth.getInstance().signOut();
        btnStart.setVisibility(View.GONE);
        tvUserName.setVisibility(View.GONE);
        edLogin.setVisibility(View.VISIBLE);
        edPassword.setVisibility(View.VISIBLE);
        btnSignIn.setVisibility(View.VISIBLE);
        btnSignUp.setVisibility(View.VISIBLE);
        btnSignOut.setVisibility(View.GONE);
    }

    private void init() {
        edLogin = findViewById(R.id.edLogin);
        edPassword = findViewById(R.id.edPassword);
        mAuth = FirebaseAuth.getInstance();
        btnStart = findViewById(R.id.btnStart);
        tvUserName = findViewById(R.id.tvUserName);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnStart = findViewById(R.id.btnStart);
        btnSignOut = findViewById(R.id.btnSignOut);

    }

    public void onClickSignUp(View view) {
        if (!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString())) {
            mAuth.createUserWithEmailAndPassword(edLogin.getText().toString(),
                    edPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        showSigned();
                        sendEmailVer();
                        Toast.makeText(getApplicationContext(), "User SignUp Succesfull", Toast.LENGTH_SHORT).show();
                    } else {
                        notSigned();
                        Toast.makeText(getApplicationContext(), "User SignUp Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Log.d("Enter :success", "sssss");

            Toast.makeText(getApplicationContext(), "Please enter your Email and Password", Toast.LENGTH_SHORT).show();

        }

    }

    public void onClickSignIn(View view) {
        if (!TextUtils.isEmpty(edLogin.getText().toString())
                && !TextUtils.isEmpty(edPassword.getText().toString())) {
            mAuth.signInWithEmailAndPassword(edLogin.getText().toString(), edPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                showSigned();
                                Log.d("TAG", "signInWithCustomToken:success");
                                Toast.makeText(getApplicationContext(), "User SignIn Succesfull", Toast.LENGTH_SHORT)
                                        .show();
                                Intent i = new Intent(LoginActivity.this, Show_Activity.class);
                                startActivity(i);
                            } else {
                                notSigned();
                                Toast.makeText(getApplicationContext(), "User SignIn Failed", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                    });
        }
    }

    private void showSigned() {
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;

        if (user.isEmailVerified()) {

            String userName = "Вы вошли как :" + user.getEmail();

            btnStart.setVisibility(View.VISIBLE);
            tvUserName.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.VISIBLE);

            edLogin.setVisibility(View.GONE);
            edPassword.setVisibility(View.GONE);
            btnSignIn.setVisibility(View.GONE);
            btnSignUp.setVisibility(View.GONE);

        } else {
            Toast.makeText(getApplicationContext(), "Проверьте вашу почту для подтверждения Email адреса", Toast.LENGTH_SHORT);

        }
    }

    private void notSigned() {
        btnStart.setVisibility(View.GONE);
        tvUserName.setVisibility(View.GONE);
        btnSignOut.setVisibility(View.GONE);

        edLogin.setVisibility(View.VISIBLE);
        edPassword.setVisibility(View.VISIBLE);
        btnSignIn.setVisibility(View.VISIBLE);
        btnSignUp.setVisibility(View.VISIBLE);
    }

    public void onClickStart(View view) {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    private void sendEmailVer() {
        FirebaseUser user = mAuth.getCurrentUser();

        assert user != null;
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Проверьте вашу почту для подтверждения Email адреса", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(getApplicationContext(), "Send email failed", Toast.LENGTH_SHORT);
                }
            }
        });
    }

}