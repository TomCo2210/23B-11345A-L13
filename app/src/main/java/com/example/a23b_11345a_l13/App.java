package com.example.a23b_11345a_l13;

import android.app.Application;

import com.example.a23b_11345a_l13.Utilities.ImageLoader;
import com.google.firebase.FirebaseApp;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        ImageLoader.initImageLoader(this);
    }
}
