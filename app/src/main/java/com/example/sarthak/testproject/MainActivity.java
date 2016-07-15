package com.example.sarthak.testproject;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    Button bSign,bRegister;
    LoginButton bFacebook;
    CallbackManager callbackManager;

    void initViews(){
        bSign=(Button)findViewById(R.id.buttonSignIn);
        bRegister=(Button)findViewById(R.id.buttonReg);
        bFacebook=(LoginButton)findViewById(R.id.buttonFacebook);
        bSign.setOnClickListener(clickListener);
        bRegister.setOnClickListener(clickListener);

        bFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent i=new Intent(MainActivity.this,ListActivity.class);
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
               if(v.getId()==R.id.buttonSignIn){
                   Intent i=new Intent(MainActivity.this,LoginActivity.class);
                   startActivity(i);
               }else if(v.getId()==R.id.buttonReg){
                   Intent i=new Intent(MainActivity.this,RegisterActivity.class);
                   startActivity(i);
               }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        callbackManager=CallbackManager.Factory.create();
        initViews();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode,data);
    }
}
