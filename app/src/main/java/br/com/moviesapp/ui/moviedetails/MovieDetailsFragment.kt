package br.com.moviesapp.ui.moviedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.moviesapp.R
import br.com.moviesapp.ui.MainActivity
import br.com.moviesapp.ui.movies.MoviesViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_details.*
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by activityViewModels<MoviesViewModel>{
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Grabs the mainComponent from the Activity and injects this Fragment
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.movie.observe(viewLifecycleOwner, Observer { movie ->
            title_text.text = movie.title
            year_text.text = movie.year
            rated_text.text = movie.rated
            runtime_text.text = movie.runtime
            plot_text.text = movie.plot
            genre_text.text = movie.genre
            actors_text.text = movie.actors
            director_text.text = movie.director
            writers_text.text = movie.writer
            released_text.text = movie.released
            Glide.with(this)
                .load(movie.poster)
                .fitCenter()
                .into(movie_image)
        })
    }
}