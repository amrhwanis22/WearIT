package com.example.amr.wearit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.amr.wearit.R;
import com.example.amr.wearit.Comman.Comman;
import com.example.amr.wearit.Model.Request;
import com.example.amr.wearit.ViewHolder.OrderHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hassan on 12/30/2017.
 */

public class OrderStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;


    FirebaseRecyclerAdapter<Request,OrderHolder> adapter;


    FirebaseDatabase database;
    DatabaseReference requests;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Orders");

        recyclerView = (RecyclerView)findViewById(R.id.listorder);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders(Comman.CurrentUser.getUname());
    }

    private void loadOrders(String name) {
        System.out.print("ana hena  ");
            adapter = new FirebaseRecyclerAdapter<Request, OrderHolder>(
                Request.class,
                        R.layout.order_layout,
                        OrderHolder.class,
                        requests.orderByChild("name")
                                .equalTo(name)

            )
        {
            @Override
            protected void populateViewHolder(OrderHolder viewHolder,Request model,int position){

            viewHolder.OrderID.setText(adapter.getRef(position).getKey());
            viewHolder.OrderAddress.setText(model.getAddress());
                if(model.getStatus() == "0")
                    viewHolder.OrderStatus.setText("Shipping");
                else
                    viewHolder.OrderStatus.setText("Placed");
                 //   System.out.print("ana jhena aho" + model.getStatus());

           // viewHolder.OrderStatus.setText(Comman.convertCodeToStatus(model.getStatus()));

        }
        };
        recyclerView.setAdapter(adapter);

    }



    }



