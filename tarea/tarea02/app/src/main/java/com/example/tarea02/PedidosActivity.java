package com.example.tarea02;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PedidosActivity extends AppCompatActivity {

    private CheckBox cbHamburguesa;
    private CheckBox cbPizza;
    private CheckBox cbRefresco;
    private CheckBox cbEnsalada;
    private CheckBox cbPapas;

    private EditText etCantHamburguesa;
    private EditText etCantPizza;
    private EditText etCantRefresco;
    private EditText etCantEnsalada;
    private EditText etCantPapas;

    private RadioGroup rgModalidad;
    private Spinner spPersonas;
    private EditText etComentarios;

    private Button btnConfirmar;
    private Button btnRegresar;
    private TextView tvBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        String nombre = getIntent().getStringExtra("nombre");

        tvBienvenida        = findViewById(R.id.tvBienvenida);
        cbHamburguesa       = findViewById(R.id.cbHamburguesa);
        cbPizza             = findViewById(R.id.cbPizza);
        cbRefresco          = findViewById(R.id.cbRefresco);
        cbEnsalada          = findViewById(R.id.cbEnsalada);
        cbPapas             = findViewById(R.id.cbPapas);
        etCantHamburguesa   = findViewById(R.id.etCantHamburguesa);
        etCantPizza         = findViewById(R.id.etCantPizza);
        etCantRefresco      = findViewById(R.id.etCantRefresco);
        etCantEnsalada      = findViewById(R.id.etCantEnsalada);
        etCantPapas         = findViewById(R.id.etCantPapas);
        rgModalidad         = findViewById(R.id.rgModalidad);
        spPersonas          = findViewById(R.id.spPersonas);
        etComentarios       = findViewById(R.id.etComentarios);
        btnConfirmar        = findViewById(R.id.btnConfirmar);
        btnRegresar         = findViewById(R.id.btnRegresar);

        if (nombre != null && !nombre.isEmpty()) {
            tvBienvenida.setText("Hola, " + nombre + ". Selecciona tu pedido:");
        }

        // Spinner de numero de personas
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.num_personas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPersonas.setAdapter(adapter);

        btnConfirmar.setOnClickListener(v -> {
            if (!cbHamburguesa.isChecked() && !cbPizza.isChecked()
                    && !cbRefresco.isChecked() && !cbEnsalada.isChecked()
                    && !cbPapas.isChecked()) {
                Toast.makeText(this, "Selecciona al menos un producto", Toast.LENGTH_SHORT).show();
                return;
            }
            if (rgModalidad.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Selecciona la modalidad de entrega", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Pedido confirmado! Gracias por tu compra.", Toast.LENGTH_LONG).show();
        });

        btnRegresar.setOnClickListener(v -> finish());
    }
}
