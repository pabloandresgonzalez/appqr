package fabricadesoftware.com.model

import com.google.gson.annotations.SerializedName

data class Prestamo(

    val id: Int,
    val ciudad: String,
    val bloque: String,
    val direccion: String,
    val estado: String,
    val serial: String,
    val placa: String,
    val salon: String,
    val programa: String,
    val celular: String,
    val referencia: String,
    val cantidad: Int,
    val descripcion: String,

    @SerializedName("user_id") val userId: Int,
    @SerializedName("name_user") val nameUser: String,
    @SerializedName("surname_user") val surnameUser: String,
    @SerializedName("cedula_user") val cedulaUser: String,
    @SerializedName("nombre_activo") val nombreActivo: String,
    @SerializedName("salida_por") val salidaPor: String,
    @SerializedName("editado_por") val editadoPor: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)






