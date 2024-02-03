package kh.edu.rupp.visitme.fragment;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kh.edu.rupp.visitme.adapter.ProvinceAdapter;
import kh.edu.rupp.visitme.api.ApiService;
import kh.edu.rupp.visitme.databinding.FragmentHomeBinding;
import kh.edu.rupp.visitme.databinding.FragmentProvincesBinding;
import kh.edu.rupp.visitme.model.Province;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProvincesFragment extends Fragment {

    private FragmentProvincesBinding binding;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentProvincesBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showLoading();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        apiService.loadProvinces().enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {
                hideLoading();
                if (response.isSuccessful()) {
                    displayProvinces(response.body());
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show();
                    Log.e("dse", "Load profile error: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Province>> call, Throwable t) {
                hideLoading();
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show();
                Log.e("dse", "Load profile error: " + t.getMessage());
            }
        });
    }

    private void displayProvinces(List<Province> provinces) {
        // Adapter
        ProvinceAdapter adapter = new ProvinceAdapter();
        adapter.setDataSet(provinces);

        // LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());

        // RecyclerView
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);
    }

    private void showLoading() {
        progressDialog = new ProgressDialog(requireContext());
        progressDialog.show();
    }

    private void hideLoading() {
        progressDialog.dismiss();
    }

}
