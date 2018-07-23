package pe.com.onecode.movieapp.ui

import android.content.res.Configuration
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import pe.com.onecode.movieapp.R
import pe.com.onecode.movieapp.entities.Movie
import pe.com.onecode.movieapp.entities.ResponseMovies
import pe.com.onecode.movieapp.presenter.MoviePresenter
import pe.com.onecode.movieapp.presenter.MoviePresenterImp
import pe.com.onecode.movieapp.ui.adapter.MovieAdapter
import pe.com.onecode.movieapp.ui.decorator.CustomDecorator
import pe.com.onecode.movieapp.utilities.ItemClick
import pe.com.onecode.movieapp.utilities.random
import pe.com.onecode.movieapp.utilities.toast
import pe.com.onecode.movieapp.view.MovieView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), MovieView, ItemClick<Movie> {

    private val presenter: MoviePresenter = MoviePresenterImp(this)
    private lateinit var adapter: MovieAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar)

        createAdapter()

        addScrollEvent()

        presenter.getMovies(page = 1)

        fabrefresh.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                (fabrefresh.drawable as AnimatedVectorDrawable).start()
            }
            val page: Int = randomPage()
            presenter.getMovies(page)
        }
    }

    override fun getContext() = applicationContext

    override fun onClick(movie: Movie) {
        toast(movie.title)
    }

    override fun showMovies(response: ResponseMovies) {
        adapter.addData(response.movies)
    }

    // To use this, you have to change your manifest and put orientation
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) layoutManager.spanCount = 3
        else layoutManager.spanCount = 2
    }

    private fun createAdapter() {

        adapter = MovieAdapter(R.layout.item_recycler_movie, this)

        val orientation = resources.configuration.orientation
        var spanCount = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2

        layoutManager = GridLayoutManager(this, spanCount, GridLayoutManager.VERTICAL, false)
        recyclermovies.setHasFixedSize(true)
        recyclermovies.layoutManager = layoutManager

        if (recyclermovies.itemDecorationCount == 0) {
            val decorator = CustomDecorator(this, R.integer.offset)
            recyclermovies.addItemDecoration(decorator)
        }
        recyclermovies.adapter = adapter
    }

    private fun addScrollEvent() {
        recyclermovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fabrefresh.visibility === View.VISIBLE) fabrefresh.hide()
                else if (dy < 0 && fabrefresh.visibility !== View.VISIBLE) fabrefresh.show()
            }
        })
    }

    private fun randomPage(): Int = (1..6).random()

}
