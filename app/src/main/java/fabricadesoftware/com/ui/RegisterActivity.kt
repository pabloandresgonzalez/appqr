package fabricadesoftware.com.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import fabricadesoftware.com.R
import fabricadesoftware.com.io.ApiService
import fabricadesoftware.com.io.response.LoginResponse
import fabricadesoftware.com.util.PreferenceHelper
import fabricadesoftware.com.util.PreferenceHelper.set
import fabricadesoftware.com.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private val apiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        // Poner el icono en el acction bar
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.mipmap.logo)

        val tvGoToLogin = findViewById<View>(R.id.tvGoToLogin)
        tvGoToLogin.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnconfirmRegister = findViewById<Button>(R.id.btnConfirmRegister)
        btnconfirmRegister.setOnClickListener{
            performRegister()
        }
    }

    private fun performRegister(){
        val etRegisterName =  findViewById<TextView>(R.id.etRegisterName)
        val name = etRegisterName.text.toString().trim()
        val etRegisterSurName =  findViewById<TextView>(R.id.etRegisterSurName)
        val surname = etRegisterSurName.text.toString().trim()
        val etRegisterEmail =  findViewById<TextView>(R.id.etRegisterEmail)
        val email = etRegisterEmail.text.toString().trim()
        val etRegisterCelular =  findViewById<TextView>(R.id.etRegisterCelular)
        val celular = etRegisterCelular.text.toString().trim()
        val etRegisterPassword =  findViewById<TextView>(R.id.etRegisterPassword)
        val password = etRegisterPassword.text.toString()
        val etRegisterPasswordConfirmation =  findViewById<TextView>(R.id.etRegisterPasswordConfirmation)
        val passwordConfirmation = etRegisterPasswordConfirmation.text.toString()

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            toast(getString(R.string.error_register_empty_fiels))
            return
        }
        /*
        if (password != passwordConfirmation){
            toast(getString(R.string.error_register_password_do_not_match))
        }
        */
        val call = apiService.postRegiter(name, surname, email, celular, password, passwordConfirmation)
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
                        toast(getString(R.string.welcome_name, loginResponse.user.name))
                        gotoMenuActivity()
                    } else {
                        toast(getString(R.string.error_invalid_credentials))
                    }
                }else {
                    toast(getString(R.string.error_register_validation))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                toast(t.localizedMessage)
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
}