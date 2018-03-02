package com.example.amr.wearit;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amr.wearit.Comman.Comman;
import com.example.amr.wearit.Model.OrderModel;
import com.example.amr.wearit.Model.Request;
import com.example.amr.wearit.SQLDatabase.SQLDatabase;
import com.example.amr.wearit.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager lauout;

    FirebaseDatabase DB;
    DatabaseReference Table;
    TextView TotalPrice;
    Button Submit;
    List<OrderModel> cart=new ArrayList<>();
    CartAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        DB=FirebaseDatabase.getInstance();
        Table=DB.getReference("Orders");


        recyclerView=(RecyclerView)findViewById(R.id.cartData);
        recyclerView.setHasFixedSize(true);

        lauout=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(lauout);

        TotalPrice=(TextView)findViewById(R.id.totalprice);
        Submit=(Button)findViewById(R.id.submitcart);



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cart.size() > 0)
                    showAlertDialog();
                else
                    Toast.makeText(Cart.this,"Empty Cart", Toast.LENGTH_LONG).show();


            }
        });



        LoadClothesList();






    }

    private void showAlertDialog() {

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(Cart.this);
        alertdialog.setTitle("Thanks for your patience:");
        alertdialog.setMessage("Address: ");

        final EditText editTxt = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        editTxt.setLayoutParams(lp);
        alertdialog.setView(editTxt);
        alertdialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        alertdialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Request request = new Request(editTxt.getText().toString(),TotalPrice.getText().toString(),cart,Comman.CurrentUser.getUname());
                Table.child(String.valueOf(System.currentTimeMillis())).setValue(request);
                new SQLDatabase(getBaseContext()).cleanCart();
                Toast.makeText(Cart.this,"Thanks!!!",Toast.LENGTH_LONG).show();
                finish();

            }
        });
        alertdialog.setNegativeButton("No",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertdialog.show();
    }

    private void LoadClothesList() {

        cart=new SQLDatabase(getBaseContext()).GetCart();
        adapter=new CartAdapter(cart,this);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        double total=0;
        for(OrderModel order:cart)
        {
            total+=(Integer.parseInt(order.getItemPrice()))*(Integer.parseInt(order.getItemQuantity()));
        }

        Locale lcl=new Locale("en","US");
        NumberFormat format=NumberFormat.getCurrencyInstance(lcl);

        TotalPrice.setText(format.format(total));

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle().equals(Comman.DELETE))
            deleteCart(item.getOrder());
        return true;
    }

    public void deleteCart(int order) {
        cart.remove(order);

        new SQLDatabase(this).cleanCart();

        for (OrderModel item:cart)
            new SQLDatabase(this).addToCart(item);

        LoadClothesList();
    }
}
