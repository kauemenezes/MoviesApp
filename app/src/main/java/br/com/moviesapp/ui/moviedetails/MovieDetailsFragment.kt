package br.com.moviesapp.ui.moviedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import br.com.moviesapp.R
import br.com.moviesapp.databinding.MovieDetails
import br.com.moviesapp.ui.MainActivity
import br.com.moviesapp.ui.movies.MoviesViewModel
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by activityViewModels<MoviesViewModel>{
        viewModelFactory
    }
    private lateinit var binding: MovieDetails

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Grabs the mainComponent from the Activity and injects this Fragment
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            binding.movie = movie
        }
    }
}