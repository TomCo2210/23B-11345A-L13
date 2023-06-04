package com.example.a23b_11345a_l13.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.a23b_11345a_l13.Model.Movie;
import com.example.a23b_11345a_l13.Utilities.Constants;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Movie>> mMovies;

    public HomeViewModel() {
        mMovies = new MutableLiveData<>();
        mMovies.setValue(getMoviesFromFirebase());
    }

    private ArrayList<Movie> getMoviesFromFirebase() {
        ArrayList<Movie> movies = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(Constants.DBKeys.MOVIES);

        databaseReference.addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Movie movie = snapshot.getValue(Movie.class);
                        movies.add(movie);
                        mMovies.setValue(movies);

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Movie movie = snapshot.getValue(Movie.class);
                        movies.add(movie);
                        mMovies.setValue(movies);
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
        return movies;
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return mMovies;
    }
}