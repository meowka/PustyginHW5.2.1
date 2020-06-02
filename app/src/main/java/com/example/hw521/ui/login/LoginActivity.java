package com.example.hw521.ui.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hw521.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Button registerButton;
    String usernameStr;
    String passwordStr;
    String loginFile;
    String passwordFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);
        loginFile = "login_file.txt";
        passwordFile = "password_file.txt";
    }

    public void registerClick(View view) {
        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStreamPass = null;
        try {
            usernameStr = usernameEditText.getText().toString();
            passwordStr = passwordEditText.getText().toString();
            if (usernameStr.equals("") || passwordStr.equals("")) {
                Toast.makeText(LoginActivity.this, "Error login or password", Toast.LENGTH_SHORT).show();
            } else {
                fileOutputStream = openFileOutput(loginFile, MODE_PRIVATE);
                fileOutputStreamPass = openFileOutput(passwordFile, MODE_PRIVATE);
                fileOutputStream.write(usernameStr.getBytes());
                fileOutputStreamPass.write(passwordStr.getBytes());
                Toast.makeText(LoginActivity.this, "register done!", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null && fileOutputStreamPass != null) {
                try {
                    fileOutputStream.close();
                    fileOutputStreamPass.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loginClick(View view) {

        FileInputStream fileInputStream = null;
        FileInputStream fileInputStreamPass = null;
        try {
            usernameStr = usernameEditText.getText().toString();
            passwordStr = passwordEditText.getText().toString();
            fileInputStream = openFileInput(loginFile);
            byte[] bytesLogin = new byte[fileInputStream.available()];
            fileInputStream.read(bytesLogin);
            fileInputStreamPass = openFileInput(passwordFile);
            byte[] bytePass = new byte[fileInputStreamPass.available()];
            fileInputStreamPass.read(bytePass);
            String openLogin = new String(bytesLogin);
            String openPassword = new String(bytePass);
            if (openLogin.equals(usernameStr) && openPassword.equals(passwordStr)) {
                Toast.makeText(LoginActivity.this, "welcome", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "Error login or password", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null && fileInputStreamPass != null) {
                try {
                    fileInputStream.close();
                    fileInputStreamPass.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
