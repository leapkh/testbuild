package kh.edu.rupp.visitme.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import kh.edu.rupp.visitme.databinding.ActivityInputBinding;


public class InputActivity extends AppCompatActivity {

    private final int CALCULATION_REQUEST_CODE = 1;

    private ActivityInputBinding binding;

    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult activityResult) {
            if (activityResult.getResultCode() == RESULT_OK) {
                Intent data = activityResult.getData();
                int result = data.getIntExtra("result", 0);
                binding.txtResult.setText("Result is: " + result);
            }
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCalculate.setOnClickListener(v -> calculate());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CALCULATION_REQUEST_CODE && resultCode == RESULT_OK) {
            int result = data.getIntExtra("result", 0);
            binding.txtResult.setText("Result is: " + result);
        }
    }

    private void calculate() {
        String input1 = binding.edtInput1.getText().toString();
        String input2 = binding.edtInput2.getText().toString();

        Intent intent = new Intent(this, CalculationActivity.class);

        intent.putExtra("input1", input1);
        intent.putExtra("input2", input2);

        //startActivityForResult(intent, CALCULATION_REQUEST_CODE);
        launcher.launch(intent);
    }

}
