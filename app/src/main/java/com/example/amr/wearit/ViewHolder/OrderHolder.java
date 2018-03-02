package com.example.amr.wearit.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.amr.wearit.R;
import com.example.amr.wearit.Interface.ItemClickListener;

/**
 * Created by Hassan on 12/31/2017.
 */

public class OrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView OrderID,OrderStatus,OrderAddress;
    private ItemClickListener itemClickListener;


    public OrderHolder(View itemView) {
        super(itemView);


            OrderID = (TextView)itemView.findViewById(R.id.order_id);
            OrderStatus = (TextView)itemView.findViewById(R.id.order_status);
            OrderAddress = (TextView)itemView.findViewById(R.id.order_add);


            itemView.setOnClickListener(this);
        }



    @Override
    public void onClick(View v) {

            itemClickListener.onClick(v,getAdapterPosition(),false);


    }


    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }
}
