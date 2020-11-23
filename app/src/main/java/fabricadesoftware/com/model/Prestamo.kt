package fabricadesoftware.com.model

import com.google.gson.annotations.SerializedName

/*
"id": 1,
"user_id": 1,
"name_user": "Pablo Andres",
"surname_user": "Gonzalez Angel",
"cedula_user": "80153783",
"ciudad": "Bogota",
"bloque": "Casa",
"direccion": "Carrera 9 # 170 - 20",
"estado": "Abierto",
"nombre_activo": "portatil",
"serial": "1",
"placa": "1",
"salon": "TI",
"programa": "Auxiliar TI",
"celular": "3144534311",
"referencia": "1",
"cantidad": 1,
"salida_por": "No aplica",
"descripcion": "Prestamo portatil para trabajo en casa",
"editado_por": "pabloandres6@gmail.com",
"created_at": "2020-09-29T03:28:53.000000Z",
"updated_at": "2020-11-06T00:07:21.000000Z"
 */

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






