package fabricadesoftware.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import fabricadesoftware.com.PreferenceHelper.get
import fabricadesoftware.com.PreferenceHelper.set


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // shared preferences -> guardar variables y varoles datos puntuales, par clave valor(Key=>Value)
        // sqlite -> cuando quereos tener localmente info del servidor por que muy rara vez cambia
        // files -> la usamos cuando quremos guardar una imagen  un archivo

        /*
        //usando propio codigo para optener las preferencias
        val preferences = getSharedPreferences("general", MODE_PRIVATE)
        val session = preferences.getBoolean("session", false)
        */
        //usando un object preferenceHelper de un articulo sugerido
        val preferences = PreferenceHelper.defaultPrefs(this)
        if (preferences["session", false])
            gotoMenuActivity()

        val btnLogin = findViewById<View>(R.id.btnLogin)
        btnLogin.setOnClickListener{
            // Validar datos del usuario en el servidor
            createSessionPreference()
            gotoMenuActivity()
        }

        val tvGoToRegister = findViewById<View>(R.id.tvGoToRegister)
        tvGoToRegister.setOnClickListener{
            Toast.makeText(this, getString(R.string.please_fill_your_register_data), Toast.LENGTH_SHORT).show();

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createSessionPreference() {
        /*
        //usando propio codigo para optener las preferencias
        val preferences = getSharedPreferences("general", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("session", true)
        editor.apply()
         */
        //usando un object preferenceHelper de un articulo sugerido que llema al metodo set
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = true
    }

    private fun gotoMenuActivity() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

}

