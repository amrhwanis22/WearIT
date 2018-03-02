package com.example.amr.wearit.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amr.wearit.Cart;
import com.example.amr.wearit.Comman.Comman;
import com.example.amr.wearit.Interface.ItemClickListener;
import com.example.amr.wearit.R;

/**
 * Created by amr on 12/30/17.
 */



public class CarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {





    public TextView cart_item_name,cart_item_price;
    public TextView cart_item_count;
    public ImageView imageView;

    private ItemClickListener itemClickListener;


    public void setCart_item_name(TextView cart_item_name) {
        this.cart_item_name = cart_item_name;
    }


    public CarViewHolder(final View itemView) {
        super(itemView);
        cart_item_name=(TextView)itemView.findViewById(R.id.cart_item_name);
        cart_item_price=(TextView)itemView.findViewById(R.id.cart_item_price);
        cart_item_count=(TextView)itemView.findViewById(R.id.cart_item_count);

        imageView = (ImageView)itemView.findViewById(R.id.cartImage);
        itemView.setOnCreateContextMenuListener(this);

    }

    @Override
    public void onClick(View v) {



    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Select Action");
        menu.add(0,0,getAdapterPosition(), Comman.DELETE);

    }
}


