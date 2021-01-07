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
    @Headers("Accept: application/json")
    fun storePrestamo(
            @Header("Authorization") authHeader: String,
            @Query("ciudad") ciudad: String,
            @Query("bloque") bloque: String,
            @Query("direccion") direccion: String,
            @Query("salon") salon: String,
            @Query("programa") programa: String,
            @Query("descripcion") descripcion: String
    ): Call<SimpleResponse>

    @POST("register")
    @Headers("Accept: application/json")
    fun postRegiter(
            @Query("name") name: String,
            @Query("surname") surname: String,
            @Query("cedula") cedula: String,
            @Query("email") email: String,
            @Query("celular") celular: String,
            @Query("password") password: String
    ): Call<LoginResponse>

    @POST("fcm/token")
    fun postToken(
            @Header("Authorization") authHeader: String,
            @Query("device_token") token: String
    ): Call<Void>

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