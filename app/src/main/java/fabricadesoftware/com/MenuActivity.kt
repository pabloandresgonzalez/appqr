package fabricadesoftware.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import fabricadesoftware.com.PreferenceHelper.set

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnCrearPrestamo = findViewById<View>(R.id.btnCrearPrestamo)
        btnCrearPrestamo.setOnClickListener{

            val intent = Intent(this, CrearPrestamoActivity::class.java)
            startActivity(intent)
        }

        val btnCrearPrestamoBien = findViewById<View>(R.id.btnCrearPrestamoBien)
        btnCrearPrestamoBien.setOnClickListener{

            val intent = Intent(this, CrearPrestamoActivity::class.java)
            startActivity(intent)
        }

        val btnCrearPrestamoParqueadero = findViewById<View>(R.id.btnCrearPrestamoParqueadero)
        btnCrearPrestamoParqueadero.setOnClickListener{

            val intent = Intent(this, CrearPrestamoActivity::class.java)
            startActivity(intent)
        }

        val btnMisPrestamos = findViewById<View>(R.id.btnMisPrestamos)
        btnMisPrestamos.setOnClickListener{

            val intent = Intent(this, PrestamoActivity::class.java)
            startActivity(intent)
        }

        val btnlogout = findViewById<View>(R.id.btnlogout)
        btnlogout.setOnClickListener{

            clearSessionPreference()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun clearSessionPreference() {
        /*
        val preferences = getSharedPreferences("general", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("session", false)
        editor.apply()
         */
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = false
    }
}