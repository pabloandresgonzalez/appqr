package fabricadesoftware.com.ui

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
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

        val tvDetalle: TextView = itemView.findViewById(R.id.tvDetalle)
        val tvdireccion: TextView = itemView.findViewById(R.id.tvdireccion)
        val tvprograma: TextView = itemView.findViewById(R.id.tvprograma)
        val tvestado: TextView = itemView.findViewById(R.id.tvestado)
        val tvdescripcion: TextView = itemView.findViewById(R.id.tvdescripcion)
        val tvuserId: TextView = itemView.findViewById(R.id.tvuserId)
         val tvsurnameUser: TextView = itemView.findViewById(R.id.tvsurnameUser)
        val tvcedulaUser: TextView = itemView.findViewById(R.id.tvcedulaUser)
        val tvciudad: TextView = itemView.findViewById(R.id.tvciudad)
        val tvactivo: TextView = itemView.findViewById(R.id.tvactivo)
        val tvserial: TextView = itemView.findViewById(R.id.tvserial)
        val tvplaca: TextView = itemView.findViewById(R.id.tvplaca)
        val tvcelular: TextView = itemView.findViewById(R.id.tvcelular)
        val tvreferencia: TextView = itemView.findViewById(R.id.tvreferencia)
        val tvcantidad: TextView = itemView.findViewById(R.id.tvcantidad)
        val tvsalidaPor: TextView = itemView.findViewById(R.id.tvsalidaPor)
        val tveditadoPor: TextView = itemView.findViewById(R.id.tveditadoPor)
        val tvCreado: TextView = itemView.findViewById(R.id.tvCreado)
        val tvactualizado: TextView = itemView.findViewById(R.id.tvactualizado)

        val ibExpand: ImageButton = itemView.findViewById(R.id.ibExpand)
        val linearLayoutDetails: View = itemView.findViewById(R.id.linearLayoutDetails)



        fun bind(prestamo: Prestamo){
            tvprestamoid.text = itemView.context.getString(R.string.item_prestamo_id, prestamo.id)
            tvusuario.text = itemView.context.getString(R.string.item_prestamo_usuario, prestamo.nameUser)
            tvbloque.text = itemView.context.getString(R.string.item_prestamo_bloque, prestamo.bloque)
            tvDetalle.text = itemView.context.getString(R.string.item_prestamo_detalle)
            tvdireccion.text = itemView.context.getString(R.string.item_prestamo_direccion, prestamo.direccion)
            tvprograma.text = itemView.context.getString(R.string.item_prestamo_programa, prestamo.programa)
            tvestado.text = itemView.context.getString(R.string.item_prestamo_estado,prestamo.estado)
            tvdescripcion.text = itemView.context.getString(R.string.item_prestamo_descripcion,prestamo.descripcion)
            tvuserId.text = itemView.context.getString(R.string.item_prestamo_userid, prestamo.userId.toString())
            tvsurnameUser.text = itemView.context.getString(R.string.item_prestamos_surnameuser,prestamo.surnameUser)
            tvcedulaUser.text = itemView.context.getString(R.string.item_prestamos_celular,prestamo.cedulaUser)
            tvciudad.text = itemView.context.getString(R.string.item_prestamo_ciudad,prestamo.ciudad)
            tvactivo.text = itemView.context.getString(R.string.item_prestamo_activo,prestamo.nombreActivo)
            tvserial.text = itemView.context.getString(R.string.item_prestamo_serial,prestamo.serial)
            tvplaca.text = itemView.context.getString(R.string.item_prestamo_placa,prestamo.placa)
            tvcelular.text = itemView.context.getString(R.string.item_prestamo_celular,prestamo.celular)
            tvreferencia.text = itemView.context.getString(R.string.item_prestamo_referencia,prestamo.referencia)
            tvcantidad.text = itemView.context.getString(R.string.item_prestamo_cantidad,prestamo.cantidad.toString())
            tvsalidaPor.text = itemView.context.getString(R.string.item_prestamo_salidapor,prestamo.salidaPor)
            tveditadoPor.text = itemView.context.getString(R.string.item_prestamo_editadopor,prestamo.editadoPor)
            tvCreado.text = itemView.context.getString(R.string.item_prestamo_creado,prestamo.createdAt)
            tvactualizado.text = itemView.context.getString(R.string.item_prestamo_actualizado,prestamo.updatedAt)

            ibExpand.setOnClickListener(){
                if (linearLayoutDetails.visibility == View.VISIBLE){
                    linearLayoutDetails.visibility = View.GONE
                    ibExpand.setImageResource(R.drawable.detalle)
                } else {
                    linearLayoutDetails.visibility = View.VISIBLE
                    ibExpand.setImageResource(R.drawable.detallemenos)
                }

            }
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



