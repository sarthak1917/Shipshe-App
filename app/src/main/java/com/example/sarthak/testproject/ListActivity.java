package com.example.sarthak.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sarthak.testproject.Adapter.StoreAdapter;
import com.example.sarthak.testproject.model.StoreItem;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<StoreItem> itemList,selectedItems,removeItems;
    StoreAdapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */

                Intent i=new Intent(ListActivity.this,CartActivity.class);
                i.putExtra("selectedValues",selectedItems);
                startActivityForResult(i, 100);
            }
        });


        lv=(ListView)findViewById(R.id.storeList);
        itemList=new ArrayList<StoreItem>();
        selectedItems=new ArrayList<StoreItem>();
        initializeAdapter();
        lv.setAdapter(adapter);
        //lv.setOnItemClickListener(itemClickListener);
        lv.setOnItemClickListener(clickListener);

    }

    ListView.OnItemClickListener clickListener=new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedItems.add(itemList.get(position));
            Toast.makeText(ListActivity.this,"added to cart",Toast.LENGTH_SHORT).show();
        }
    };

    /*
    AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedItems.add(itemList.get(position));
            Toast.makeText(ListActivity.this,"added to cart",Toast.LENGTH_SHORT).show();
        }
    };
    */



    void initializeAdapter(){
        adapter=new StoreAdapter(this,R.layout.list_item,itemList);
        addData();
    }

    void addData(){
       itemList.add(new StoreItem(3,"Cold Drink Set","set of 3 Cold Drinks :)",R.drawable.first,80));
        itemList.add(new StoreItem(4,"Fancy Feast Cake"," a yummy yummy cake:)",R.drawable.second,60));
        itemList.add(new StoreItem(5,"MilkyWay Set","At the rate pf 2!! get 4...:)",R.drawable.third,100));
        itemList.add(new StoreItem(2,"Glitter Set","Get this cool glitters :)",R.drawable.fourth, 50));
        itemList.add(new StoreItem(3,"Red Mugs Set","This is a set of 3 Mugs :)",R.drawable.fifth,80));
        itemList.add(new StoreItem(2,"gift Item Set","impress by this amazing gift :)",R.drawable.sixth,40));
        itemList.add(new StoreItem(3,"Ninteno Car","A toy for children",R.drawable.seventh,30));
        itemList.add(new StoreItem(4,"Texas Necklace","A cool ranger necklace",R.drawable.eigth,75));
        itemList.add(new StoreItem(3,"Duracell","Cell which lasts longer",R.drawable.ninth,60));
        itemList.add(new StoreItem(4,"Ancient Mug","An Egyptian Mug",R.drawable.tenth,90));
        itemList.add(new StoreItem(3,"Beta Energy Drink","Drink for all",R.drawable.eleventh,70));
        itemList.add(new StoreItem(3,"Tape","Glue Glue Glue",R.drawable.twelth,30));
        itemList.add(new StoreItem(2,"James Bond T-Shirt","Wanna be James Bond",R.drawable.thirteen,100));
        itemList.add(new StoreItem(5,"Skullcandy(Black)","Earphones ;)",R.drawable.fourteen,90));
        itemList.add(new StoreItem(5,"Skullcandy(White)","Earphones :)",R.drawable.fifteen,90));
        itemList.add(new StoreItem(4,"Skull earphones","go get ur ears",R.drawable.sixteen,90));
        itemList.add(new StoreItem(2,"Toy Car","Play u little Kids",R.drawable.seventh,40));
        itemList.add(new StoreItem(3,"EXL earphones","earphones ",R.drawable.eigthteen,70));
        itemList.add(new StoreItem(3,"Skull HeadPhones","Buy and Enjoy",R.drawable.nineteen,90));
        itemList.add(new StoreItem(3,"Clipped Earphones","Enjoy the earphones",R.drawable.twenthy,80));
        itemList.add(new StoreItem(4,"Skates","Race it u all racers..",R.drawable.twentyone,100));
        itemList.add(new StoreItem(3,"Ice Skates","ice icey ice",R.drawable.rwentytwo,80));
        itemList.add(new StoreItem(3,"Skateboard","go and do skating....",R.drawable.twenty_three,100));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int val=data.getIntExtra("result",0);
        if(val==1) {
            selectedItems.removeAll(selectedItems);
        }else if(val==2){
            selectedItems.removeAll(selectedItems);
            removeItems=(ArrayList<StoreItem>)data.getSerializableExtra("dataList");
            for(int i=0;i<removeItems.size();i++){
                selectedItems.add(removeItems.get(i));
            }
        }
    }
}
