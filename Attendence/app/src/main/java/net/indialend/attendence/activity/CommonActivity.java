package net.indialend.attendence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import net.indialend.attendence.R;
import net.indialend.attendence.bean.Staff;
import net.indialend.attendence.dao.DatabaseHandler;
import net.indialend.attendence.operation.ImageLoadTask;
import net.indialend.attendence.operation.StaffDetailOperation;

public  class CommonActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHandler db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void loadProfileDetail(Staff staff){
        ((TextView) findViewById(R.id.profileName)).setText(staff.getName());
        ((TextView) findViewById(R.id.profileEmail)).setText(staff.getEmail());

        if(staff.getProfilePic() != null && !staff.getProfilePic().trim().isEmpty()) {

            ImageView imageView = (ImageView) findViewById(R.id.profilePic);
            new ImageLoadTask(staff.getProfilePic(), imageView).execute();
        }

        if(this instanceof ProfileActivity){
            ((ProfileActivity) this).loadStaff(staff);
        }

    }

    public void profileClick(View view){
        Intent mapActivityIntent  =  new Intent(this, QRCodeActivity.class);
        startActivity(mapActivityIntent);
        finish();
    }


    public void setupDrawer(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        db = new DatabaseHandler(this);

        new StaffDetailOperation(this, "COMMON").execute();


    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
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

        if (id == R.id.action_logout) {

            db.deleteUser();

            Intent mapActivityIntent  =  new Intent(this, LoginActivity.class);
            startActivity(mapActivityIntent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent mapActivityIntent = null ;
        if(id == R.id.home){
            mapActivityIntent  =  new Intent(this, MainActivity.class);

        } else if (id == R.id.profile) {
            mapActivityIntent  =  new Intent(this, ProfileActivity.class);

        } else if (id == R.id.leave) {
            mapActivityIntent  =  new Intent(this, LeaveActivity.class);

        } else if (id == R.id.notification) {


        }

        if(mapActivityIntent != null) {
            startActivity(mapActivityIntent);
            finish();
        }


        return true;
    }

}
