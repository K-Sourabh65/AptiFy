package com.example.aptify_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private  TextView signup;
    public EditText email,pass;
    public Button loginbtn;
    String emailPattern  = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = (TextView) findViewById(R.id.newpage);
        email = (EditText)findViewById(R.id.logemail);
        pass = (EditText)findViewById(R.id.logpass);
        loginbtn = (Button) findViewById(R.id.signin);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Activity_Signup.class);
                startActivity(i);
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }

            private void performLogin() {
                String email1 = email.getText().toString();
                String pass1 = pass.getText().toString();

                if(!email1.matches(emailPattern)){
                    email.setError("Enter Valid Email");
                }else if(pass1.isEmpty() || pass1.length()<6){
                    pass.setError("Enter Valid Password");
                }else {
                    progressDialog.setMessage("Please Wait while Logging in...");
                    progressDialog.setTitle("Login");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                                if(user.isEmailVerified()){
                                    progressDialog.dismiss();
                                    sendUserToNextActivity();
                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                }else{
                                    user.sendEmailVerification();
                                    Toast.makeText(MainActivity.this, "Verify yourself using the link sent to your mail!\n", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        private void sendUserToNextActivity() {
                            Intent in = new Intent(MainActivity.this,homePage.class);
                            startActivity(in);
                        }
                    });
                }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            if(uid.equals("vfJHYhyUZWWumffrHZnOMkrHXDp2")) {
                Intent intent = new Intent(MainActivity.this, homePage.class);
                startActivity(intent);
                finish();
            }
            else if(mAuth.getCurrentUser().isEmailVerified()) {
                Intent intent = new Intent(MainActivity.this, homePage.class);
                startActivity(intent);
                finish();
            }
        }
    }
}