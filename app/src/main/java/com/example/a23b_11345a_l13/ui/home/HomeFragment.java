package com.example.a23b_11345a_l13.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a23b_11345a_l13.Adapters.MovieAdapter;
import com.example.a23b_11345a_l13.Model.Movie;
import com.example.a23b_11345a_l13.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView mainLSTMovies;
    private MovieAdapter movieAdapter;
    Observer<ArrayList<Movie>> observer = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            movieAdapter.updateMovies(movies);
        }
    };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mainLSTMovies = binding.mainLSTMovies;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mainLSTMovies.setLayoutManager(linearLayoutManager);
        movieAdapter = new MovieAdapter(getContext(),homeViewModel.getMovies().getValue());
        mainLSTMovies.setAdapter(movieAdapter);
        homeViewModel.getMovies().observe(getViewLifecycleOwner(), observer);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}