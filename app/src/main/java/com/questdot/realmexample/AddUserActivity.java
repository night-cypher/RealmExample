package com.questdot.realmexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddUserActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etFirstname;
    EditText etLastname;
    EditText etCountry;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        etFirstname = (EditText)findViewById(R.id.et_firstname);
        etLastname = (EditText)findViewById(R.id.et_lastname);
        etCountry = (EditText)findViewById(R.id.et_country);

        btnSave = (Button)findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_save:
                if (etFirstname.getText().toString().trim().equals("") || etLastname.getText().toString().trim().equals("") || etCountry.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Entry not saved, missing some fields", Toast.LENGTH_SHORT).show();
                } else {
                    RealmController.with(this).createUser(new User(etFirstname.getText().toString(), etLastname.getText().toString(), etCountry.getText().toString()));
                    finish();
                }
                break;
        }




    }
}
