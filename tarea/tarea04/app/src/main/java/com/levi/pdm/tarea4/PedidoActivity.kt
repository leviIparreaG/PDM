package com.levi.pdm.tarea4

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class PedidoActivity : AppCompatActivity() {

    private lateinit var tvResumen: TextView
    private lateinit var etDireccion: EditText
    private lateinit var etCantidad: EditText
    private lateinit var rgPago: RadioGroup
    private lateinit var chkTerminos: CheckBox

    private var nombre   = ""
    private var telefono = ""
    private var entrega  = ""
    private var extras   = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvResumen    = findViewById(R.id.tvResumen)
        etDireccion  = findViewById(R.id.etDireccion)
        etCantidad   = findViewById(R.id.etCantidad)
        rgPago       = findViewById(R.id.rgPago)
        chkTerminos  = findViewById(R.id.chkTerminos)

        cargarResumenInicial()

        findViewById<Button>(R.id.btnVolver).setOnClickListener { finish() }
        findViewById<Button>(R.id.btnConfirmar).setOnClickListener { confirmarPedido() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun cargarResumenInicial() {
        nombre   = intent.getStringExtra(MainActivity.EXTRA_NOMBRE).orEmpty()
        telefono = intent.getStringExtra(MainActivity.EXTRA_TELEFONO).orEmpty().ifBlank { getString(R.string.no_registrado) }
        entrega  = intent.getStringExtra(MainActivity.EXTRA_ENTREGA).orEmpty()
        extras   = intent.getStringExtra(MainActivity.EXTRA_EXTRAS).orEmpty()

        tvResumen.text = getString(R.string.resumen_base, nombre, telefono, entrega, extras)
    }

    private fun confirmarPedido() {
        val direccion = etDireccion.text.toString().trim()
        val cantidad  = etCantidad.text.toString().trim()

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

        val pago = when (rgPago.checkedRadioButtonId) {
            R.id.rbEfectivo      -> getString(R.string.pago_efectivo)
            R.id.rbTarjeta       -> getString(R.string.pago_tarjeta)
            R.id.rbTransferencia -> getString(R.string.pago_transferencia)
            else                 -> ""
        }

        startActivity(
            Intent(this, ConfirmacionActivity::class.java).apply {
                putExtra(ConfirmacionActivity.EXTRA_NOMBRE,    nombre)
                putExtra(ConfirmacionActivity.EXTRA_TELEFONO,  telefono)
                putExtra(ConfirmacionActivity.EXTRA_ENTREGA,   entrega)
                putExtra(ConfirmacionActivity.EXTRA_EXTRAS,    extras)
                putExtra(ConfirmacionActivity.EXTRA_DIRECCION, direccion)
                putExtra(ConfirmacionActivity.EXTRA_CANTIDAD,  cantidad)
                putExtra(ConfirmacionActivity.EXTRA_PAGO,      pago)
            }
        )
    }
}
