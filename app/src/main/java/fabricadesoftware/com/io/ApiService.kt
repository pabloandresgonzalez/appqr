package fabricadesoftware.com.io

import com.google.gson.GsonBuilder
import fabricadesoftware.com.io.response.LoginResponse
import fabricadesoftware.com.io.response.SimpleResponse
import fabricadesoftware.com.model.Prestamo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*
import kotlin.collections.ArrayList


interface ApiService {

    @POST("login")
     fun postLogin(@Query("email") email: String, @Query("password") password: String):
            Call<LoginResponse>

    @POST("logout")
    fun postLogout(@Header("Authorization") authHeader: String):
            Call<Void>

    @GET("prestamos")
    fun getPrestamos(@Header("Authorization") authHeader: String):
            Call<ArrayList<Prestamo>>

    @POST("prestamos")
    @Headers("Accept: application/jason")
    fun storePrestamo(
            @Header("Authorization") authHeader: String,
            /*
            @Query("ciudad") ciudad: String,
            @Query("bloque") bloque: String,
            @Query("direccion") direccion: String,
            @Query("salon") salon: String,
            @Query("programa") programa: String,
            @Query("celular") celular: String,
            @Query( "descripcion") descripcion: String,
             */
           // @Query("user_id") user_id: Int,
            //@Query("name_user") name_user: Int,
            //@Query("surname_user") surname_user: String,
            //@Query("cedula_user") cedula_user: String,
            @Query("ciudad") ciudad: String,
            @Query("bloque") bloque: String,
            @Query("direccion") direccion: String,
            @Query("salon") salon: String,
            @Query("programa") programa: String,
            @Query("celular") celular: String,
            @Query( "descripcion") descripcion: String,
            @Query("estado") estado: String,
            @Query("referencia") referencia: String,
            @Query("cantidad") cantidad: String,
            @Query("editado_por") editadopor: String

    ):
    Call<SimpleResponse>


    companion object Factory {
        private const val BASE_URL = "http://167.172.255.179/api/auth/"

        fun create() : ApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val gson = GsonBuilder()
                    .setLenient()
                    .create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}