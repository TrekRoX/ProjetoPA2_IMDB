package br.com.willtrkapp.projetopa2.Services

import android.util.Log
import br.com.willtrkapp.projetopa2.MainActivity
import br.com.willtrkapp.projetopa2.Models.Movie
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IMDBClient(mainActivity: MainActivity) {

    val URL_IMDB: String = "http://www.omdbapi.com"
    val API_KEY_OMDB: String = "3492cdff"

    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    var callback: MovieCallback? = null

    // Instancia cliente HTTP
    init {
        // Adiciona um interceptador que é um objeto de uma classe anônima
        okHttpClientBuilder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                // Requisição interceptada
                val reqIntercept: Request = chain.request()
                // Cria nova requisição com e adiciona o cabeçalho
                val url = reqIntercept.url().newBuilder().addQueryParameter("apikey", API_KEY_OMDB).build()
                val newReq: Request = reqIntercept.newBuilder()
                    .url(url)
                    .method(reqIntercept.method(), reqIntercept.body())
                    .build()

                return chain.proceed(newReq)
            }
        })
    }

    // Novo objeto Retrofit usando a URL base e o HttpClient com interceptador
    val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL_IMDB).client(okHttpClientBuilder.build()).build()

    // Cria um objeto, a partir da Interface Retrofit, que contém as funções de requisição
    val endpointsApi: IDMBEndPoint = retrofit.create(IDMBEndPoint::class.java)

    fun findMovie(title: String){


        endpointsApi.getFilmByTitle(title).enqueue(

            object : Callback<Movie> {
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    call.cancel()
                    callback?.onRequestFail(t)
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    val body = response.body()
                    if (body != null){

                        if (body.response.equals("False")){
                            callback?.onResponseFail(body)
                        }
                        else{
                            callback?.onResponse(body)
                        }
                    }
                }

            }
        )
    }

    fun findMovieById(idIMDB: String){

        endpointsApi.getFilmById(idIMDB).enqueue(

            object : Callback<Movie> {
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    call.cancel()
                    callback?.onRequestFail(t)
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    val body = response.body()
                    if (body != null){

                        if (body.response.equals("False")){
                            callback?.onResponseFail(body)
                        }
                        else{
                            callback?.onResponse(body)
                        }
                    }
                }

            }
        )
    }

    fun searchListMovies(title: String){

        endpointsApi.getFilmsListByTitle(title).enqueue(

            object : Callback<Movie> {
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    call.cancel()
                    callback?.onRequestFail(t)
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    val body = response.body()
                    if (body != null){

                        if (body.response.equals("False")){
                            callback?.onResponseFail(body)
                        }
                        else{
                            callback?.onResponse(body)
                        }
                    }
                }

            }
        )
    }

    interface MovieCallback{
        fun onResponse(obj: Movie)
        fun onResponseFail(obj: Movie)
        fun onRequestFail(err: Throwable)
    }
}