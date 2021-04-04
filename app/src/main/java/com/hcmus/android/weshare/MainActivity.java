package com.hcmus.android.weshare;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hcmus.android.weshare.viewmodel.AuthenticationViewModel;

public class MainActivity extends AppCompatActivity {

    private final int REGISTER_CODE = 240;

    private EditText emailEditText;
    private EditText passwordEditText;

    private AuthenticationViewModel authenticationViewModel;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigateToChatBox();
        initEditTexts();
        initViewModel();
        loginWithLatestInfo();
    }

    private void navigateToChatBox() {
        Intent intent = new Intent(this, ChatBoxActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void loginWithLatestInfo() {
        authenticationViewModel.loginWithLatestInfo();
    }

    private void initViewModel() {
        authenticationViewModel = new ViewModelProvider(this).get(AuthenticationViewModel.class);
        authenticationViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                storeLoginInfo();
                Toast.makeText(MainActivity.this, R.string.login_success_message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void storeLoginInfo() {
        String email = authenticationViewModel.getUserEmail().getValue();
        String password = authenticationViewModel.getUserPassword().getValue();
        authenticationViewModel.storeLoginInfo(email, password);
    }

    private void initEditTexts() {
        emailEditText = findViewById(R.id.login_email_edit_text);
        passwordEditText = findViewById(R.id.login_password_edit_text);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void loginButtonClick(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        authenticationViewModel.login(email, password);
    }

    public void signUpButtonClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, REGISTER_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGISTER_CODE && resultCode == RESULT_OK) {
            String email = data.getStringExtra("email");
            String password = data.getStringExtra("password");
            emailEditText.setText(email);
            passwordEditText.setText(password);
            authenticationViewModel.login(email, password);
        }
    }
}