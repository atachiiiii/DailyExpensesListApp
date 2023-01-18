package com.example.dailyexpenseslistapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private FloatingActionButton fab_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar= findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Daily Shopping List");

        fab_btn=findViewById(R.id.id_fab);

        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                customDialog();
            }
        });
}


        private void customDialog(){

            AlertDialog.Builder mydialog=new AlertDialog.Builder(HomeActivity.this);

            LayoutInflater inflater=LayoutInflater.from(HomeActivity.this);
            View myview=inflater.inflate(R.layout.input_data, null);

            AlertDialog dialog= mydialog.create();

            mydialog.setView(myview);

            dialog.show();

        }

    }
