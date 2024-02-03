package kh.edu.rupp.visitme.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import kh.edu.rupp.visitme.databinding.ActivityPlaceReviewBinding;

public class PlaceReviewActivity extends Activity {

    private ActivityPlaceReviewBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPlaceReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
