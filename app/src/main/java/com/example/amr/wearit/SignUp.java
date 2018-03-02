package com.example.amr.wearit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amr.wearit.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    Button SignUpbtn;
    EditText UName,Password;
    String SName,SPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SignUpbtn=(Button)findViewById(R.id.signupbtnact);
        UName=(MaterialEditText)findViewById(R.id.unameup);
        Password=(MaterialEditText)findViewById(R.id.passwordup);

        final FirebaseDatabase DB=FirebaseDatabase.getInstance();

        final DatabaseReference TableDb=DB.getReference("User");
        SignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog=new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please Wait!!");
                mDialog.show();
                SName=UName.getText().toString();
                SPassword=Password.getText().toString();

                TableDb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(SName).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this,"User Already Exists",Toast.LENGTH_LONG).show();
                        }else{


                            mDialog.dismiss();
                            User U=new User(SName,SPassword);
                            TableDb.child(SName).setValue(U);
                            Toast.makeText(SignUp.this,"New User Have been Added",Toast.LENGTH_LONG).show();
                            finish();


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
