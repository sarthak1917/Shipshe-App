package com.example.sarthak.testproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    EditText etxtUsername,etxtPassword;
    Button btnLogin,btnRegister;
    LoginButton btnFab;
    SharedPreferences prefs;
    ProgressDialog dialog;
    CallbackManager callbackManager;
    String username,password;

    void initViews(){
        etxtPassword=(EditText)findViewById(R.id.password);
        etxtUsername=(EditText)findViewById(R.id.userName);

        btnLogin=(Button)findViewById(R.id.loginButton);
        btnFab=(LoginButton)findViewById(R.id.buttonFab);
        btnRegister=(Button)findViewById(R.id.buttonReg);

        btnLogin.setOnClickListener(clickListener);
        btnRegister.setOnClickListener(clickListener);

        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading");


        btnFab.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent i=new Intent(LoginActivity.this,ListActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.loginButton){
               if(username.equals(etxtUsername.getText().toString().trim())&&password.equals(etxtPassword.getText().toString().trim())){
                   readSPData();
               }else{
                   showError();
               }
            }else if(v.getId()==R.id.buttonReg){
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        }
    };

    void showError(){
        etxtPassword.setError("Incorrect Passowrd");
        etxtUsername.setError("Incorrect Username");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        callbackManager=CallbackManager.Factory.create();
        initViews();

        prefs=getSharedPreferences("login_data",MODE_PRIVATE);
        readSPData();
    }

    void readSPData(){
        username=prefs.getString("username_no"," ");
        password=prefs.getString("password"," ");

        if(!(username.equals(" ")||password.equals(" "))){
            etxtUsername.setText(username);
            etxtPassword.setText(password);
            dialog.show();
            try{
                Thread.sleep(300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            Intent i=new Intent(LoginActivity.this,ListActivity.class);
            startActivityForResult(i, 100);
            //now start the list view
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100) {
            dialog.dismiss();
        }else{
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }
    }
}
