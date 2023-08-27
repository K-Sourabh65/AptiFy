package com.example.aptify_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity_Signup extends AppCompatActivity {
    public EditText email,name,age,pass,confpass;
    public Button signupbtn;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://aptify-4401d-default-rtdb.firebaseio.com/");
    String emailPattern  = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = (EditText)findViewById(R.id.regemail);
        name = (EditText)findViewById(R.id.regfullname);
        age = (EditText)findViewById(R.id.regage);
        pass = (EditText)findViewById(R.id.regpass);
        confpass = (EditText)findViewById(R.id.regconpass);
        signupbtn = (Button) findViewById(R.id.signupbtn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performAuth();
            }

            private void performAuth() {
                String email1 = email.getText().toString();
                String name1 = name.getText().toString();
                String age1 = age.getText().toString();
                String pass1 = pass.getText().toString();
                String confpass1 = confpass.getText().toString();

                if(!email1.matches(emailPattern)){
                    email.setError("Enter Valid Email");
                }else if(pass1.isEmpty() || pass1.length()<6){
                    pass.setError("Enter Valid Password");
                }else if(!pass1.equals(confpass1)){
                    confpass.setError("Passwords don't match. Please try again.");
                }else{
                    mAuth.createUserWithEmailAndPassword(email1, pass1)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        SignUpData sign_up_data1 = new SignUpData(email1, name1, pass1, age1);
                                        FirebaseDatabase.getInstance().getReference("Login")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(sign_up_data1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                            user.sendEmailVerification()
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            System.out.println("onSuccess: email verification has been sent.");
                                                                        }
                                                                    })
                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            System.out.println("onFailure: failed to send the emailVerification.");
                                                                            System.out.println("onFailure: " + e.getMessage());
                                                                        }
                                                                    });

                                                            Toast.makeText(getApplicationContext(), "Verify yourself using the link sent to your mail!", Toast.LENGTH_LONG).show();
                                                            navigate_to_login();
                                                        } else {
                                                            Toast.makeText(getApplicationContext(), "failed to sign-up try again!", Toast.LENGTH_LONG).show();
                                                        }
                                                    }

                                                    private void navigate_to_login() {
                                                        finish();
                                                        Intent in = new Intent(Activity_Signup.this,MainActivity.class);
                                                        startActivity(in);
                                                    }
                                                });
                                    } else {
                                        Toast.makeText(getApplicationContext(), "failed to sign-up \nYou already have an account!\nPlease login using the mail-id!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                    progressDialog.setMessage("Please Wait while Registering...");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                }
            }
        });
    }
}