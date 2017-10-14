package com.example.alili_yyjcnwj.gpa;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
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

import org.hyunjun.school.School;
import org.hyunjun.school.SchoolException;
import org.hyunjun.school.SchoolMenu;
import org.hyunjun.school.SchoolSchedule;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private  Fragment menuFragment;
    private  Fragment scheduleFragment;
    private  Fragment notificationFragment;
    private  Fragment settingsFragment;

    Thread Snotification = new Thread(){
        public void run(){
            School api = new School(School.Type.HIGH, School.Region.SEOUL, "B100000373");

            try {
                List<SchoolSchedule> scheduleList = api.getMonthlySchedule(2017, 10);

                for(int i = 0; i< scheduleList.size(); i++) {
                    Log.i("School", (i+1) + "일 학사일정");
                    Log.i("School", scheduleList.get(i).schedule);
                }
                Log.i("School",scheduleList.get(14).schedule);
            } catch (SchoolException e) {
                Log.i("School", e.getMessage());
            }
        }
    };
    Thread Smenu = new Thread(){
        public void run() {
            School api = new School(School.Type.HIGH, School.Region.SEOUL, "B100000373");

            try {
                List<SchoolMenu> menuList = api.getMonthlyMenu(2017, 10);

                for (int i = 0; i < menuList.size(); i++) {
                    Log.i("School", (i + 1) + "일 급식");
                    Log.i("School", menuList.get(i).toString());
                }
                Log.i("School", menuList.get(14).lunch);
                Log.i("School", menuList.get(14).dinner);
            } catch (SchoolException e) {
                Log.i("School", e.getMessage());
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        menuFragment  = new menuFragment();
        scheduleFragment = new scheduleFragment();
        notificationFragment = new notificationFragment();
        settingsFragment = new settingsFragment();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Snotification.start();
        //Smenu.start();


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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        //FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_menu) {
            // Handle the camera action
            transaction.replace(R.id.content_main, menuFragment);
        } else if (id == R.id.nav_schedule) {
            transaction.replace(R.id.content_main, scheduleFragment);

        } else if (id == R.id.nav_notification) {
            transaction.replace(R.id.content_main, notificationFragment);

        } else if (id == R.id.nav_settings) {
            transaction.replace(R.id.content_main, settingsFragment);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
