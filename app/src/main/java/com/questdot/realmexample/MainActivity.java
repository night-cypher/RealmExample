package com.questdot.realmexample;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    RecyclerView recyclerView;
    Button btnAdd;
    private Realm realm;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        btnAdd = (Button)findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(this);

        //get realm instance
        this.realm = RealmController.with(this).getRealm();


    }

    private void setRealmData() {
//        List<User> users = new ArrayList<>();
//
//        User user = new User();
//        user.setUser_id((int) (1 + System.currentTimeMillis()));
//        user.setFirst_name("John");
//        user.setLast_name("Doe");
//        user.setCountry("Indonesia");
//        users.add(user);
//
//        user = new User();
//        user.setUser_id((int) (2 + System.currentTimeMillis()));
//        user.setFirst_name("Ashtony");
//        user.setLast_name("Doe");
//        user.setCountry("Alaska");
//        users.add(user);
//
//        user = new User();
//        user.setUser_id((int) (3 + System.currentTimeMillis()));
//        user.setFirst_name("Queen");
//        user.setLast_name("Doe");
//        user.setCountry("Caribian");
//        users.add(user);
//
//        for (User b : users) {
//            // Persist your data easily
//            realm.beginTransaction();
//            realm.copyToRealm(b);
//            realm.commitTransaction();
//        }

//        Toast.makeText(this, RealmController.with(this).getUsers().size() + "", Toast.LENGTH_SHORT).show();

        // refresh the realm instance
        RealmController.with(this).refresh();

        adapter = new UserAdapter(RealmController.with(this).getUsers(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();

        setRealmData();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_add:
                Intent intent = new Intent(this, AddUserActivity.class);
                startActivity(intent);
        }
    }
}
