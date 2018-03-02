package com.example.amr.wearit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amr.wearit.Comman.Comman;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {


    CallbackManager cbm;
    TextView email;
    ProgressDialog pd;
    ImageView IMavatar;
    LoginButton btn;
    Button BtnSignIn,BtnSignUp;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cbm.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbm=CallbackManager.Factory.create();
        email=(TextView)findViewById(R.id.txtMail);
        IMavatar=(ImageView)findViewById(R.id.avatar);
        btn=(LoginButton)findViewById(R.id.fblogin);
        btn.setReadPermissions(Arrays.asList("public_profile","email"));

        btn.registerCallback(cbm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                pd=new ProgressDialog(MainActivity.this);
                pd.setMessage("Getting your data pleas wait");
                pd.show();
                GraphRequest req=GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        pd.dismiss();
                        Log.d("response",response.toString());

                        GetData(object);
                    }
                });
                Bundle param=new Bundle();
                param.putString("fields","id,email");
                req.setParameters(param);
                req.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        if(AccessToken.getCurrentAccessToken()!=null)
        {

            email.setText(AccessToken.getCurrentAccessToken().getUserId());

        }

        BtnSignIn=(Button)findViewById(R.id.signinbtn);
        BtnSignUp=(Button)findViewById(R.id.signupbtn);


        BtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),SignIn.class));

            }
        });
        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUp.class));
            }
        });
    }

    private void GetData(JSONObject obj) {
        try
        {
            URL profilePic= new URL("https://graph.facebook.com/"+obj.getString("id")+"/picture?width=250&height=250");


            Picasso.with(this).load(profilePic.toString()).into(IMavatar);

            email.setText(obj.getString("email"));
            email.setTextColor(Color.RED);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void printKeyHash() {

        try{
            PackageInfo info=getPackageManager().getPackageInfo("com.example.amr.wearit", PackageManager.GET_SIGNATURES);
            for(Signature s: info.signatures)
            {
                MessageDigest md=MessageDigest.getInstance("SHA");
                md.update(s.toByteArray());
                Log.d("Key Hash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
