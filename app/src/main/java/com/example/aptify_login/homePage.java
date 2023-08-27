package com.example.aptify_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;







import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class homePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    TextView t1;

    FrameLayout f1,f2,f3,f4,f5,f6;

    Button btn;

    private String selectedText = "";


    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference dbref;
    SignUpData data;
    private TextView fullnameP,emailP,ageP;
    String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);


        //toolbar
        setSupportActionBar(toolbar);

        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        t1 = findViewById(R.id.textView4);
        f1 = findViewById(R.id.framelayout);
        f2 = findViewById(R.id.framelayout_2);
        f3 = findViewById(R.id.framelayout_3);
        f4 = findViewById(R.id.framelayout_4);
        f5 = findViewById(R.id.framelayout_5);
        f6 = findViewById(R.id.framelayout_6);


        //fetch data from database
        dbref = FirebaseDatabase.getInstance().getReference("Login");
        dbref.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();

        dbref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot dbsnap = task.getResult();
                    data = dbsnap.getValue(SignUpData.class);
                    if (data != null) {
                        name = data.getFullname();
                        t1.setText(name);
                    }
                } else {
                    Toast.makeText(homePage.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });





        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedText = "Logical Round";
                Intent in = new Intent(homePage.this,Quiz_Splash.class);


                in.putExtra("text",selectedText);

                startActivity(in);
//

            }
        });

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedText = "Technical Round";
                Intent in = new Intent(homePage.this,Quiz_Splash.class);
                in.putExtra("text",selectedText);
                startActivity(in);
            }
        });



        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedText = "Verbal Round";
                Intent in = new Intent(homePage.this,Quiz_Splash.class);
                in.putExtra("text",selectedText);
                startActivity(in);
            }
        });

        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedText = "Numeric Round";
                Intent in = new Intent(homePage.this,Quiz_Splash.class);
                in.putExtra("text",selectedText);
                startActivity(in);
            }
        });

        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedText = "Critical Round";
                Intent in = new Intent(homePage.this,Quiz_Splash.class);
                in.putExtra("text",selectedText);
                startActivity(in);
            }
        });

        f6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedText = "Question";
                Intent in = new Intent(homePage.this,Quiz_Splash.class);
                in.putExtra("text",selectedText);
                startActivity(in);
            }
        });


    }



    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_menu:
                Intent i =new Intent(homePage.this,homePage.class);
                startActivity(i);
                break;
            case R.id.leader_board:
                i = new Intent(homePage.this,Activity_lederboard_loader.class);
                startActivity(i);
                break;

            case R.id.profile:
                i =new Intent(homePage.this,profile.class);
                startActivity(i);
                break;
            case R.id.score_hist:
                i =new Intent(homePage.this,scoreHistory_splash.class);
                startActivity(i);
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent in =new Intent(homePage.this,MainActivity.class);
                startActivity(in);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}