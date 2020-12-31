package fabricadesoftware.com.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import fabricadesoftware.com.R
import fabricadesoftware.com.io.ApiService
import fabricadesoftware.com.io.response.SimpleResponse
import fabricadesoftware.com.util.PreferenceHelper
import fabricadesoftware.com.util.PreferenceHelper.get
import fabricadesoftware.com.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CrearPrestamoActivity : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        ApiService.create()
    }

    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_prestamo)

        val cvstep1 = findViewById<View>(R.id.cvstep1)
        val cvstep2 = findViewById<View>(R.id.cvstep2)
        val etCiudad = findViewById<EditText>(R.id.etCiudad)
        val etBloque = findViewById<EditText>(R.id.etBloque)
        val etDireccion = findViewById<EditText>(R.id.etDireccion)
        val etSalon = findViewById<EditText>(R.id.etSalon)
        val etCargo = findViewById<EditText>(R.id.etCargo)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcion)


        val btnGuardarPrestamo = findViewById<View>(R.id.btnGuardarPrestamo)
        btnGuardarPrestamo.setOnClickListener{

            if (etCiudad.text.isEmpty() || etBloque.text.isEmpty() || etDireccion.text.isEmpty()
                    || etSalon.text.isEmpty() || etCargo.text.isEmpty() || etDescripcion.text.isEmpty()) {
                Toast.makeText(this, "Por favor diligenciar todos los campos.", Toast.LENGTH_SHORT).show()
            } else {
                showPrestamoDataToConfirm()
                // continua a resumen de prestamo y confirmar envio
                cvstep1.visibility = View.GONE
                cvstep2.visibility = View.VISIBLE
            }
        }

        val btnEnviarPrestamo = findViewById<View>(R.id.btnEnviarPrestamo)
        btnEnviarPrestamo.setOnClickListener {
            performStorePrestamo()
        }

    }

    private fun performStorePrestamo() {
        val btnEnviarPrestamo = findViewById<View>(R.id.btnEnviarPrestamo)
        btnEnviarPrestamo.isClickable = false

        val tokenResult = preferences["tokenResult", ""]
        val authHeader = "Bearer $tokenResult"

        val tvCiudadPrestamo =  findViewById<TextView>(R.id.tvCiudadPrestamo)
        val ciudad = tvCiudadPrestamo.text.toString()
        val tvBloquePrestamo = findViewById<TextView>(R.id.tvBloquePrestamo)
        val bloque = tvBloquePrestamo.text.toString()
        val tvDireccionPrestamo = findViewById<TextView>(R.id.tvDireccionPrestamo)
        val direccion = tvDireccionPrestamo.text.toString()
        val tvSalonPrestamo = findViewById<TextView>(R.id.tvSalonPrestamo)
        val salon = tvSalonPrestamo.text.toString()
        val tvProgramaPrestamo = findViewById<TextView>(R.id.tvProgramaPrestamo)
        val programa = tvProgramaPrestamo.text.toString()
        val tvDescripcionPrestamo = findViewById<TextView>(R.id.tvDescripcionPrestamo)
        val descripcion = tvDescripcionPrestamo.text.toString()

        val call = apiService.storePrestamo(
                authHeader, ciudad,
                bloque, direccion, salon,
                programa, descripcion
        )
        call.enqueue(object : Callback<SimpleResponse> {
            override fun onResponse(call: Call<SimpleResponse>, response: Response<SimpleResponse>) =
                    if (response.isSuccessful) {
                        toast(getString(R.string.create_prestamo_success))
                        finish()
                    } else {
                        toast(getString(R.string.create_prestamo_error))
                        btnEnviarPrestamo.isClickable = false
                    }

            override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                toast(t.localizedMessage)
                btnEnviarPrestamo.isClickable = false
            }
        })
    }

    private fun showPrestamoDataToConfirm(){
        val etCiudad = findViewById<EditText>(R.id.etCiudad)
        val etBloque = findViewById<EditText>(R.id.etBloque)
        val etDireccion = findViewById<EditText>(R.id.etDireccion)
        val etSalon = findViewById<EditText>(R.id.etSalon)
        val etCargo = findViewById<EditText>(R.id.etCargo)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcion)

        val tvCiudadPrestamo = findViewById<TextView>(R.id.tvCiudadPrestamo)
        tvCiudadPrestamo.text = etCiudad.text.toString()
        val tvBloquePrestamo = findViewById<TextView>(R.id.tvBloquePrestamo)
        tvBloquePrestamo.text = etBloque.text.toString()
        val tvDireccionPrestamo = findViewById<TextView>(R.id.tvDireccionPrestamo)
        tvDireccionPrestamo.text = etDireccion.text.toString()
        val tvSalonPrestamo = findViewById<TextView>(R.id.tvSalonPrestamo)
        tvSalonPrestamo.text = etSalon.text.toString()
        val tvProgramaPrestamo = findViewById<TextView>(R.id.tvProgramaPrestamo)
        tvProgramaPrestamo.text = etCargo.text.toString()
        val tvDescripcionPrestamo = findViewById<TextView>(R.id.tvDescripcionPrestamo)
        tvDescripcionPrestamo.text = etDescripcion.text.toString()
    }

    override fun onBackPressed() {
        val cvstep1 = findViewById<View>(R.id.cvstep1)
        val cvstep2 = findViewById<View>(R.id.cvstep2)
        when {
            cvstep2.visibility == View.VISIBLE -> {
                cvstep2.visibility = View.GONE
                cvstep1.visibility = View.VISIBLE
            }
            cvstep1.visibility == View.VISIBLE -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.dialog_create_prestamo_exit_title))
                builder.setMessage(getString(R.string.dialog_create_prestamo_exix_message))
                builder.setPositiveButton(getString(R.string.dialog_create_prestamo_exit_positive_btn)) { _, _ ->
                    finish()
                }
                builder.setNegativeButton(getString(R.string.dialog_create_prestamo_exit_negative_btn)) { dialog, with ->
                    dialog.dismiss()
                }

                val dialog = builder.create()
                dialog.show()
            }
        }


    }
}


