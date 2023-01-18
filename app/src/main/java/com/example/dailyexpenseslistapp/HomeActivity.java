package com.example.dailyexpenseslistapp;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;

import Model.Data;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private FloatingActionButton fab_btn;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar= findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Daily Shopping List");

        mAuth=FirebaseAuth.getInstance();

        FirebaseUser mUser=mAuth.getCurrentUser();
        String uId=mUser.getUid();

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Expenses List").child(uId);



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

            dialog.setView(myview);

            EditText type=myview.findViewById(R.id.edt_type);
            EditText amount=myview.findViewById(R.id.edt_amount);
            EditText note=myview.findViewById(R.id.edt_note);
            Button btnSave=myview.findViewById(R.id.btn_save);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String mType=type.getText().toString().trim();
                    String mAmount=amount.getText().toString().trim();
                    String mNote=note.getText().toString().trim();

                    int ammint=Integer.parseInt(mAmount);

                    if (TextUtils.isEmpty(mType)) {
                        type.setError("Required Field..");
                        return;
                    }
                    if (TextUtils.isEmpty(mAmount)) {
                        amount.setError("Required Field..");
                        return;
                    }
                    if (TextUtils.isEmpty(mNote)) {
                        note.setError("Reuqired Field..");
                        return;
                    }

                    String id= mDatabase.getKey();

                    String date= DateFormat.getDateInstance().format(new Date());

                    Data data=new Data(mType,ammint,mNote,date,id);

                    mDatabase.child(id).setValue(data);

                    Toast.makeText(getApplicationContext(),"Data Add",Toast.LENGTH_SHORT);
                    dialog.dismiss();
                }
            });


            dialog.show();

        }

    }
