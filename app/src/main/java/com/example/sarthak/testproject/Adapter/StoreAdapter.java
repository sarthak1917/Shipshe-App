package com.example.sarthak.testproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarthak.testproject.ListActivity;
import com.example.sarthak.testproject.R;
import com.example.sarthak.testproject.model.StoreItem;

import java.util.ArrayList;

/**
 * Created by Sarthak on 3/12/2016.
 */
public class StoreAdapter extends ArrayAdapter<StoreItem> {

    ArrayList<StoreItem> itemList;
    Context ctx;
    TextView txtTitle,txtDesc,txtPrize;
    RatingBar bar;
    ImageView iv;


    public StoreAdapter(Context context, int resource, ArrayList<StoreItem> objects) {
        super(context, resource, objects);
        ctx=context;
        itemList=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView= LayoutInflater.from(ctx).inflate(R.layout.list_item,parent,false);
        txtTitle=(TextView)myView.findViewById(R.id.textViewTitle);
        txtDesc=(TextView)myView.findViewById(R.id.textViewDesc);
        txtPrize=(TextView)myView.findViewById(R.id.textViewPrize);

        bar=(RatingBar)myView.findViewById(R.id.ratingBar);
        iv=(ImageView)myView.findViewById(R.id.imageViewList);

        addData(position);
        return myView;
    }

    void addData(final int position){
        txtTitle.setText(itemList.get(position).getTitle());
        txtDesc.setText(itemList.get(position).getDiscription());
        txtPrize.setText(itemList.get(position).getPrize()+"");

        bar.setRating(itemList.get(position).getRating());
        iv.setImageResource(itemList.get(position).getImageResource());


        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(itemList.get(position).getRating()-rating>0){
                    Toast.makeText(ctx,"Disliked the item",Toast.LENGTH_SHORT).show();
                }else if(itemList.get(position).getRating()-rating<0){
                    Toast.makeText(ctx,"Thank you for liking our item",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}

