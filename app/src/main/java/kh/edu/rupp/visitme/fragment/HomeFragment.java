package kh.edu.rupp.visitme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kh.edu.rupp.visitme.activity.PlaceDetailActivity;
import kh.edu.rupp.visitme.adapter.PlaceAdapter;
import kh.edu.rupp.visitme.adapter.RecyclerViewItemListener;
import kh.edu.rupp.visitme.api.ApiService;
import kh.edu.rupp.visitme.databinding.FragmentHomeBinding;
import kh.edu.rupp.visitme.model.Place;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load near me places
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.loadNearMePlaces().enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                if(response.isSuccessful()) {
                    Log.d("home-fragment", "Load near me places success.");
                    // Display places
                    displayNearMePlaces(response.body());
                } else {
                    Toast.makeText(requireContext(), "Failed to load data from server.", Toast.LENGTH_LONG).show();
                    Log.e("home-fragment", "Load near me place error. " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                Toast.makeText(requireContext(), "Failed to load data from server.", Toast.LENGTH_LONG).show();
                Log.e("home-fragment", "Load near me place error. " + t.getMessage());
            }
        });

        // Load near me places
        apiService.loadTopPlaces().enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                if(response.isSuccessful()) {
                    Log.d("home-fragment", "Load near me places success.");
                    // Display places
                    displayTopPlaces(response.body());
                } else {
                    Toast.makeText(requireContext(), "Failed to load data from server.", Toast.LENGTH_LONG).show();
                    Log.e("home-fragment", "Load near me place error. " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                Toast.makeText(requireContext(), "Failed to load data from server.", Toast.LENGTH_LONG).show();
                Log.e("home-fragment", "Load near me place error. " + t.getMessage());
            }
        });
    }

    private void displayNearMePlaces(List<Place> places) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        PlaceAdapter adapter = new PlaceAdapter(places);
        adapter.setListener(index -> {
            Place place = adapter.getItem(index);
            startPlaceDetailActivity(place);
        });
        binding.rclNearMe.setLayoutManager(layoutManager);
        binding.rclNearMe.setAdapter(adapter);

    }

    private void displayTopPlaces(List<Place> places) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        PlaceAdapter adapter = new PlaceAdapter(places);
        adapter.setListener(index -> {
            Place place = adapter.getItem(index);
            startPlaceDetailActivity(place);
        });
        binding.rclTopPlaces.setLayoutManager(layoutManager);
        binding.rclTopPlaces.setAdapter(adapter);

    }

    private void startPlaceDetailActivity(Place place) {
        Intent intent = PlaceDetailActivity.newIntent(requireContext(), place);
        startActivity(intent);
    }

}
