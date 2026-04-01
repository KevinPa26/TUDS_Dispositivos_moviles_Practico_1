package com.s23.practico1;

import android.os.Bundle;
import android.widget.Toast;
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

        // --- Observadores ---

        vm.getMensajeToast().observe(this, msj -> {
            Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
        });

        vm.getConversorModelo().observe(this, conversorModelo -> {
            //input Dolares
            binding.etDolares.setText(conversorModelo.getResultadoDolares());
            binding.etDolares.setEnabled(conversorModelo.isInputDolares());
            //input Euros
            binding.etEuros.setText(conversorModelo.getResultadoEuros());
            binding.etEuros.setEnabled(conversorModelo.isInputEuros());

            //radio buttons
            binding.rbDolares.setChecked(conversorModelo.isRbDolares());
            binding.rbEuros.setChecked(conversorModelo.isRbEuros());

            //tipo de cambio
            binding.etTipoCambio.setText(String.valueOf(conversorModelo.getTipoDeCambio()));
        });

        // --- Listeners ---

        binding.rbDolares.setOnClickListener(v -> {
            vm.dolaresChecked();
        });

        binding.rbEuros.setOnClickListener(v -> {
            vm.eurosChecked();
        });

        binding.btnCambiarValor.setOnClickListener(v -> {
            vm.actualizarTipoCambio(binding.etTipoCambio.getText().toString());
        });

        binding.btnConvertir.setOnClickListener(v -> {
            vm.calcular(binding.etDolares.getText().toString(), binding.etEuros.getText().toString());
        });
    }
}
