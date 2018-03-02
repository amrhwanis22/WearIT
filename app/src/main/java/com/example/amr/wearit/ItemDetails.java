package com.example.amr.wearit;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.amr.wearit.Model.Clothes;
import com.example.amr.wearit.Model.OrderModel;
import com.example.amr.wearit.SQLDatabase.SQLDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.Console;

public class ItemDetails extends AppCompatActivity {

    TextView item_name,item_price,item_available;

    ImageView Item_image;
    Button btnCart;
    ElegantNumberButton numberButton;
   // RadioGroup Size;
    String ItemID="";

    FirebaseDatabase DB;
    DatabaseReference Table;
    RadioButton Rb;
    Clothes currentCloth;
    String[] numPanels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        DB=FirebaseDatabase.getInstance();
        Table=DB.getReference("Clothes");
     //   Size = (RadioGroup)findViewById(R.id.sizes);
        numberButton=(ElegantNumberButton)findViewById(R.id.numberbutton);
        btnCart=(Button)findViewById(R.id.btncart);
        btnCart.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){



                Log.i("Item Name: " ,currentCloth.getName());
                Toast.makeText(ItemDetails.this,"Item Is added to your Cart",Toast.LENGTH_LONG).show();

                new SQLDatabase(getBaseContext()).addToCart(new OrderModel(
                        ItemID,
                        currentCloth.getName(),
                        numberButton.getNumber(),
                        currentCloth.getPrice(),
                        currentCloth.getDiscount()
                ));

                Toast.makeText(ItemDetails.this,"Item Is added to your Cart",Toast.LENGTH_LONG).show();
            }
        });

        item_name=(TextView)findViewById(R.id.name_item);
        item_available=(TextView)findViewById(R.id.item_availabe);
        item_price=(TextView)findViewById(R.id.price_item);
        Item_image=(ImageView)findViewById(R.id.img_item);
       // Size = (RadioGroup) findViewById(R.id.sizes);

        if(getIntent()!=null) {
            ItemID = getIntent().getStringExtra("ItemID");
        }
        if(!ItemID.isEmpty())
        {

            getDetialsItem(ItemID);
        }




    }



    private void getDetialsItem(final String itemID) {
        Table.child(itemID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentCloth =dataSnapshot.getValue(Clothes.class);


                Picasso.with(getBaseContext()).load(currentCloth.getItemImage()).into(Item_image);


                item_price.setText(currentCloth.getPrice()+" EGP");

//                Toast.makeText(ItemDetails.this,Rb.getText(),Toast.LENGTH_LONG).show();

                if(currentCloth.getItemAvailable().equals("1")){

                    item_available.setText("Available");
                    item_available.setTextColor(Color.LTGRAY);




                }else{

                    item_available.setText("Not Available");
                    item_available.setTextColor(Color.RED);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
