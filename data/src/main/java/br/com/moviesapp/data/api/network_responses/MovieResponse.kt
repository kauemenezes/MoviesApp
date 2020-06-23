package br.com.moviesapp.data.api.network_responses

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("title")
    var title: String?,
    @SerializedName("year")
    var year: String?,
    @SerializedName("rated")
    var rated: String?,
    @SerializedName("released")
    var released: String?,
    @SerializedName("runtime")
    var runtime: String?,
    @SerializedName("genre")
    var genre: String?,
    @SerializedName("director")
    var director: String?,
    @SerializedName("writer")
    var writer: String?,
    @SerializedName("actors")
    var actors: String?,
    @SerializedName("plot")
    var plot: String?,
    @SerializedName("poster")
    var poster: String?
)