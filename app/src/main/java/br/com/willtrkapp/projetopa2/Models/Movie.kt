package br.com.willtrkapp.projetopa2.Models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Title")
    var title: String = "",
    @SerializedName("Year")
    var year: String = "",
    @SerializedName("Rated")
    var rated: String = "",
    @SerializedName("Released")
    var released: String = "",
    @SerializedName("Runtime")
    var runtime: String = "",
    @SerializedName("Genre")
    var genre: String = "",
    @SerializedName("Director")
    var director: String = "",
    @SerializedName("Writer")
    var writer: String = "",
    @SerializedName("Actors")
    var actors: String = "",
    @SerializedName("Plot")
    var plot: String = "",
    @SerializedName("Language")
    var language: String = "",
    @SerializedName("Country")
    var country: String = "",
    @SerializedName("Awards")
    var awards: String = "",
    @SerializedName("Poster")
    var poster: String = "",
    @SerializedName("Ratings")
    var ratings: List<Rating> = listOf(),
    @SerializedName("Metascore")
    var metascore: String = "",
    @SerializedName("imdbRating")
    var imdbRating: String = "",
    @SerializedName("imdbVotes")
    var imdbVotes: String = "",
    @SerializedName("imdbID")
    var imdbID: String = "",
    @SerializedName("Type")
    var type: String = "",
    @SerializedName("DVD")
    var dVD: String = "",
    @SerializedName("BoxOffice")
    var boxOffice: String = "",
    @SerializedName("Production")
    var production: String = "",
    @SerializedName("Website")
    var website: String = "",

    @SerializedName("Response")
    var response: String = "",

    //Error na Requisição
    @SerializedName("Error")
    var error: String = "",

    //Rating
    @SerializedName("Source")
    var source: String = "",
    @SerializedName("Value")
    var value: String = "",

    //Search Movies List
    // Fields on Response (Title, Year, imdbID, Type e Poster + Search[array], totalResults e Response)
    @SerializedName("Search")
    var search: List<Movie> = listOf(),
    @SerializedName("totalResults")
    var totalResults: String = "",
    @SerializedName("page")
    var page: Int = 1

)