package com.mkttestprojects.hellokotlin.ui.moviedetail

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.mkttestprojects.hellokotlin.R
import com.mkttestprojects.hellokotlin.common.BaseActivity
import com.mkttestprojects.hellokotlin.custom_control.BlurImage
import com.mkttestprojects.hellokotlin.models.DetailMovieListModel
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.ui.main.MovieAdapter
import com.mkttestprojects.hellokotlin.ui.movietrailer.MovieTrailerActivity
import com.mkttestprojects.hellokotlin.util.AppConstants.BASE_IMG_URL
import com.mkttestprojects.hellokotlin.viewmodels.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieDetailActivity : BaseActivity(), View.OnClickListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var requestManager : RequestManager

    var runtime by Delegates.notNull<Int>()

    lateinit var duration : String

    var movieId: Int = 0

    private val adapter by lazy {
        MovieAdapter()
    }

    private val movieDetailViewModel by lazy {
        ViewModelProvider(this,viewModelProviderFactory).get(MovieDetailViewModel::class.java)
    }

    companion object {
        val IE_MOVIEID = "IE_MOVIEID"

        fun MovieDetailActivityIntent(context: Context, Id: Int): Intent {
            return Intent(context,MovieDetailActivity::class.java).apply { putExtra(IE_MOVIEID,Id) }
        }

        private const val TAG = "MovieDetailActivity"
    }
    
    override fun getLayoutResource(): Int {
        return R.layout.activity_movie_detail
    }

    override fun setUpContents(savedInstanceState: Bundle?) {

        movieId = intent.getSerializableExtra(IE_MOVIEID) as Int
        movieDetailViewModel.movieId = movieId

        getMovieDetail(movieId)

        setupSimilarMoviesRecycler()
        getSimilarMovies(movieId)

        layout_play.setOnClickListener(this)
    }

    private fun setupSimilarMoviesRecycler() {
        similar_movie_rv.setLayoutManager(
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        )
        similar_movie_rv.setAdapter(adapter)
    }

    private fun getSimilarMovies(movieId: Int) {
        movieDetailViewModel.observeSimilarMoviesById(movieId)
            .observe(this, Observer {
                when(it.status){
                    Resource.Status.LOADING -> {
                        adapter.showLoading()
                    }
                    Resource.Status.SUCCESS -> {
                        adapter.clearFooter();
                        adapter.add(it.data.results.toList())
                    }
                    Resource.Status.ERROR -> {
                        adapter.clearFooter()
                        showToastMsg(it.message)
                    }
                }
            })
    }

    private fun getMovieDetail(movieId: Int) {
        movieDetailViewModel.observeMovieDetail(movieId).observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                }
                Resource.Status.SUCCESS -> {
                    bindMovieDetailData(it.data)
                }
                Resource.Status.ERROR -> {
                    showToastMsg(it.message)
                }
            }
        })
    }

    private fun bindMovieDetailData(data: DetailMovieListModel) {
        requestManager
            .asBitmap()
            .load(BASE_IMG_URL + data.poster_path)
            .into(object : CustomTarget<Bitmap>(){
                override fun onLoadCleared(placeholder: Drawable?) {

                }
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    val bitmapImage = BlurImage.fastblur(resource,0.08.toFloat(),5)

                    requestManager
                        .load(resource)
                        .into(iv_poster)

                    requestManager
                        .load(bitmapImage)
                        .into(iv_poster_bg)                            }

            })
        txt_movietitle.setText(data.title)

        runtime = data.runtime
        duration = (runtime / 60).toString() + "h " + runtime % 60 + "m"
        txt_duration.setText(duration)

        txt_movieOverview.setText(data.overview)

        txt_releaseyear.setText(data.release_date)

        if(data.adult == true){
            txt_isAdult.setText("18+")
        }
    }

    override fun onClick(v: View?) {
        startActivity(
            MovieTrailerActivity.MovieTrailerIntent(
                this@MovieDetailActivity,
                movieId
            )
        )

    }

}