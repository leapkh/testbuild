package kh.edu.rupp.visitme.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import kh.edu.rupp.visitme.R;
import kh.edu.rupp.visitme.databinding.ActivityMainBinding;
import kh.edu.rupp.visitme.fragment.AccountFragment;
import kh.edu.rupp.visitme.fragment.HomeFragment;
import kh.edu.rupp.visitme.fragment.MoreFragment;
import kh.edu.rupp.visitme.fragment.ProvincesFragment;
import kh.edu.rupp.visitme.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Bind UI
        showFragment(new HomeFragment());

        // Setup listener
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.mnuHome:
                    showFragment(new HomeFragment());
                    break;
                case R.id.mnuProvinces:
                    showFragment(new ProvincesFragment());
                    break;
                case R.id.mnuSearch:
                    showFragment(new SearchFragment());
                    break;
                case R.id.mnuAccount:
                    showFragment(new AccountFragment());
                    break;
                case R.id.mnuMore:
                    showFragment(new MoreFragment());
                    break;
            }

            return true;
        });
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lytFragmentContainer, fragment);
        fragmentTransaction.commit();
    }

}