package fabricadesoftware.com.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fabricadesoftware.com.util.PreferenceHelper
import fabricadesoftware.com.R
import fabricadesoftware.com.io.ApiService
import fabricadesoftware.com.model.Prestamo


class PrestamoActivity : AppCompatActivity() {


    private val apiService: ApiService by lazy {
        ApiService.create()
    }

    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prestamo)

        val prestamos = ArrayList<Prestamo>()
        prestamos.add(
                Prestamo(1, "Pablo", "1")
        )
        prestamos.add(
                Prestamo(1, "Andres", "2")
        )
        prestamos.add(
                Prestamo(1, "PGonzalez", "3")
        )


        val rvPrestamos = findViewById<RecyclerView>(R.id.rvprestamos)

        rvPrestamos.layoutManager = LinearLayoutManager(this)
        rvPrestamos.adapter = PrestamoAdapter(prestamos)

    }


}

