package fabricadesoftware.com.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import fabricadesoftware.com.util.PreferenceHelper
import fabricadesoftware.com.R
import fabricadesoftware.com.io.ApiService
import fabricadesoftware.com.io.response.LoginResponse
import fabricadesoftware.com.util.PreferenceHelper.get
import fabricadesoftware.com.util.PreferenceHelper.set
import fabricadesoftware.com.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private  val apiService: ApiService by lazy {
        ApiService.create()
    }

    private val snackbar by lazy {
        val mainLayout = findViewById<View>(R.id.mainLayout)
        Snackbar.make(mainLayout, R.string.press_back_again, Snackbar.LENGTH_SHORT)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = PreferenceHelper.defaultPrefs(this)
        if (preferences["tokenResult", ""].contains("."))
            gotoMenuActivity()

        val btnLogin = findViewById<View>(R.id.btnLogin)
        btnLogin.setOnClickListener{
            // Validar datos del usuario en el servidor
            performLogin()

        }

        val tvGoToRegister = findViewById<View>(R.id.tvGoToRegister)
        tvGoToRegister.setOnClickListener{
            Toast.makeText(this, getString(R.string.please_fill_your_register_data), Toast.LENGTH_SHORT).show();

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performLogin() {
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val call = apiService.postLogin(etEmail.text.toString(), etPassword.text.toString())
        call.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse == null){
                        toast(getString(R.string.error_login_response))
                        return
                    }
                    if (loginResponse.success) {
                        createSessionPreference(loginResponse.tokenResult)
                        gotoMenuActivity()
                    } else {
                        toast(getString(R.string.error_invalid_credentials))
                    }
                } else {
                    toast(getString(R.string.error_login_response))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                toast("Por favor ingrese e-mail y contraseña")
            }
        })
    }


    private fun createSessionPreference(tokenResult: String) {
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["tokenResult"] = tokenResult
    }

    private fun gotoMenuActivity() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        if (snackbar.isShown)
            super.onBackPressed()
        else
            snackbar.show()
    }


}

