package com.hcmus.android.weshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hcmus.android.weshare.viewmodel.RegisterViewModel;

import static androidx.lifecycle.ViewModelProvider.*;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;

    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();
        initViewModel();
    }

    private void initViewModel() {
        // TODO: init view model
        // TODO: add observe behavior for the Mutable Live Data
    }

    private void initComponents() {
        // TODO: init EditText fields
    }

    public void registerButtonClick(View view) {
        // TODO: Get the email and password, call the appropriate register function
    }
}