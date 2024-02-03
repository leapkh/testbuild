package kh.edu.rupp.visitme.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kh.edu.rupp.visitme.databinding.FragmentAccountBinding;
import kh.edu.rupp.visitme.databinding.FragmentMoreBinding;

public class MoreFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentMoreBinding binding = FragmentMoreBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

}
