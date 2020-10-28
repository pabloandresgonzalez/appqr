package fabricadesoftware.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class CrearPrestamoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_prestamo)

        val btnGuardarPrestamo = findViewById<View>(R.id.btnGuardarPrestamo)
        btnGuardarPrestamo.setOnClickListener{
            Toast.makeText(this, "Solicitud enviada correctamente!!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}