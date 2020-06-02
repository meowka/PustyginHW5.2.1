package com.example.hw521.ui.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hw521.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream fileOutputStream = null;
                FileOutputStream fileOutputStreamPass = null;
                try {
                    fileOutputStream = openFileOutput(loginFile, MODE_PRIVATE);
                    fileOutputStreamPass = openFileOutput(passwordFile, MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriterPass = new OutputStreamWriter(fileOutputStreamPass);
                    BufferedWriter bufferedWriterPass = new BufferedWriter(outputStreamWriterPass);
                    bufferedWriterPass.write(passwordStr);

                    
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    bufferedWriter.write(usernameStr);
                    bufferedWriter.close();
                    bufferedWriterPass.close();
                    Toast.makeText(LoginActivity.this,"register done!",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream fileInputStream = null;
                FileInputStream fileInputStreamPass = null;
                try {
                    fileInputStream = openFileInput(loginFile);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader readerLogin = new BufferedReader(inputStreamReader);
                    fileInputStreamPass = openFileInput(passwordFile);
                    InputStreamReader inputStreamReaderPass = new InputStreamReader(fileInputStreamPass);
                    BufferedReader readerPass = new BufferedReader(inputStreamReaderPass);
                    String openLogin = readerLogin.readLine();
                    String openPassword = readerPass.readLine();
                    if (openLogin.equals(usernameStr) && openPassword.equals(passwordStr)){
                        Toast.makeText(LoginActivity.this,"welcome",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"Error login or password",Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void init(){
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);
        loginFile = "login_file.txt";
        passwordFile = "password_file.txt";
        usernameStr = usernameEditText.getText().toString();
        passwordStr = passwordEditText.getText().toString();

    }



}
