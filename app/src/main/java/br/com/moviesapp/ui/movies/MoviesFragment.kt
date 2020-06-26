package br.com.moviesapp.ui.movies

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import br.com.moviesapp.R
import br.com.moviesapp.commons.Error
import br.com.moviesapp.commons.Loading
import br.com.moviesapp.commons.Success
import br.com.moviesapp.data.utils.SharedPrefsHelper
import br.com.moviesapp.domain.models.Movie
import br.com.moviesapp.ui.MainActivity
import br.com.moviesapp.utils.checkAndSortList
import br.com.moviesapp.utils.saveListOrderInPreferences
import kotlinx.android.synthetic.main.fragment_movies.*
import java.net.UnknownHostException
import javax.inject.Inject


class MoviesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper
    private val viewModel by activityViewModels<MoviesViewModel>{
        viewModelFactory
    }
    private val moviesListAdapter: MoviesListAdapter by lazy {
        MoviesListAdapter(this) {
            callMovieDetails(it)
        }
    }
    private var searchView: SearchView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Grabs the mainComponent from the Activity and injects this Fragment
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        movies_recyclerview.adapter = moviesListAdapter
        val itemTouchHelper = ItemTouchHelper(moviesListAdapter.simpleCallback)
        itemTouchHelper.attachToRecyclerView(movies_recyclerview)
        subscribeObservers()
        addListener()
    }

    private fun addListener() {
        swipe_refresh_movies.setColorSchemeResources(R.color.colorPrimary)
        swipe_refresh_movies.setOnRefreshListener {
            getMovies()
        }
    }

    private fun subscribeObservers() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Loading -> {
                    displayLoadingState()
                }
                is Success -> {
                    hideLoadingState()
                    swipe_refresh_movies.isRefreshing = false
                }
                is Error -> {
                    if (progress_movies.isVisible || swipe_refresh_movies.isRefreshing) {
                        val text = if (it.error is UnknownHostException) {
                            getString(R.string.text_connection_unavailable)
                        } else {
                            getString(R.string.text_main_generic_error)
                        }
                        val toast = Toast.makeText(requireContext(), text, Toast.LENGTH_LONG)
                        toast.show()
                    }
                    hideLoadingState()
                    swipe_refresh_movies.isRefreshing = false
                }
            }
        })

        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            if (!movies.isNullOrEmpty()) {
                checkAndSortList(movies.toMutableList(), moviesListAdapter, sharedPrefsHelper)
            }
        })
    }

    private fun displayLoadingState() {
        movies_recyclerview.visibility = View.GONE
        progress_movies.visibility = View.VISIBLE
    }

    private fun hideLoadingState() {
        movies_recyclerview.visibility = View.VISIBLE
        progress_movies.visibility = View.GONE
    }

    private fun getMovies() {
        viewModel.loadMovies(true)
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
        viewModel.setMovie(movie)
        val action = MoviesFragmentDirections.nextMovieDetails()
        findNavController().navigate(action)
    }
}