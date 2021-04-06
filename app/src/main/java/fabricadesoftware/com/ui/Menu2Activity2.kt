package fabricadesoftware.com.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.Result
import fabricadesoftware.com.R
import me.dm7.barcodescanner.zxing.ZXingScannerView

class Menu2Activity2 : AppCompatActivity(), ZXingScannerView.ResultHandler {

    /*
    private val apiService by lazy {
        ApiService.create()
    }

    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }
    */
    private var mScannerView: ZXingScannerView? = null
    private var tvResult: TextView? = null



    private lateinit var appBarConfiguration: AppBarConfiguration



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu22)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        // Poner el icono en el acction bar
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.mipmap.logo)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if (ContextCompat.checkSelfPermission(
                        this@Menu2Activity2,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this@Menu2Activity2,
                        Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    this@Menu2Activity2,
                    arrayOf(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA
                    ),
                    1000
            )
        }
/*
        val storeToken = intent.getBooleanExtra("store_token", false)
        if (storeToken)
            storeToken()

 */



        


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
    @Override
    override fun handleResult(result: Result) {
        val dato = result.text
        setContentView(R.layout.fragment_home)
        mScannerView!!.stopCamera()
        tvResult = findViewById<View>(R.id.etqr) as TextView
        (tvResult as EditText).setText(dato)

    }
/*
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

                val intent = Intent(this@Menu2Activity2, MainActivity::class.java)
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
        private const val TAG = "Menu2Activity2"
    }


 */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu2_activity2, menu)

    /*
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

     */


        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}

annotation class Override

