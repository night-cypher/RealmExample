package com.questdot.realmexample;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import io.realm.Realm;
import io.realm.RealmResults;


public class RealmController {
    private static RealmController instance =new RealmController();
    private final Realm realm;

    public RealmController() {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.setAutoRefresh(true);
    }

    //clear all objects from User.class
    public void clearAll() {

        realm.beginTransaction();
        realm.delete(User.class);
        realm.commitTransaction();
    }

    //find all objects in the User.class
    public RealmResults<User> getUsers() {

        return realm.where(User.class).findAll();
    }

    //query a single item with the given id
    public User getUser(int id) {

        return realm.where(User.class).equalTo("user_id", id).findFirst();
    }

    //check if User.class is empty
    public boolean hasUser() {
        return !realm.isEmpty();
    }

    //query example
    public RealmResults<User> queriedUser() {

        return realm.where(User.class)
                .contains("first_name", "A")
                .or()
                .contains("country", "Indonesia")
                .findAll();

    }

    public void createUser(User userReq){
        User user = new User();
        user.setUser_id((int) (System.currentTimeMillis()));
        user.setFirst_name(userReq.getFirst_name());
        user.setLast_name(userReq.getLast_name());
        user.setCountry(userReq.getCountry());

        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();
    }

    public void updateUser(User userReq){
        User user = getUser(userReq.getUser_id());
        realm.beginTransaction();
        user.setFirst_name(userReq.getFirst_name());
        user.setLast_name(userReq.getLast_name());
        user.setCountry(userReq.getCountry());

        realm.commitTransaction();
    }

    public void deleteUserByPosition(int position){
        RealmResults<User> results = realm.where(User.class).findAll();

        // All changes to data must happen in a transaction
        realm.beginTransaction();

        // remove single match
        results.deleteFromRealm(position);
        realm.commitTransaction();
    }
}
