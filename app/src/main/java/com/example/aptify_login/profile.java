package com.example.aptify_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends AppCompatActivity {
    private Button logoutbtn,changePass;
    private TextView fullnameP,emailP,ageP;

    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference dbref;

    SignUpData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logoutbtn = findViewById(R.id.logoutButton);
        fullnameP = findViewById(R.id.textViewName);
        ageP = findViewById(R.id.textViewAge);
        emailP = findViewById(R.id.textViewEmail);


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
                        fullnameP.setText(data.getFullname());
                        ageP.setText(data.getAge());
                        emailP.setText(data.getEmail());
                    }
                } else {
                    Toast.makeText(profile.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent in =new Intent(profile.this,MainActivity.class);
                startActivity(in);
            }
        });

    }
}