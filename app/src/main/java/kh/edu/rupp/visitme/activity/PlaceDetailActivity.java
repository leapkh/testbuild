package kh.edu.rupp.visitme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.visitme.databinding.ActivityPlaceDetailBinding;
import kh.edu.rupp.visitme.model.Place;

public class PlaceDetailActivity extends Activity {

    private static final String EXTRA_PLACE = "place";

    private ActivityPlaceDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind UI
        binding = ActivityPlaceDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Place place = (Place) getIntent().getSerializableExtra(EXTRA_PLACE);
        Picasso.get().load(place.getImageUrl()).into(binding.imgPlace);
        binding.txtPlaceName.setText(place.getName());

        // Handle user events
        binding.imgBack.setOnClickListener(v -> finish());
        binding.txtAbout.setOnClickListener(v -> {
            Toast.makeText(this, "You click About.", Toast.LENGTH_LONG).show();
        });

        binding.txtPhotos.setOnClickListener(v -> {
            Toast.makeText(this, "You click Photos.", Toast.LENGTH_LONG).show();
        });

        binding.txtReviews.setOnClickListener( v -> startReviewActivity(place));

    }

    private void startReviewActivity(Place place) {
        Intent intent = PlaceReviewActivity.newIntent(this, place);
        startActivity(intent);
    }

    public static Intent newIntent(Context context, Place place) {
        Intent intent = new Intent(context, PlaceDetailActivity.class);
        intent.putExtra(EXTRA_PLACE, place);
        return intent;
    }

}
