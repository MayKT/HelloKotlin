package com.mkttestprojects.hellokotlin.ui.movietrailer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mkttestprojects.hellokotlin.R
import com.mkttestprojects.hellokotlin.common.BaseActivity
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.ui.moviedetail.MovieDetailActivity
import com.mkttestprojects.hellokotlin.viewmodels.ViewModelProviderFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_movie_trailer.*
import javax.inject.Inject

class MovieTrailerActivity : BaseActivity() {

    @Inject
    lateinit var viewModelProvier: ViewModelProviderFactory

    private val movieTrailerViewModel by lazy {
        ViewModelProvider(this, viewModelProvier).get(MovieTrailerViewModel::class.java)
    }

    var movieId: Int = 0

    companion object {
        val IE_MOVIEID = "IE_MOVIEID"

        fun MovieTrailerIntent(context: Context, Id: Int): Intent {
            return Intent(context, MovieTrailerActivity::class.java).apply {
                putExtra(
                    IE_MOVIEID,
                    Id
                )
            }
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_movie_trailer
    }

    override fun setUpContents(savedInstanceState: Bundle?) {
        movieId = intent.getSerializableExtra(MovieDetailActivity.IE_MOVIEID) as Int
        movieTrailerViewModel.movieId = movieId
        getTrailer(movieTrailerViewModel.movieId)
    }

    private fun getTrailer(movieId: Int) {
        movieTrailerViewModel.observeMovieTrailer(movieId)
            .observe(this, Observer {
                when (it.status) {
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.SUCCESS -> {
                        val movieid: String = it.data.results!!.get(0).key
                        lifecycle.addObserver(youtube_player)
                        youtube_player.addYouTubePlayerListener(object :
                            AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                youTubePlayer.loadVideo(movieid, 0f)
                            }
                        })
                    }
                    Resource.Status.ERROR -> {
                        showToastMsg(it.message)
                    }
                }
            })
    }
}