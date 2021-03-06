package com.example.hw521.ui.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hw521.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private String usernameStr;
    private String passwordStr;
    private String loginFile;
    private String passwordFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginFile = "login_file.txt";
        passwordFile = "password_file.txt";
    }

    public void registerClick(View view) {
        usernameStr = usernameEditText.getText().toString();
        passwordStr = passwordEditText.getText().toString();
        if (usernameStr.equals("") || passwordStr.equals("")) {
            Toast.makeText(LoginActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
        } else {
            try (FileOutputStream fileOutputStream = openFileOutput(loginFile, MODE_PRIVATE);
                 FileOutputStream fileOutputStreamPass = openFileOutput(passwordFile, MODE_PRIVATE)) {
                fileOutputStream.write(usernameStr.getBytes());
                fileOutputStreamPass.write(passwordStr.getBytes());
                Toast.makeText(LoginActivity.this, R.string.regDone, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loginClick(View view) {
        usernameStr = usernameEditText.getText().toString();
        passwordStr = passwordEditText.getText().toString();
        try (FileInputStream fileInputStream = openFileInput(loginFile);
             FileInputStream fileInputStreamPass = openFileInput(passwordFile)) {
            byte[] bytesLogin = new byte[fileInputStream.available()];
            fileInputStream.read(bytesLogin);
            byte[] bytePass = new byte[fileInputStreamPass.available()];
            fileInputStreamPass.read(bytePass);
            String openLogin = new String(bytesLogin);
            String openPassword = new String(bytePass);
            if (openLogin.equals(usernameStr) && openPassword.equals(passwordStr)) {
                Toast.makeText(LoginActivity.this, R.string.welcome, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

