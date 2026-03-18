package com.levi.pdm.tarea2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etTelefono: EditText
    private lateinit var chkBebida: CheckBox
    private lateinit var chkPostre: CheckBox
    private lateinit var chkCombo: CheckBox
    private lateinit var rgEntrega: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etTelefono = findViewById(R.id.etTelefono)
        chkBebida = findViewById(R.id.chkBebida)
        chkPostre = findViewById(R.id.chkPostre)
        chkCombo = findViewById(R.id.chkCombo)
        rgEntrega = findViewById(R.id.rgEntrega)

        findViewById<Button>(R.id.btnContinuar).setOnClickListener {
            abrirPantallaPedido()
        }

        findViewById<Button>(R.id.btnLimpiar).setOnClickListener {
            limpiarFormulario()
        }
    }

    private fun abrirPantallaPedido() {
        val nombre = etNombre.text.toString().trim()
        if (nombre.isBlank()) {
            Toast.makeText(this, R.string.error_nombre, Toast.LENGTH_SHORT).show()
            return
        }

        val entrega = when (rgEntrega.checkedRadioButtonId) {
            R.id.rbDomicilio -> getString(R.string.entrega_domicilio)
            R.id.rbParaLlevar -> getString(R.string.entrega_llevar)
            else -> getString(R.string.entrega_local)
        }

        val extras = buildList {
            if (chkBebida.isChecked) add(getString(R.string.extra_bebida))
            if (chkPostre.isChecked) add(getString(R.string.extra_postre))
            if (chkCombo.isChecked) add(getString(R.string.extra_combo))
        }.joinToString(separator = ", ").ifBlank { getString(R.string.extra_sin) }

        val intent = Intent(this, PedidoActivity::class.java).apply {
            putExtra(EXTRA_NOMBRE, nombre)
            putExtra(EXTRA_TELEFONO, etTelefono.text.toString().trim())
            putExtra(EXTRA_ENTREGA, entrega)
            putExtra(EXTRA_EXTRAS, extras)
        }
        startActivity(intent)
    }

    private fun limpiarFormulario() {
        etNombre.text?.clear()
        etTelefono.text?.clear()
        chkBebida.isChecked = false
        chkPostre.isChecked = false
        chkCombo.isChecked = false
        rgEntrega.check(R.id.rbLocal)
        etNombre.requestFocus()
    }

    companion object {
        const val EXTRA_NOMBRE = "extra_nombre"
        const val EXTRA_TELEFONO = "extra_telefono"
        const val EXTRA_ENTREGA = "extra_entrega"
        const val EXTRA_EXTRAS = "extra_extras"
    }
}
