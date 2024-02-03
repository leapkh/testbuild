package kh.edu.rupp.visitme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import kh.edu.rupp.visitme.databinding.ActivityPlaceDetailBinding;

public class PlaceDetailActivity extends Activity {

    private ActivityPlaceDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind UI
        binding = ActivityPlaceDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Handle user events
        binding.txtAbout.setOnClickListener(v -> {
            Toast.makeText(this, "You click About.", Toast.LENGTH_LONG).show();
        });

        binding.txtPhotos.setOnClickListener(v -> {
            Toast.makeText(this, "You click Photos.", Toast.LENGTH_LONG).show();
        });

        binding.txtReviews.setOnClickListener( v -> startReviewActivity());

    }

    private void startReviewActivity() {
        Intent intent = new Intent(this, PlaceReviewActivity.class);
        startActivity(intent);
    }

}
