package com.example.amr.wearit.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amr.wearit.Interface.ItemClickListener;
import com.example.amr.wearit.R;

/**
 * Created by amr on 12/29/17.
 */

public class ClothesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    public TextView clothes_Name;
    public ImageView clothes_Image;
    private ItemClickListener ICL;


    public void setICL(ItemClickListener ICL) {
        this.ICL = ICL;
    }

    public ClothesHolder(View itemView) {
        super(itemView);
        clothes_Name=(TextView)itemView.findViewById(R.id.clothes_text);
        clothes_Image=(ImageView)itemView.findViewById(R.id.clothes_image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        ICL.onClick(v,getAdapterPosition(),false);

    }
}
