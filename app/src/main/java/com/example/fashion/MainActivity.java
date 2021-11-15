package com.example.fashion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fashion.activities.HoodieActivity;
import com.example.fashion.activities.NewArrivalsActivity;
import com.example.fashion.activities.OuterwearsActivity;
import com.example.fashion.activities.ThongBaoActivity;
import com.example.fashion.activities.TopsActivity;
import com.example.fashion.fragment.GioHangFragment;
import com.example.fashion.fragment.HomeFragment;
import com.example.fashion.fragment.ProfileFragment;

import com.example.fashion.fragment.TimKiemFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Toolbar toolbar;
    AnimatedBottomBar animatedBottomBar;
    FragmentManager fragmentManager;
    ImageView imgNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        initViews(savedInstanceState);
        initComponentsNavHeader();
        notification();
    }
    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(0);
    }

    @SuppressLint("NonConstantResourceId")
    private void initViews(Bundle savedInstanceState) {
        /**
         * Menu Bottom Navigation Drawer
         * */
        animatedBottomBar = findViewById(R.id.navigation);

        if (savedInstanceState == null) {
            animatedBottomBar.selectTabById(R.id.nav_home, true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment)
                    .commit();
        }

        animatedBottomBar.setOnTabSelectListener((lastIndex, lastTab, newIndex, newTab) -> {
            Fragment fragment = null;
            switch (newTab.getId()) {
                case R.id.nav_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.nav_giohang:
                    fragment = new GioHangFragment();
                    break;
                case R.id.nav_thongtin:
                    fragment = new ProfileFragment();
                    break;
            }

            if (fragment != null) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                        .commit();
            } else {
                Log.e(TAG, "Error in creating Fragment");
            }
        });

        /**
         * Menu Navigation Drawer
         **/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setToolbarNavigationClickListener(view -> drawer.openDrawer(GravityCompat.START));
        toggle.setHomeAsUpIndicator(R.drawable.ic_baseline_reorder_24);
        toggle.syncState();
    }

    private void initComponentsNavHeader(){
        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setItemIconTintList(null); //disable tint on each icon to use color icon svg
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_newarivals:
                        Intent intent1 = new Intent(MainActivity.this, NewArrivalsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_tops:
                        Intent intent2 = new Intent(MainActivity.this, TopsActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_outerwear:
                        Intent intent3 = new Intent(MainActivity.this, OuterwearsActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_hoodies:
                        Intent intent4 = new Intent(MainActivity.this, HoodieActivity.class);
                        startActivity(intent4);
                        break;

                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }

            private void Pesan(String pesan) {
                Toast.makeText(MainActivity.this, pesan, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void notification(){
        imgNotification = findViewById(R.id.imgThongBao);
        imgNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThongBaoActivity.class));
            }
        });
    }
//    //Inflate the menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_bottom, menu);
//        return true;
//    }
//
//
//
//    //Handling Action Bar button click
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        switch (item.getItemId()) {
//            //Back button
//            case android.R.id.home:
//                //If this activity started from other activity
//                finish();
//
//            /*If you wish to open new activity and close this one
//            startNewActivity();
//            */
//                return true;
//            case R.id.action_addfav:
//                //addfav (heart icon) was clicked, Insert your after click code here.
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}