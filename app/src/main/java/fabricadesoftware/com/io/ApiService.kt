package fabricadesoftware.com.io

import com.google.gson.GsonBuilder
import fabricadesoftware.com.io.response.LoginResponse
import fabricadesoftware.com.model.Prestamo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


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