package fabricadesoftware.com.model

/*
{
"id": 1,
"role": "admin",
"name": "Pablo Andres",
"surname": "Gonzalez Angel",
"cedula": "80153783",
"email": "pabloandres6@gmail.com",
"celular": "3144534311",
"image": "1604513170codigo.PNG"
}
*/

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