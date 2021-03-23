package com.hcmus.android.weshare;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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

    /** The shared preferences are used to store latest login information */
    private SharedPreferences latestLoginPreferences;
    private SharedPreferences.Editor latestLoginEditor;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        loginWithLatestInfo();
    }

    private void initComponents() {
        initSharePreferences();
        initEditTexts();
        initViewModel();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void loginWithLatestInfo() {
        String latestEmail = latestLoginPreferences.getString("email", "");
        String latestPassword = latestLoginPreferences.getString("password", "");
        loginWithGivenEmailAndPassword(latestEmail, latestPassword);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void loginWithGivenEmailAndPassword(String email, String password) {
        emailEditText.setText(email);
        passwordEditText.setText(password);
        loginWithEmailAndPassword(email, password);
    }

    @SuppressLint("CommitPrefEdits")
    private void initSharePreferences() {
        latestLoginPreferences = getSharedPreferences("login_preferences", MODE_PRIVATE);
        latestLoginEditor = latestLoginPreferences.edit();
    }

    private void initViewModel() {
        authenticationViewModel = new ViewModelProvider(this).get(AuthenticationViewModel.class);
        authenticationViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                Toast.makeText(MainActivity.this, R.string.login_success_message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initEditTexts() {
        emailEditText = findViewById(R.id.login_email_edit_text);
        passwordEditText = findViewById(R.id.login_password_edit_text);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void loginButtonClick(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        loginWithEmailAndPassword(email, password);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void loginWithEmailAndPassword(String email, String password) {
        storeLoginInfo(email, password);
        authenticationViewModel.login(email, password);
    }

    private void storeLoginInfo(String email, String password) {
        latestLoginEditor.putString("email", email);
        latestLoginEditor.putString("password", password);
        latestLoginEditor.commit();
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
            loginWithGivenEmailAndPassword(email, password);
        }
    }
}