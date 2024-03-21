package kh.edu.rupp.visitme.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kh.edu.rupp.visitme.BuildConfig;
import kh.edu.rupp.visitme.databinding.FragmentAccountBinding;
import kh.edu.rupp.visitme.databinding.FragmentMoreBinding;

public class MoreFragment extends Fragment {

    private FragmentMoreBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentMoreBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(!BuildConfig.isPro) {
            hideLanguageMenu();
        }
    }

    private void hideLanguageMenu() {
        binding.lytLanguage.setVisibility(View.GONE);
    }

}
