package com.hcmus.android.weshare;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.hcmus.android.weshare.viewmodel.AuthenticationViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private String email;
    private String password;

    private AuthenticationViewModel authenticationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        initViewModel();
    }

    private void initViewModel() {
        authenticationViewModel = new ViewModelProvider(this).get(AuthenticationViewModel.class);
        authenticationViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                Toast.makeText(MainActivity.this, R.string.login_success_message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initComponents() {
        emailEditText = findViewById(R.id.login_email_edit_text);
        passwordEditText = findViewById(R.id.login_password_edit_text);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void loginButtonClick(View view) {
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        if (email.length() > 0 && password.length() > 0) {
            authenticationViewModel.login(email, password);
        }
    }

    public void signUpButtonClick(View view) {
        navigateToRegisterActivity();
    }

    int REGISTER_CODE = 240;

    public void navigateToRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, REGISTER_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REGISTER_CODE) {
            if (resultCode == MainActivity.RESULT_OK) {
                email = data.getStringExtra("email");
                password = data.getStringExtra("password");
                emailEditText.setText(email);
                passwordEditText.setText(password);
                authenticationViewModel.login(email, password);
            }
            else if (resultCode == MainActivity.RESULT_CANCELED) {

            }
        }

    }

}