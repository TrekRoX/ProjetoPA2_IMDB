package br.com.willtrkapp.projetopa2.Services
import br.com.willtrkapp.projetopa2.Models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface IDMBEndPoint {
    @GET("/")
    fun getFilmByTitle(@Query("t") title: String): Call<Movie>

    @GET("/")
    fun getFilmById(@Query("i") id: String): Call<Movie>

    @GET("/")
    fun getFilmsListByTitle(@Query("s") title: String): Call<Movie>
}