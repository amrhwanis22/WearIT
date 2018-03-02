package com.example.amr.wearit.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amr.wearit.Interface.ItemClickListener;
import com.example.amr.wearit.R;
import com.google.android.gms.actions.ItemListIntents;

/**
 * Created by amr on 12/26/17.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public Button txtMenu;
    public ImageView ImageMeny;
    private ItemClickListener ICL;


    public MenuViewHolder(View itemView) {
        super(itemView);
        txtMenu=(Button) itemView.findViewById(R.id.menu_text);
        ImageMeny=(ImageView)itemView.findViewById(R.id.menu_image);
        itemView.setOnClickListener(this);

    }

    public void setICL(ItemClickListener ICL) {
        this.ICL = ICL;
    }
    public void setButton(ItemClickListener sss)
    {

    }

    @Override
    public void onClick(View v) {
        ICL.onClick(v,getAdapterPosition(),false);
        txtMenu.setOnClickListener(MenuViewHolder.this);
    }
}
