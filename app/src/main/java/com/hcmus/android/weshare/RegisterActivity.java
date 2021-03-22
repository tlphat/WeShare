package com.hcmus.android.weshare;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.hcmus.android.weshare.viewmodel.AuthenticationViewModel;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;

    private AuthenticationViewModel authenticationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();
        initViewModel();
    }

    private void initViewModel() {
        authenticationViewModel = new ViewModelProvider(this).get(AuthenticationViewModel.class);

        authenticationViewModel.getUserMutableLiveData().observe(RegisterActivity.this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    Toast.makeText(RegisterActivity.this, "Mutable live data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initComponents() {
        emailEditText = findViewById(R.id.register_email_edit_text);
        passwordEditText = findViewById(R.id.register_email_edit_text);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void registerButtonClick(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.length() > 0 && password.length() > 0) {
            authenticationViewModel.register(email, password);
        }
    }
}