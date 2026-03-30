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
            if (msj != null && !msj.isEmpty()) {
                Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
            }
        });

        vm.getTipoCambioActual().observe(this, valor -> {
            binding.etTipoCambio.setText(String.valueOf(valor));
        });

        // Observadores de resultados (se limpian solos cuando el VM manda "")
        vm.getResultadoDolares().observe(this, res -> {
            binding.etDolares.setText(res);
        });

        vm.getResultadoEuros().observe(this, res -> {
            binding.etEuros.setText(res);
        });

        // Observador basado en el ID seleccionado (Sin booleanos externos)
        vm.getRbSeleccionadoId().observe(this, id -> {
            binding.etDolares.setEnabled(id == R.id.rbEuros);
            binding.etEuros.setEnabled(id == R.id.rbDolares);
        });

        // --- Listeners ---

        binding.rgTipoConversion.setOnCheckedChangeListener((group, checkedId) -> {
            vm.configurarCampos(checkedId);
        });

        binding.btnCambiarValor.setOnClickListener(v -> {
            vm.actualizarTipoCambio(binding.etTipoCambio.getText().toString());
        });

        binding.btnConvertir.setOnClickListener(v -> {
            vm.calcular(binding.etDolares.getText().toString(), binding.etEuros.getText().toString());
        });
    }
}
