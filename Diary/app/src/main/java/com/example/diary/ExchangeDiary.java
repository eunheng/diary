package com.example.diary;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ExchangeDiary extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("ExchangeDiary.realm").schemaVersion(0).build();
        Realm.setDefaultConfiguration(config);
    }
}
