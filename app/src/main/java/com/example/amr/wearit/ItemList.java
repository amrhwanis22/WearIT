package com.example.amr.wearit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.amr.wearit.Interface.ItemClickListener;
import com.example.amr.wearit.Model.Clothes;
import com.example.amr.wearit.ViewHolder.ClothesHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ItemList extends AppCompatActivity {


    RecyclerView recycleview;
    RecyclerView.LayoutManager layoutMan;
    private String CatID="";
    FirebaseDatabase DB;
    DatabaseReference Table;
    TextView catname;
    FirebaseRecyclerAdapter<Clothes,ClothesHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        DB=FirebaseDatabase.getInstance();

        Table=DB.getReference("Clothes");


        recycleview=(RecyclerView)findViewById(R.id.recycler_item);
        recycleview.setHasFixedSize(false);
        layoutMan=new LinearLayoutManager(this);
        recycleview.setLayoutManager(layoutMan);
        if(getIntent()!=null)
        {
            CatID=getIntent().getStringExtra("CategoryID");
        }
        if(!CatID.isEmpty() && CatID!=null)
        {

            LoadListFood(CatID);
        }

    }

    private void LoadListFood(String catID) {
        adapter=new FirebaseRecyclerAdapter<Clothes, ClothesHolder>(Clothes.class,R.layout.cloth_item,ClothesHolder.class,Table.orderByChild("categoryID").equalTo(CatID)) {
            @Override
            protected void populateViewHolder(ClothesHolder viewHolder, Clothes model, int position) {

                viewHolder.clothes_Name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getItemImage()).into(viewHolder.clothes_Image);
                final Clothes locl=model;

                viewHolder.setICL(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int Position, boolean isLongClick) {

                        startActivity(new Intent(getApplicationContext(),ItemDetails.class).putExtra("ItemID",adapter.getRef(Position).getKey()));



                    }

                    @Override
                    public void setOnClickListener(View view, int Position, boolean isLongClick) {

                    }

                });


            }
        };

        recycleview.setAdapter(adapter);
    }
}
