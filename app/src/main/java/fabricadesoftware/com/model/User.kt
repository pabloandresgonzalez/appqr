package fabricadesoftware.com.model

data class User(
    val id: Int,
    val role: String,
    val name: String,
    val surname: String,
    val cedula: String,
    val email: String,
    val celular: String,
    val image: String
)