package br.com.moviesapp.ui.movies

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import br.com.moviesapp.R
import br.com.moviesapp.commons.Error
import br.com.moviesapp.commons.Loading
import br.com.moviesapp.commons.Success
import br.com.moviesapp.data.utils.SharedPrefsHelper
import br.com.moviesapp.databinding.FragmentMoviesBinding
import br.com.moviesapp.domain.models.Movie
import br.com.moviesapp.util.checkAndSortList
import br.com.moviesapp.util.saveListOrderInPreferences
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.UnknownHostException


class MoviesFragment : Fragment() {

    private val sharedPrefsHelper: SharedPrefsHelper by inject()
    private val moviesViewModel: MoviesViewModel by viewModel()
    private val moviesListAdapter: MoviesListAdapter by lazy {
        MoviesListAdapter(this) {
            callMovieDetails(it)
        }
    }
    private var searchView: SearchView? = null
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initComponents() {
        binding.moviesRecyclerview.adapter = moviesListAdapter
        val itemTouchHelper = ItemTouchHelper(moviesListAdapter.simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.moviesRecyclerview)
        subscribeObservers()
        addListener()
    }

    private fun addListener() {
        binding.swipeRefreshMovies.setColorSchemeResources(R.color.colorPrimary)
        binding.swipeRefreshMovies.setOnRefreshListener {
            getMovies()
        }
    }

    private fun subscribeObservers() {
        moviesViewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is Loading -> {
                    displayLoadingState()
                }
                is Success -> {
                    hideLoadingState()
                    binding.swipeRefreshMovies.isRefreshing = false
                }
                is Error -> {
                    if (binding.progressMovies.isVisible || binding.swipeRefreshMovies.isRefreshing) {
                        val text = if (it.error is UnknownHostException) {
                            getString(R.string.text_connection_unavailable)
                        } else {
                            getString(R.string.text_main_generic_error)
                        }
                        val toast = Toast.makeText(requireContext(), text, Toast.LENGTH_LONG)
                        toast.show()
                    }
                    hideLoadingState()
                    binding.swipeRefreshMovies.isRefreshing = false
                }
            }
        }

        moviesViewModel.movies.observe(viewLifecycleOwner) { movies ->
            if (!movies.isNullOrEmpty()) {
                checkAndSortList(movies.toMutableList(), moviesListAdapter, sharedPrefsHelper)
            }
        }
    }

    private fun displayLoadingState() {
        binding.moviesRecyclerview.visibility = View.GONE
        binding.progressMovies.visibility = View.VISIBLE
    }

    private fun hideLoadingState() {
        binding.moviesRecyclerview.visibility = View.VISIBLE
        binding.progressMovies.visibility = View.GONE
    }

    private fun getMovies() {
        moviesViewModel.loadMovies(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movies_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        if (searchView != null) {
            searchView!!.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            searchView!!.queryHint = getString(R.string.search_text)
            searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String): Boolean {
                    moviesListAdapter.filter.filter(newText)
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    moviesListAdapter.filter.filter(query)
                    return false
                }

            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                true
            }
            R.id.action_save_list_order -> {
                saveListOrderInPreferences(moviesListAdapter, sharedPrefsHelper)
                Toast.makeText(requireContext(), getString(R.string.toast_save_list_order_text),
                    Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun callMovieDetails(movie: Movie) {
        val action = MoviesFragmentDirections.nextMovieDetails(movie.id)
        findNavController().navigate(action)
    }
}