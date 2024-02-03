package kh.edu.rupp.visitme.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.visitme.api.ApiService;
import kh.edu.rupp.visitme.databinding.FragmentAccountBinding;
import kh.edu.rupp.visitme.databinding.FragmentSearchBinding;
import kh.edu.rupp.visitme.model.Profile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showLoading();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.loadProfile().enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                hideLoading();
                if(response.isSuccessful()) {
                    displayProfile(response.body());
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show();
                    Log.e("dse", "Load profile error: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                hideLoading();
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show();
                Log.e("dse", "Load profile error: " + t.getMessage());
            }
        });
    }

    private void displayProfile(Profile profile) {
        Picasso.get().load(profile.getProfileImage()).into(binding.imgProfile);
        binding.txtName.setText(profile.getFullName());
    }

    private void showLoading() {
        progressDialog = new ProgressDialog(requireContext());
        progressDialog.show();
    }

    private void hideLoading() {
        progressDialog.dismiss();
    }

}
