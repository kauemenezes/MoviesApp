package br.com.moviesapp.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.moviesapp.R
import br.com.moviesapp.domain.models.Movie
import com.bumptech.glide.Glide
import java.util.*
import kotlin.collections.ArrayList

class MoviesListAdapter(private val fragment: MoviesFragment, val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MoviesListAdapter.ViewHolder>(), Filterable {

    private var movies: MutableList<Movie>? = null
    private var filteredMovies: MutableList<Movie>? = null

    fun setMoviesList(movies: List<Movie>) {
        this.movies = movies.toMutableList()
        this.filteredMovies = this.movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = if (filteredMovies != null) filteredMovies!!.size else 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val movie = filteredMovies!![position]

            title?.text = movie.title
            released?.text = movie.released
            genre?.text = movie.genre
            Glide.with(fragment)
                .load(movie.poster)
                .fitCenter()
                .into(posterImage!!)

            itemView.setOnClickListener{
                onClick(movie)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var posterImage: ImageView?= null
        var title: TextView?= null
        var released: TextView?= null
        var genre: TextView?= null

        init {
            posterImage = itemView.findViewById(R.id.poster_image)
            title = itemView.findViewById(R.id.title_text)
            released = itemView.findViewById(R.id.released_text)
            genre = itemView.findViewById(R.id.genre_text)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSequenceString = constraint.toString()
                if (charSequenceString.isEmpty()) {
                    filteredMovies = movies
                } else {
                    val filteredList: MutableList<Movie> = ArrayList()
                    for (movie in movies!!) {
                        if (movie.title!!.toLowerCase()
                                .contains(charSequenceString.toLowerCase())
                        ) {
                            filteredList.add(movie)
                        }
                        filteredMovies = filteredList
                    }
                }
                val results = FilterResults()
                results.values = filteredMovies
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredMovies = (results!!.values as List<Movie>).toMutableList()
                notifyDataSetChanged()
            }

        }
    }

    fun getList(): List<Movie>? {
        return filteredMovies
    }

    var simpleCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
        0
    ) {
        override fun onMove(
            @NonNull recyclerView: RecyclerView,
            @NonNull viewHolder: RecyclerView.ViewHolder,
            @NonNull target: RecyclerView.ViewHolder
        ): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition
            Collections.swap(filteredMovies, fromPosition, toPosition)
            recyclerView.adapter!!.notifyItemMoved(fromPosition, toPosition)
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            TODO("Not yet implemented")
        }
    }

    companion object {
        private val MOVIES_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}