package fabricadesoftware.com.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.method.MovementMethod
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.iid.FirebaseInstanceId
import com.google.zxing.Result
import fabricadesoftware.com.R
import fabricadesoftware.com.io.ApiService
import fabricadesoftware.com.util.PreferenceHelper
import fabricadesoftware.com.util.PreferenceHelper.get
import fabricadesoftware.com.util.PreferenceHelper.set
import fabricadesoftware.com.util.toast
import me.dm7.barcodescanner.core.BarcodeScannerView
import me.dm7.barcodescanner.zxing.ZXingScannerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class MenuActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private val apiService by lazy {
        ApiService.create()
    }

    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }

    private var mScannerView: ZXingScannerView? = null
    private var tvResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    /*
        if (ContextCompat.checkSelfPermission(
                        this@MenuActivity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this@MenuActivity,
                        Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    this@MenuActivity,
                    arrayOf(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA
                    ),
                    1000
            )
        }

     */

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

    // Activa la camara para escaneo
    fun btnEscaner(v: View?) {

            // uno de los radio botones esta marcado
            mScannerView = ZXingScannerView(this)
            setContentView(mScannerView)
            mScannerView!!.setResultHandler(this)
            mScannerView!!.startCamera()

    }

    // Resultado del escaner y activar los demas botones
    @override
    override fun handleResult(result: Result) {
        val dato = result.text
        setContentView(R.layout.activity_menu)
        mScannerView!!.stopCamera()
        tvResult = findViewById<View>(R.id.etqr) as TextView
        (tvResult as EditText).setText(dato)

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
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "Token registrado correctamente")
                    } else {
                        Log.d(TAG, "Problema al registrar token")
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
        call.enqueue(object : Callback<Void> {
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

}

private fun TextView?.setScroller() {

}

annotation class override






