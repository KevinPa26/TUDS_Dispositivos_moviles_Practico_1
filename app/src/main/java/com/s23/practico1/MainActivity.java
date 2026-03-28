package com.s23.practico1;

import android.os.Bundle;
import android.view.View;
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
        vm.getResultado().observe(this, res -> binding.etTotal.setText(res));

        vm.getDolaresHabilitado().observe(this, habilitado -> {
            binding.etDolar.setEnabled(habilitado);
            if (!habilitado) binding.etDolar.setText("");
        });

        vm.getEurosHabilitado().observe(this, habilitado -> {
            binding.etEuro.setEnabled(habilitado);
            if (!habilitado) binding.etEuro.setText("");
        });

        // Listeners
        binding.rGroup.setOnCheckedChangeListener((group, checkedId) -> {
            vm.alCambiarTipoConversion(checkedId);
        });

        binding.btnConvertir.setOnClickListener(v -> {
            String dolares = binding.etDolar.getText().toString();
            String euros = binding.etEuro.getText().toString();
            vm.convertir(dolares, euros);
        });
    }
}
