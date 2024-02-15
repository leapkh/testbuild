package kh.edu.rupp.visitme.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kh.edu.rupp.visitme.R
import kh.edu.rupp.visitme.databinding.ActivityPlaceReviewBinding
import kh.edu.rupp.visitme.model.Place

class PlaceReviewActivity: Activity() {

    private lateinit var binding: ActivityPlaceReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind UI
        binding = ActivityPlaceReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val place = intent.getSerializableExtra(EXTRA_PLACE) as Place
        binding.txtTitle.text = getString(R.string.add_review_for_f, place.name)

        // Handle user events
        binding.imgBack.setOnClickListener { finish() }
        binding.btnSubmit.setOnClickListener {

        }
    }

    companion object {
        private const val EXTRA_PLACE = "place"

        @JvmStatic
        fun newIntent(context: Context, place: Place): Intent {
            val intent = Intent(context, PlaceReviewActivity::class.java)
            intent.putExtra(EXTRA_PLACE, place)
            return intent
        }
    }

}