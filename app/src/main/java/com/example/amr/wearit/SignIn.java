package com.example.amr.wearit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amr.wearit.Comman.Comman;
import com.example.amr.wearit.Model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    EditText Uname,Password;
    Button SignIn;
    String SUName,SPassword;
    FirebaseDatabase DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        Uname=(MaterialEditText)findViewById(R.id.uname);
        Password=(MaterialEditText)findViewById(R.id.password);
        SignIn=(Button)findViewById(R.id.signinbtnact);
        FirebaseApp.initializeApp(this);
        DB=FirebaseDatabase.getInstance();

        final DatabaseReference TableDb=DB.getReference("User");

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog=new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please Wait!!");
                mDialog.show();
                SUName=Uname.getText().toString();
                SPassword=Password.getText().toString();
                Log.i("UName: ",SUName);
                TableDb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!SUName.isEmpty()&&!SPassword.isEmpty()){
                        if(dataSnapshot.child(SUName).exists())
                        {

                        mDialog.dismiss();
                        User U=dataSnapshot.child(SUName).getValue(User.class);
                        U.setUname(SUName);

                        if(U.getPassword().equals(SPassword))
                        {
                            Comman.CurrentUser=U;
                            startActivity(new Intent(SignIn.this,Home.class ));
                            finish();
                        }else{

                            Toast.makeText(SignIn.this,"Wrong",Toast.LENGTH_LONG).show();
                        }
                        }else{
                            mDialog.dismiss();


                            Toast.makeText(SignIn.this,"User Dosnt Exist in database",Toast.LENGTH_LONG).show();
                        }
                        }else{

                            mDialog.dismiss();
                            Toast.makeText(SignIn.this,"Please entre user name and password",Toast.LENGTH_LONG).show();

                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
