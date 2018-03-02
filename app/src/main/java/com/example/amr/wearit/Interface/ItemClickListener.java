package com.example.amr.wearit.Interface;

import android.view.View;

/**
 * Created by amr on 12/26/17.
 */

public interface ItemClickListener {

    void onClick(View view, int Position, boolean isLongClick);
    void setOnClickListener(View view, int Position, boolean isLongClick);
}
