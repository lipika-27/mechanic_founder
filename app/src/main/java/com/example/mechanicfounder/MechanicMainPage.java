package com.example.mechanicfounder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mechanicfounder.fragments.Home_Fragment;
import com.example.mechanicfounder.fragments.Logout_Fragment;
import com.example.mechanicfounder.fragments.Update_Fragment;
import com.google.android.material.navigation.NavigationView;

public class MechanicMainPage extends AppCompatActivity {

    NavigationView navmenu;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_main_page);
       // getSupportActionBar().hide();

        toolbar = findViewById(R.id.mechanic_toolbar);
        navmenu = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.nav_draw);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Home_Fragment()).commit();

        navmenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            Fragment fragment;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.menu_home :
                        fragment = new Home_Fragment();
                       // Toast.makeText(getApplicationContext(),"this is home",Toast.LENGTH_SHORT).show();
                      //  drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_update :
                        fragment = new Update_Fragment();
                      //  Toast.makeText(getApplicationContext(),"this is update",Toast.LENGTH_SHORT).show();
                       // drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_logout :
                        fragment = new Logout_Fragment();
                      //  Toast.makeText(getApplicationContext(),"user logout",Toast.LENGTH_SHORT).show();
                        //drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                finish();
                return true;
            }
        });
    }
}