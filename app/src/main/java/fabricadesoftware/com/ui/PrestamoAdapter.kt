package fabricadesoftware.com.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fabricadesoftware.com.R
import fabricadesoftware.com.model.Prestamo


class PrestamoAdapter
    : RecyclerView.Adapter<PrestamoAdapter.ViewHolder>(){

    var prestamos = ArrayList<Prestamo>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvprestamoid: TextView = itemView.findViewById(R.id.tvprestamoid)
        val tvusuario: TextView = itemView.findViewById(R.id.tvusuario)
        val tvbloque: TextView = itemView.findViewById(R.id.tvbloque)

        fun bind(prestamo: Prestamo){
            tvprestamoid.text = itemView.context.getString(R.string.item_prestamo_id, prestamo.id)
            tvusuario.text = itemView.context.getString(R.string.item_prestamo_usuario, prestamo.nameUser)
            tvbloque.text = itemView.context.getString(R.string.item_prestamo_bloque, prestamo.bloque)
        }
    }

    // Inflate XLM items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_prestamo, parent, false)
        )
    }

    // Binds date
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prestamo = prestamos[position]

        holder.bind(prestamo)


    }

    // Return number of elements
    override fun getItemCount() = prestamos.size // function expresion
}



