package com.questdot.realmexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class UserDetailActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etFirstname;
    EditText etLastname;
    EditText etCountry;
    Button btnSave,btnDelete;

    private int id;
    private int position;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        etFirstname = (EditText)findViewById(R.id.et_firstname);
        etLastname = (EditText)findViewById(R.id.et_lastname);
        etCountry = (EditText)findViewById(R.id.et_country);
        btnSave = (Button)findViewById(R.id.btn_save);
        btnDelete = (Button)findViewById(R.id.btn_delete);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("user_id", 0);
        position = intent.getIntExtra("position", 0);

        user = RealmController.getInstance().getUser(id);
        etFirstname.setText(user.getFirst_name());
        etLastname.setText(user.getLast_name());
        etCountry.setText(user.getCountry());

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_delete:
                RealmController.getInstance().deleteUserByPosition(position);
                finish();
                break;

            case R.id.btn_save:
                if (etFirstname.getText().toString().trim().equals("") || etLastname.getText().toString().trim().equals("") || etCountry.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Entry not saved, missing some fields", Toast.LENGTH_SHORT).show();
                } else {
                    RealmController.getInstance().updateUser(new User(id, etFirstname.getText().toString(), etLastname.getText().toString(), etCountry.getText().toString()));
                    finish();
                }
                break;


        }

    }
}
