package com.example.aptify_login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class que extends AppCompatActivity {

    ArrayList<Model> listOfQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que);

        listOfQ = new ArrayList<>();
        listOfQ.add(new Model("sai","s","a","i","r","s"));
        listOfQ.add(new Model("raj","r","a","j","t","r"));
        listOfQ.add(new Model("raut","r","a","u","t","r"));

        //Log.d("ss", String.valueOf(listOfQ.size()));
    }
}