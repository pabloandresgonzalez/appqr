package fabricadesoftware.com.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.iid.FirebaseInstanceId
import fabricadesoftware.com.util.PreferenceHelper
import fabricadesoftware.com.R
import fabricadesoftware.com.io.ApiService
import fabricadesoftware.com.util.PreferenceHelper.set
import fabricadesoftware.com.util.PreferenceHelper.get
import fabricadesoftware.com.util.toast
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response

class MenuActivity : AppCompatActivity() {

    private val apiService by lazy {
        ApiService.create()
    }

    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Poner el icono en el acction bar
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.mipmap.logo)

        val storeToken = intent.getBooleanExtra("store_token", false)
        if (storeToken)
            storeToken()

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
            performLogout()
            clearSessionPreference()
        }

    }

    private fun storeToken() {
        val tokenResult = preferences["tokenResult", ""]
        val authHeader = "Bearer $tokenResult"

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this) { instanceIdResult ->
            val deviceToken = instanceIdResult.token
            //Log.d("FCMService", deviceToken)

            val call = apiService.postToken(authHeader, deviceToken)
            call.enqueue(object: Callback<Void>  {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Log.d(TAG,"Token registrado correctamente")
                    }else {
                        Log.d(TAG,"Problema al registrar token")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    toast(t.localizedMessage)
                }
            })
        }
    }

    private fun performLogout() {
        val tokenResult = preferences["tokenResult", ""]
        val call = apiService.postLogout("Bearer $tokenResult")
        call.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                clearSessionPreference()
                
                val intent = Intent(this@MenuActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                toast(getString(R.string.error_login_response))
            }

        })
    }

    private fun clearSessionPreference() {
        preferences["tokenResult"] = ""
    }

    companion object {
        private const val TAG = "MenuActivity"
    }

    fun btnEscaner(view: View) {}
}