package com.s23.practico1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.s23.practico1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        vm = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Observadores

        vm.getResultado().observe(this, res -> {
            if(res.contains("EUR")) {
                binding.etEuros.setText(res);
            } else if (res.contains("USD")) {
                binding.etDolares.setText(res);
            }
        });

        vm.getDolaresHabilitado().observe(this, habilitado -> binding.etDolares.setEnabled(habilitado));

        vm.getEurosHabilitado().observe(this, habilitado -> binding.etEuros.setEnabled(habilitado));

        // Listeners
        binding.rgTipoConversion.setOnCheckedChangeListener((group, checkedId) -> vm.configurarCampos(checkedId));

        binding.btnConvertir.setOnClickListener(v -> {
            String dolares = binding.etDolares.getText().toString();
            String euros = binding.etEuros.getText().toString();
            vm.calcularVersionDos(dolares, euros);
        });
    }
}
