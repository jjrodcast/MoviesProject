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

    private val TAG = MainActivity::class.java.canonicalName
    private val presente: MoviePresenter = MoviePresenterImp(this)
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar)

        presente.getMovies(page = 1)

        fabrefresh.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                (fabrefresh.drawable as AnimatedVectorDrawable).start()
            }
            //progressbar.visibility = View.VISIBLE
            val page = randonPage()
            presente.getMovies(page)
        }

        recyclermovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fabrefresh.visibility === View.VISIBLE) fabrefresh.hide()
                else if (dy < 0 && fabrefresh.visibility !== View.VISIBLE) fabrefresh.show()
            }
        })
    }

    override fun getContext() = applicationContext

    override fun onClick(movie: Movie) {
        toast(movie.title)
    }

    override fun showMovies(response: ResponseMovies) {

        val adapter = MovieAdapter(R.layout.item_recycler_movie, response.movies, this)

        val orientation = resources.configuration.orientation
        var spancount = 2
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) spancount = 3

        recyclermovies.setHasFixedSize(true)
        layoutManager = GridLayoutManager(this, spancount, GridLayoutManager.VERTICAL, false)
        recyclermovies.layoutManager = layoutManager


        if (recyclermovies.itemDecorationCount == 0) {
            val decorator = CustomDecorator(this, R.integer.offset)
            recyclermovies.addItemDecoration(decorator)
        }
        recyclermovies.adapter = adapter

        //progressbar.visibility = View.GONE

    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) (layoutManager as GridLayoutManager).spanCount = 3
        else (layoutManager as GridLayoutManager).spanCount = 2
    }

    private fun randonPage(): Int = (1..6).random()

}
