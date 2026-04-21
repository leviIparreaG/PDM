package com.levi.pdm.tarea3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var etNombre: EditText
    private lateinit var etTelefono: EditText
    private lateinit var chkBebida: CheckBox
    private lateinit var chkPostre: CheckBox
    private lateinit var chkCombo: CheckBox
    private lateinit var rgEntrega: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navigationView: NavigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.nav_open, R.string.nav_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_buscar -> {
                Log.d(TAG, "Buscar pedido")
                true
            }
            R.id.menu_favoritos -> {
                Log.d(TAG, "Mis favoritos")
                true
            }
            R.id.menu_cancelar_pedido -> {
                Log.d(TAG, "Cancelar pedido")
                true
            }
            R.id.menu_historial -> {
                Log.d(TAG, "Historial de pedidos")
                true
            }
            R.id.menu_informacion -> {
                Log.d(TAG, "Informacion de la app")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_inicio -> Log.d(TAG, "Inicio")
            R.id.nav_nuevo_pedido -> Log.d(TAG, "Nuevo pedido")
            R.id.nav_mis_pedidos -> Log.d(TAG, "Mis pedidos")
            R.id.nav_favoritos -> Log.d(TAG, "Favoritos")
            R.id.nav_perfil -> Log.d(TAG, "Mi perfil")
            R.id.nav_configuracion -> Log.d(TAG, "Configuracion")
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
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
        private const val TAG = "MainActivity"
        const val EXTRA_NOMBRE = "extra_nombre"
        const val EXTRA_TELEFONO = "extra_telefono"
        const val EXTRA_ENTREGA = "extra_entrega"
        const val EXTRA_EXTRAS = "extra_extras"
    }
}
