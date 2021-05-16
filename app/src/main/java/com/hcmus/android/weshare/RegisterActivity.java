package com.hcmus.android.weshare;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
        authenticationViewModel.getUserMutableLiveData().observe(RegisterActivity.this, firebaseUser -> {
            if (firebaseUser != null) {
                authenticationViewModel.saveUserInfoToDB();
            }
        });
        authenticationViewModel.getUser().observe(RegisterActivity.this,
                user -> navigateBackToLoginActivity());
    }

    private void navigateBackToLoginActivity() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("email", emailEditText.getText().toString());
        returnIntent.putExtra("password", passwordEditText.getText().toString());
        setResult(MainActivity.RESULT_OK, returnIntent);
        finish();
    }

    private void initComponents() {
        emailEditText = findViewById(R.id.register_email_edit_text);
        passwordEditText = findViewById(R.id.register_password_edit_text);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void registerButtonClick(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        authenticationViewModel.register(email, password);
    }
}