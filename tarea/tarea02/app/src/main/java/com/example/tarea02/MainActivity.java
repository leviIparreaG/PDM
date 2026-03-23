package com.example.tarea02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etCorreo;
    private EditText etTelefono;
    private RadioGroup rgTipoVisita;
    private CheckBox cbClienteFrecuente;
    private CheckBox cbTerminos;
    private Button btnVerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Pantalla 1 - Login / Bienvenida
        setContentView(R.layout.activity_main);

        // Pantalla 2 - Pedidos (comentar la linea anterior y descomentar esta para ver la segunda pantalla)
        // setContentView(R.layout.activity_pedidos);

        etNombre        = findViewById(R.id.etNombre);
        etCorreo        = findViewById(R.id.etCorreo);
        etTelefono      = findViewById(R.id.etTelefono);
        rgTipoVisita    = findViewById(R.id.rgTipoVisita);
        cbClienteFrecuente = findViewById(R.id.cbClienteFrecuente);
        cbTerminos      = findViewById(R.id.cbTerminos);
        btnVerMenu      = findViewById(R.id.btnVerMenu);

        btnVerMenu.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();

            if (nombre.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa tu nombre", Toast.LENGTH_SHORT).show();
                return;
            }
            if (rgTipoVisita.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Por favor selecciona el tipo de visita", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!cbTerminos.isChecked()) {
                Toast.makeText(this, "Debes aceptar los terminos y condiciones", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, PedidosActivity.class);
            intent.putExtra("nombre", nombre);
            startActivity(intent);
        });
    }
}
