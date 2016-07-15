package com.example.sarthak.testproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sarthak.testproject.Adapter.StoreAdapter;
import com.example.sarthak.testproject.model.StoreItem;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ListView lv;
    EditText txtCost;
    Button btnPay;
    Intent i;
    ArrayList<StoreItem> data;
    StoreAdapter adapter;
    int pos;
    ImageView thankImage,payThankImage;
    AlertDialog.Builder builderDelete,builderPay;
    AlertDialog dialogDelete,dialogPay;


    void initViews(){
        lv=(ListView)findViewById(R.id.listViewcart);
        txtCost=(EditText)findViewById(R.id.editTextCost);
        btnPay=(Button)findViewById(R.id.buttonPay);
        thankImage=(ImageView)findViewById(R.id.imageViewThank);
        thankImage.setImageResource(R.drawable.thank_you);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                dialogDelete.show();
            }
        });

        deleteDialogBuilder();

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPay.show();
            }
        });

        payDialogBuilder();
    }

    void deleteDialogBuilder(){
        builderDelete=new AlertDialog.Builder(this);
        builderDelete.setMessage("Do u want to remove this item from Cart?");
        builderDelete.setTitle("Delete Item");
        builderDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                data.remove(pos);
                adapter.notifyDataSetChanged();
                calCost();
            }
        });
        builderDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogDelete.dismiss();
            }
        });
        dialogDelete=builderDelete.create();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent returnIntent=new Intent();
            returnIntent.putExtra("result",2);
            returnIntent.putExtra("dataList", data);
            setResult(RESULT_OK,returnIntent);
            return super.onKeyDown(keyCode,event);

        }
        return super.onKeyDown(keyCode,event);
    }

    void payDialogBuilder(){
        builderPay=new AlertDialog.Builder(this);
        builderPay.setTitle("Payment Screen");

        payThankImage=new ImageView(this);
        payThankImage.setImageResource(R.drawable.thanks);

        builderPay.setView(payThankImage);
        builderPay.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                data.removeAll(data);
                Intent returnIntent=new Intent();
                returnIntent.putExtra("result",1);
                setResult(Activity.RESULT_OK,returnIntent);
                CartActivity.this.finish();
            }
        });
        dialogPay=builderPay.create();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initViews();
        i=getIntent();
        data=(ArrayList<StoreItem>)i.getSerializableExtra("selectedValues");
        adapter=new StoreAdapter(this,R.layout.list_item,data);
        lv.setAdapter(adapter);
        calCost();
    }

    void calCost(){
        int totalCost=0;
        for(int i=0;i<data.size();i++){
            totalCost+=data.get(i).getPrize();
        }
        txtCost.setText(totalCost+"Rs");
        if(data.size()>2){
            thankImage.setVisibility(View.GONE);
        }
    }
}
