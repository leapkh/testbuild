package kh.edu.rupp.visitme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import kh.edu.rupp.visitme.databinding.ActivityCalculationBinding;

public class CalculationActivity extends Activity {

    private ActivityCalculationBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCalculationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String input1 = intent.getStringExtra("input1");
        String input2 = intent.getStringExtra("input2");

        int result = Integer.parseInt(input1) + Integer.parseInt(input2);
        binding.txtResult.setText("Result is " + result);

        binding.btnSendResult.setOnClickListener(v -> sendResultBack(result));

    }

    private void sendResultBack(int result) {

        Intent data = new Intent();
        data.putExtra("result", result);
        setResult(RESULT_OK, data);

        finish();

    }

}
