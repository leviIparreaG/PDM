package com.levi.pdm.tarea2

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PedidoActivity : AppCompatActivity() {

    private lateinit var tvResumen: TextView
    private lateinit var etDireccion: EditText
    private lateinit var etCantidad: EditText
    private lateinit var rgPago: RadioGroup
    private lateinit var chkTerminos: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)

        tvResumen = findViewById(R.id.tvResumen)
        etDireccion = findViewById(R.id.etDireccion)
        etCantidad = findViewById(R.id.etCantidad)
        rgPago = findViewById(R.id.rgPago)
        chkTerminos = findViewById(R.id.chkTerminos)

        cargarResumenInicial()

        findViewById<Button>(R.id.btnVolver).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnConfirmar).setOnClickListener {
            confirmarPedido()
        }
    }

    private fun cargarResumenInicial() {
        val nombre = intent.getStringExtra(MainActivity.EXTRA_NOMBRE).orEmpty()
        val telefono = intent.getStringExtra(MainActivity.EXTRA_TELEFONO).orEmpty().ifBlank { "No registrado" }
        val entrega = intent.getStringExtra(MainActivity.EXTRA_ENTREGA).orEmpty()
        val extras = intent.getStringExtra(MainActivity.EXTRA_EXTRAS).orEmpty()

        tvResumen.text = getString(
            R.string.resumen_base,
            nombre,
            telefono,
            entrega,
            extras
        )
    }

    private fun confirmarPedido() {
        val direccion = etDireccion.text.toString().trim()
        val cantidad = etCantidad.text.toString().trim()

        if (direccion.isBlank()) {
            Toast.makeText(this, R.string.error_direccion, Toast.LENGTH_SHORT).show()
            return
        }

        if (cantidad.isBlank() || cantidad == "0") {
            Toast.makeText(this, R.string.error_cantidad, Toast.LENGTH_SHORT).show()
            return
        }

        if (rgPago.checkedRadioButtonId == -1) {
            Toast.makeText(this, R.string.error_pago, Toast.LENGTH_SHORT).show()
            return
        }

        if (!chkTerminos.isChecked) {
            Toast.makeText(this, R.string.error_terminos, Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, R.string.pedido_confirmado, Toast.LENGTH_LONG).show()
    }
}
