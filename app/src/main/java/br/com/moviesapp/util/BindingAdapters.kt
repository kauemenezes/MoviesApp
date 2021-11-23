package br.com.moviesapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {

    @BindingAdapter("app:moviePoster")
    @JvmStatic
    fun moviePoster(view: ImageView, moviePoster: String?) {
        moviePoster?.let {
            Glide.with(view.context)
                .load(moviePoster)
                .fitCenter()
                .into(view)
        }
    }
}