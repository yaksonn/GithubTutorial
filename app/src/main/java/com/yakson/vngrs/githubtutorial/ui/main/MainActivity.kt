package com.yakson.vngrs.githubtutorial.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.yakson.vngrs.githubtutorial.R
import com.yakson.vngrs.githubtutorial.adapters.SearchRepoRecyclerAdapter
import com.yakson.vngrs.githubtutorial.databinding.ActivityMainBinding
import com.yakson.vngrs.githubtutorial.model.Item
import com.yakson.vngrs.githubtutorial.network.core.ApiException
import com.yakson.vngrs.githubtutorial.ui.BaseActivity
import com.yakson.vngrs.githubtutorial.ui.repositorydetail.RepositoryDetailActivity
import com.yakson.vngrs.githubtutorial.utils.EndlessRecyclerViewScrollListener
import com.yakson.vngrs.githubtutorial.utils.NetworkState
import com.yakson.vngrs.githubtutorial.utils.PER_PAGE
import com.yakson.vngrs.githubtutorial.utils.REPO_DETAIL_DATA
import com.yakson.vngrs.githubtutorial.utils.extension.setGone
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private var currentPage = 1
    private var queryText: String? = null
    private lateinit var searchRepoRecyclerAdapter: SearchRepoRecyclerAdapter
    private var repoSearchItem: ArrayList<Item?> = arrayListOf()

    override fun getViewModelClass(): KClass<MainViewModel> {
        return MainViewModel::class
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        initObservers()
        setStandingUnderOverRecyclerView()
    }

    /**
     * Initialize the observers
     */
    private fun initObservers() {
        getViewModel().getNetworkStatus()?.observe(this, Observer {
            when (it.status) {
                NetworkState.Status.LOADING -> {
                    showProgressDialog()
                }
                NetworkState.Status.SUCCESS -> {
                    hideProgressDialog()
                }
                NetworkState.Status.FAILED -> {
                    hideProgressDialog()
                }
            }
        })

        getViewModel().getError()?.observe(this, Observer {
            when (it.status) {
                ApiException.ExceptionStatus.API_ERROR -> {
                    LogUtils.d("api error")
                }
                ApiException.ExceptionStatus.GENERAL_ERROR -> {
                    LogUtils.d("general error")
                }
            }
        })

        getViewModel().getRepoData().observe(this, Observer { result ->
            result?.let { repoSearchItem ->

                searchRepoRecyclerAdapter.setData(repoSearchItem as ArrayList<Item?>)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchManager =
            this@MainActivity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchView: SearchView? = null

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this@MainActivity.componentName))
        }
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(queryText: String): Boolean {
                if (queryText.length > 5) {
                    val handler = Handler(Looper.getMainLooper())
                    handler.post({
                        getData(queryText, PER_PAGE, currentPage)
                    })
                } else if (queryText.isEmpty()) {
                    resultNotFoundView.setGone()
                }
                return false
            }
            override fun onQueryTextSubmit(query: String) = false
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun setStandingUnderOverRecyclerView() {
        searchRepoRecyclerAdapter = SearchRepoRecyclerAdapter({ item -> onEventClicked(item)})
        val manager = LinearLayoutManager(this@MainActivity)
        searchRecyclerView.apply {
            adapter = searchRepoRecyclerAdapter
            addOnScrollListener(object :
                EndlessRecyclerViewScrollListener(manager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int) {
                    currentPage++
                    queryText?.let { getViewModel().fetchRepo(it, PER_PAGE, currentPage) }
                }
            })
            layoutManager = manager
        }
    }

    private fun getData(queryText: String, perPage: Int, currentPage: Int) {
        getViewModel().fetchRepo(queryText, perPage, currentPage)
    }

    private fun onEventClicked(
        data: Item
    ) {
        Paper.book().write(REPO_DETAIL_DATA, data)
        // Send data to other activity
        val intent = Intent(this, RepositoryDetailActivity::class.java)
        startActivity(intent)
    }
}
