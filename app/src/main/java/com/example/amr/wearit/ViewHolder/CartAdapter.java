package com.example.amr.wearit.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.amr.wearit.Comman.Comman;
import com.example.amr.wearit.Model.Clothes;
import com.example.amr.wearit.Model.OrderModel;
import com.example.amr.wearit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by amr on 12/30/17.
 */

public class CartAdapter extends RecyclerView.Adapter<CarViewHolder>{
    private List<OrderModel> ListData=new ArrayList<>();

    private Context context;
    FirebaseDatabase DB;
    DatabaseReference Table;




    public CartAdapter(List<OrderModel> listData, Context context) {
        this.ListData = listData;
        this.context = context;

        this.DB=FirebaseDatabase.getInstance();
        this.Table=DB.getReference("Clothes");
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.cart_layout,parent,false);
        return new CarViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final CarViewHolder holder, final int position) {



        Table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Clothes model=dataSnapshot.child(ListData.get(position).getItemID()).getValue(Clothes.class);
                String  URL=model.getItemImage();
                System.out.println("Image ITem:"+URL);
                Picasso.with(context).load(URL).into(holder.imageView);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        TextDrawable drawable=TextDrawable.builder().buildRound(ListData.get(position).getItemQuantity(), Color.BLACK);
        //holder.imageView.setImageDrawable(drawable);
        holder.cart_item_count.setText(ListData.get(position).getItemQuantity());
        Locale locl=new Locale("en","EGP");
        NumberFormat frmat=NumberFormat.getCurrencyInstance(locl);
        double Price=(Integer.parseInt(ListData.get(position).getItemPrice()))*(Integer.parseInt(ListData.get(position).getItemQuantity()));
        holder.cart_item_price.setText(frmat.format(Price));
        holder.cart_item_name.setText(ListData.get(position).getItemName());


    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }
}
