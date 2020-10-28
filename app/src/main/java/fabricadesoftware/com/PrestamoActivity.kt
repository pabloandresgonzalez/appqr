package fabricadesoftware.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fabricadesoftware.com.model.Prestamo

class PrestamoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prestamo)

        val prestamos = ArrayList<Prestamo>()
        prestamos.add(Prestamo(id = 1, name_user = "A", bloque = "25"))
        prestamos.add(Prestamo(id = 2, name_user = "B", bloque = "26"))
        prestamos.add(Prestamo(id = 3, name_user = "C", bloque = "27"))

        val rvPrestamos = findViewById<RecyclerView>(R.id.rvprestamos)

        rvPrestamos.layoutManager = LinearLayoutManager(this)
        rvPrestamos.adapter = PrestamoAdapter(prestamos)

    }
}