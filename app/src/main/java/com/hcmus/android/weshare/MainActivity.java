package com.hcmus.android.weshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hcmus.android.weshare.viewmodel.AuthenticationViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;

    private AuthenticationViewModel authenticationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        initViewModel();
        navigateToRegister(); // For testing the register function
    }

    private void initViewModel() {
        // TODO: init view model
        // TODO: add observe behavior for the Mutable Live Data
    }

    private void initComponents() {
        // TODO: init EditText fields
    }

    public void loginButtonClick(View view) {
        // TODO: Get the email and password, call the appropriate login function
    }

    public void navigateToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}