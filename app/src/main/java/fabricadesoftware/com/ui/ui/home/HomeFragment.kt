package fabricadesoftware.com.ui.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.Result
import fabricadesoftware.com.R
import fabricadesoftware.com.io.ApiService
import fabricadesoftware.com.ui.CrearPrestamoActivity
import fabricadesoftware.com.ui.MainActivity
import fabricadesoftware.com.ui.PrestamoActivity
import fabricadesoftware.com.util.PreferenceHelper
import fabricadesoftware.com.util.PreferenceHelper.get
import fabricadesoftware.com.util.PreferenceHelper.set
import me.dm7.barcodescanner.zxing.ZXingScannerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private val apiService by lazy {
        ApiService.create()
    }

    private val preferences by lazy {
        context?.let { PreferenceHelper.defaultPrefs(it) }
    }

    private var mScannerView: ZXingScannerView? = null
    private var tvResult: TextView? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        val btnCrearPrestamo = root.findViewById<Button>(R.id.btnCrearPrestamo)
        btnCrearPrestamo.setOnClickListener{

            val intent = Intent(activity, CrearPrestamoActivity::class.java)
            startActivity(intent)
        }

        val btnCrearPrestamoBien = root.findViewById<Button>(R.id.btnCrearPrestamoBien)
        btnCrearPrestamoBien.setOnClickListener{

            val intent = Intent(activity, CrearPrestamoActivity::class.java)
            startActivity(intent)
        }

        val btnCrearPrestamoParqueadero = root.findViewById<Button>(R.id.btnCrearPrestamoParqueadero)
        btnCrearPrestamoParqueadero.setOnClickListener{

            val intent = Intent(activity, CrearPrestamoActivity::class.java)
            startActivity(intent)
        }

        val btnMisPrestamos = root.findViewById<Button>(R.id.btnMisPrestamos)
        btnMisPrestamos.setOnClickListener{

            val intent = Intent(activity, PrestamoActivity::class.java)
            startActivity(intent)
        }

        val btnlogout = root.findViewById<Button>(R.id.btnlogout)
        btnlogout.setOnClickListener{
            performLogout()
            clearSessionPreference()
        }

        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        return root



    }

    private fun performLogout() {
        val tokenResult = preferences?.get("tokenResult", "")
        val call = apiService.postLogout("Bearer $tokenResult")
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                clearSessionPreference()

                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()

            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

                Toast.makeText(context, R.string.error_login_response, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun clearSessionPreference() {
        preferences?.set("tokenResult", "")
    }

    companion object {
        private const val TAG = "Menu2Activity2"
    }


}






