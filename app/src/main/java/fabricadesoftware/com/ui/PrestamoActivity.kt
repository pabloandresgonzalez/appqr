package fabricadesoftware.com.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fabricadesoftware.com.R
import fabricadesoftware.com.io.ApiService
import fabricadesoftware.com.model.Prestamo
import fabricadesoftware.com.util.PreferenceHelper
import fabricadesoftware.com.util.PreferenceHelper.get
import fabricadesoftware.com.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PrestamoActivity : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        ApiService.create()
    }

    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }

    private val prestamoAdapter = PrestamoAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prestamo)

        loadPrestamos()

        val rvPrestamos = findViewById<RecyclerView>(R.id.rvprestamos)

        rvPrestamos.layoutManager = LinearLayoutManager(this)
        rvPrestamos.adapter = prestamoAdapter

    }


    private fun loadPrestamos() {

        val tokenResult = preferences["tokenResult", ""]
        val call = apiService.getPrestamos("Bearer $tokenResult")
        call.enqueue(object : Callback<ArrayList<Prestamo>> {
            override fun onResponse(call: Call<ArrayList<Prestamo>>, response: Response<ArrayList<Prestamo>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        prestamoAdapter.prestamos = it
                        prestamoAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Prestamo>>, t: Throwable) {
                toast(t.localizedMessage)
            }

        })

    }


}

