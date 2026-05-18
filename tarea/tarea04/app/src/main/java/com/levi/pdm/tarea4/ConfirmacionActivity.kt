package com.levi.pdm.tarea4

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ConfirmacionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacion)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val nombre    = intent.getStringExtra(EXTRA_NOMBRE).orEmpty()
        val telefono  = intent.getStringExtra(EXTRA_TELEFONO).orEmpty()
        val entrega   = intent.getStringExtra(EXTRA_ENTREGA).orEmpty()
        val extras    = intent.getStringExtra(EXTRA_EXTRAS).orEmpty()
        val direccion = intent.getStringExtra(EXTRA_DIRECCION).orEmpty()
        val cantidad  = intent.getStringExtra(EXTRA_CANTIDAD).orEmpty()
        val pago      = intent.getStringExtra(EXTRA_PAGO).orEmpty()

        findViewById<TextView>(R.id.tvDetalleConfirmacion).text = getString(
            R.string.resumen_confirmacion,
            nombre, telefono, entrega, extras, direccion, cantidad, pago
        )

        findViewById<Button>(R.id.btnNuevoPedido).setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
            )
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_NOMBRE    = "extra_nombre"
        const val EXTRA_TELEFONO  = "extra_telefono"
        const val EXTRA_ENTREGA   = "extra_entrega"
        const val EXTRA_EXTRAS    = "extra_extras"
        const val EXTRA_DIRECCION = "extra_direccion"
        const val EXTRA_CANTIDAD  = "extra_cantidad"
        const val EXTRA_PAGO      = "extra_pago"
    }
}
