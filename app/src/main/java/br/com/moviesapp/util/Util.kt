package br.com.moviesapp.util

import br.com.moviesapp.data.utils.SharedPrefsHelper
import br.com.moviesapp.domain.models.Movie
import br.com.moviesapp.ui.movies.MoviesListAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


fun saveListOrderInPreferences(moviesListAdapter: MoviesListAdapter, sharedPrefsHelper: SharedPrefsHelper) {
    val list = moviesListAdapter.getList()
    list?.let {
        val idList = list.map { it.id }
        val gson = Gson()
        val json = gson.toJson(idList)
        sharedPrefsHelper.put(SharedPrefsHelper.PREF_LIST_ORDER, json)
    }
}

fun checkAndSortList(movies: MutableList<Movie>, moviesListAdapter: MoviesListAdapter,
                     sharedPrefsHelper: SharedPrefsHelper) {
    val gson = Gson()
    val json = sharedPrefsHelper[SharedPrefsHelper.PREF_LIST_ORDER, ""]
    if (!json.isNullOrEmpty()) {
        val listType: Type = object: TypeToken<List<String>>(){}.type
        val idList: List<String> = gson.fromJson(json, listType)
        val orderById = idList.withIndex().associate { it.value to it.index }
        val sortedMovies = movies.sortedBy { orderById[it.id] }
        moviesListAdapter.setMoviesList(sortedMovies)
    } else {
        moviesListAdapter.setMoviesList(movies)
    }
}