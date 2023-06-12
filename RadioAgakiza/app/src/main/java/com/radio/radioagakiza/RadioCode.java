package com.radio.radioagakiza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class RadioCode extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_code);

        replaceFragment(new Ishikiro());

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nview=findViewById(R.id.nav);
        nview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId(); // get selected menu item's id
// check selected menu item's id and replace a Fragment Accordingly
                if (itemId == R.id.turi) {
                    replaceFragment(new turi());
                    if(drawerLayout.isDrawerOpen(GravityCompat.START)){

                        drawerLayout.closeDrawer(GravityCompat.START);
                    }

                } else if (itemId == R.id.sangira) {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "Urashobora kumviriza Radio Agakiza kuri internet ukoresheje application yitwa Radio Agakiza usanga kuri google play.Radio Agakiza kw'isoko y'ukuri.";
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Gusangira");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Sangira ukoresheje"));
                } else if (itemId == R.id.kwugara) {
                    Toast.makeText(getApplicationContext(),"Mwakoze kwutwumviriza!!Kaze kandi.", Toast.LENGTH_LONG).show();
                    finishAndRemoveTask();
                }
                else{
                    if(drawerLayout.isDrawerOpen(GravityCompat.START)){

                        drawerLayout.closeDrawer(GravityCompat.START);
                    }
                    replaceFragment(new Ishikiro());

                }

                return true;
            }
        });
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Kwugara")
                .setMessage("Muvyukuri wipfuza kugara iradiyo yawe?")
                .setNegativeButton(R.string.oya, null)
                .setPositiveButton(R.string.ego, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        RadioCode.super.onBackPressed();
                    }
                }).create().show();
    }
}