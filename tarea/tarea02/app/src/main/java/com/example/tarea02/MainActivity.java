package com.example.tarea02;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

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
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout,
                R.string.nav_open, R.string.nav_close
        );
        drawerLayout.addDrawerListener(toggle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toggle.syncState();

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        rgTipoVisita = findViewById(R.id.rgTipoVisita);
        cbClienteFrecuente = findViewById(R.id.cbClienteFrecuente);
        cbTerminos = findViewById(R.id.cbTerminos);
        btnVerMenu = findViewById(R.id.btnVerMenu);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        if (id == R.id.menu_buscar) {
            Log.d(TAG, "Buscar restaurante");
            return true;
        } else if (id == R.id.menu_favoritos) {
            Log.d(TAG, "Mis favoritos");
            return true;
        } else if (id == R.id.menu_cancelar_pedido) {
            Log.d(TAG, "Cancelar pedido");
            return true;
        } else if (id == R.id.menu_reenviar_pedido) {
            Log.d(TAG, "Reenviar pedido");
            return true;
        } else if (id == R.id.menu_informacion) {
            Log.d(TAG, "Informacion del restaurante");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            Log.d(TAG, "Inicio");
        } else if (id == R.id.nav_ver_menu) {
            Log.d(TAG, "Ver menu del restaurante");
        } else if (id == R.id.nav_mis_pedidos) {
            Log.d(TAG, "Mis pedidos");
        } else if (id == R.id.nav_favoritos) {
            Log.d(TAG, "Favoritos");
        } else if (id == R.id.nav_perfil) {
            Log.d(TAG, "Mi perfil");
        } else if (id == R.id.nav_configuracion) {
            Log.d(TAG, "Configuracion");
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
