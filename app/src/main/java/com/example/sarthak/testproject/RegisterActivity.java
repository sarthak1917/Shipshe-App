package com.example.sarthak.testproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class RegisterActivity extends AppCompatActivity {

    EditText etxtName,etxtPhone,etxtPass,etxtConPass;
    Button btnReg;
    LoginButton btnFab;
    SharedPreferences prefs;
    SharedPreferences.Editor edit;
    CallbackManager callbackManager;


    void initViews(){
        etxtName=(EditText)findViewById(R.id.editTextName);
        etxtPhone=(EditText)findViewById(R.id.editTextPhone);
        etxtPass=(EditText)findViewById(R.id.editTextPassword);
        etxtConPass=(EditText)findViewById(R.id.editTextConfirmPassword);

        btnReg=(Button)findViewById(R.id.buttonRegisterreg);
        btnFab=(LoginButton)findViewById(R.id.buttonfabReg);

        btnReg.setOnClickListener(clickListener);

        btnFab.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent i=new Intent(RegisterActivity.this,ListActivity.class);
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
            if (v.getId() == R.id.buttonRegisterreg) {
                if (check()) {
                    if (checkPass()) {
                        writeSPData();
                        Intent i = new Intent(RegisterActivity.this, ListActivity.class);
                        startActivityForResult(i, 100);
                    } else {
                        etxtConPass.setError("Password does not match");
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_register);
        callbackManager=CallbackManager.Factory.create();
        initViews();

        prefs=getSharedPreferences("login_data",MODE_PRIVATE);
        //readSPData();
    }

    boolean check(){
        if(etxtConPass.getText().toString().trim().equals("")||etxtPass.getText().toString().trim().equals("")||
                etxtPhone.getText().toString().trim().equals("")||etxtName.getText().toString().trim().equals("")){

            etxtName.setError("Name can't be empty");
            etxtPhone.setError("Phone can't be empty");
            etxtPass.setError("Password can't be empty");
            etxtConPass.setError("Please confirm the password");

            return false;
        }else{
            return true;
        }
    }

    boolean checkPass(){
        if(etxtPass.getText().toString().trim().equals(etxtConPass.getText().toString().trim())){
            return true;
        }else{
            return false;
        }
    }



    void writeSPData(){
        edit=prefs.edit();
        edit.putString("name",etxtName.getText().toString().trim());
        edit.putString("password",etxtPass.getText().toString().trim());
        edit.putString("username_no",etxtPhone.getText().toString().trim());
        edit.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100) {
            finish();
        }else{
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }

    }
}
