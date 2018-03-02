package com.example.amr.wearit.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;

import com.example.amr.wearit.Comman.Comman;
import com.example.amr.wearit.Model.Request;
import com.example.amr.wearit.R;
import com.example.amr.wearit.Home;
import com.example.amr.wearit.OrderStatus;
import com.example.amr.wearit.SignIn;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListenOrder extends Service implements ChildEventListener{

    FirebaseDatabase db;
    DatabaseReference requests;
    public ListenOrder() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        return null;
    }


    public void onCreate(){
        super.onCreate();

        db = FirebaseDatabase.getInstance();
        requests = db.getReference("Requests");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        requests.addChildEventListener(this);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        Request request = dataSnapshot.getValue(Request.class);
        showNotification(dataSnapshot.getKey(),request);
    }

    private void showNotification(String key, Request request) {
        Intent intent =  new Intent(getBaseContext(),OrderStatus.class );
        PendingIntent content = PendingIntent.getActivity(getBaseContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());

        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setTicker("Hollister")
                .setContentInfo("Your order was updated")
                .setContentText("Order @"+key+"was update status to "+ Comman.convertCodeToStatus(request.getStatus()))
                .setContentIntent(content)
                .setContentInfo("Info")
                .setSmallIcon(R.drawable.bell_ring);


        NotificationManager notmanager = (NotificationManager)getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notmanager.notify(1,builder.build());

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}



