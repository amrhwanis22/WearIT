package com.example.amr.wearit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amr.wearit.Comman.Comman;
import com.example.amr.wearit.Interface.ItemClickListener;
import com.example.amr.wearit.Model.Category;
import com.example.amr.wearit.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



        FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter;


    FirebaseDatabase DB;
    DatabaseReference Category;
    TextView FullName;
    RecyclerView RvMenu;
    RecyclerView.LayoutManager LayoutMan;

    String Uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);



        // initialize database
        DB=FirebaseDatabase.getInstance();

        Category=DB.getReference("Category");



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Cart.class));

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        Uname=Comman.CurrentUser.getName().toString();
        View HeaderView =navigationView.getHeaderView(0);
        FullName=(TextView)HeaderView.findViewById(R.id.fullname);
        FullName.setText(Uname);



        // Load Data From DataBase

        RvMenu=(RecyclerView)findViewById(R.id.recycleView);
        RvMenu.setHasFixedSize(true);
        LayoutMan=new LinearLayoutManager(this);

        RvMenu.setLayoutManager(LayoutMan);


        LoadMenu();

    }

    private void LoadMenu() {


            adapter=new FirebaseRecyclerAdapter<com.example.amr.wearit.Model.Category, MenuViewHolder>(Category.class,R.layout.menu_item,MenuViewHolder.class,Category) {


            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {


                viewHolder.txtMenu.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.ImageMeny);
                final Category ClickItem=model;

                viewHolder.setICL(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int Position, boolean isLongClick) {

                        startActivity(new Intent(Home.this,ItemList.class).putExtra("CategoryID",adapter.getRef(Position).getKey()));

                    }

                    @Override
                    public void setOnClickListener(View view, int Position, boolean isLongClick) {

                        startActivity(new Intent(Home.this,ItemList.class).putExtra("CategoryID",adapter.getRef(Position).getKey()));

                    }
                });

            }
        };
        RvMenu.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cart) {
            // Handle the camera action

            startActivity(new Intent(getApplicationContext(),Cart.class));
        } else if (id == R.id.nav_logout) {
            Intent signIn = new Intent(getApplicationContext(),SignIn.class);
            signIn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(signIn);



        } else if (id == R.id.nav_menu) {
            startActivity(new Intent(getApplicationContext(),Stores.class));

        } else if (id == R.id.nav_orders) {
            startActivity(new Intent(getApplicationContext(),OrderStatus.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
