package com.mkttestprojects.hellokotlin.ui.main.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mkttestprojects.hellokotlin.R
import com.mkttestprojects.hellokotlin.common.BaseFragment
import com.mkttestprojects.hellokotlin.models.base.Resource
import com.mkttestprojects.hellokotlin.ui.main.MovieAdapter
import com.mkttestprojects.hellokotlin.util.SmartScrollListener
import com.mkttestprojects.hellokotlin.viewmodels.ViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener,
    SmartScrollListener.OnSmartScrollListener {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    var page: Int = 1

    lateinit var search: String

    private val homeFragmentViewModel by lazy {
        ViewModelProvider(this, providerFactory).get(HomeViewModel::class.java)
    }

    private val movieAdapter by lazy {
        MovieAdapter()
    }

    private val movieAdapter2 by lazy {
        MovieAdapter()
    }

    private val searchViewModel by lazy {
        ViewModelProvider(this, providerFactory).get(SearchViewModel::class.java)
    }

    private val smartScrollListener by lazy {
        SmartScrollListener(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_home
    }

    override fun setUpContents(savedInstanceState: Bundle?) {

        homeFragmentViewModel.page = page

        searchViewModel.page = page

        et_search.addTextChangedListener(textWatcher)

        rv_search_result_items.visibility = View.GONE

        initMovieRecycler()

        subscribeMovies()

        swipe_refresh_layout.setOnRefreshListener(this)
    }

    private fun initMovieRecycler() {
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        recycler_view.adapter = movieAdapter
        recycler_view.addOnScrollListener(smartScrollListener)

    }

    private fun subscribeMovies() {
        if (page == 1)
            movieAdapter.clear()

        homeFragmentViewModel.observeMovies().observe(this, Observer {
            when (it.status) {
                Resource.Status.ERROR -> {
                    movieAdapter.clearFooter()
                    showToastMsg(it.message)
                }

                Resource.Status.LOADING -> {
                    movieAdapter.showLoading()
                }

                Resource.Status.SUCCESS -> {
                    movieAdapter.clearFooter()
                    movieAdapter.add(it.data.results.toList())
                }
            }
        })
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                if (s.length == 0) {
                    movieAdapter2.clear()
                    rv_search_result_items.visibility = View.GONE
                    recycler_view.visibility = View.VISIBLE
                    initMovieRecycler()
                    subscribeMovies()
                }
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            initSearchRecycler()

            if (s == "") {
                movieAdapter2.clear()
            } else if (s!!.length != 0) {
                search = s.toString()
                movieAdapter2.clear()
                getSearchResults(search, page)
            } else {
                movieAdapter2.clear()
            }
        }

    }

    private fun initSearchRecycler() {

        recycler_view.visibility = View.GONE
        rv_search_result_items.visibility = View.VISIBLE

        rv_search_result_items.setLayoutManager(
            StaggeredGridLayoutManager(
                3,
                GridLayoutManager.VERTICAL
            )
        )
        rv_search_result_items.setAdapter(movieAdapter2)
    }

    private fun getSearchResults(search: String, page: Int) {

        if (page == 1)
            movieAdapter2.clear()

        searchViewModel.observeSearchResults(search).observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    movieAdapter2.showLoading()
                }
                Resource.Status.ERROR -> {
                    movieAdapter2.clearFooter()
                    showToastMsg(it.message)
                }
                Resource.Status.SUCCESS -> {
                    movieAdapter2.clearFooter()
                    movieAdapter2.add(it.data.results.toList())
                }
            }
        })

    }

    override fun onRefresh() {
        swipe_refresh_layout.isRefreshing = false
        recycler_view.visibility = View.VISIBLE
        initMovieRecycler()
        subscribeMovies()
    }

    override fun onListEndReach() {
        page++
        homeFragmentViewModel.page = page
        subscribeMovies()
    }
}