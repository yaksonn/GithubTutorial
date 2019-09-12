package com.yakson.vngrs.githubtutorial.ui.userdetail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yakson.vngrs.githubtutorial.R
import com.yakson.vngrs.githubtutorial.adapters.UserDetailOtherRepoRecyclerAdapter
import com.yakson.vngrs.githubtutorial.databinding.ActivityUserDetailBinding
import com.yakson.vngrs.githubtutorial.model.Item
import com.yakson.vngrs.githubtutorial.model.Owner
import com.yakson.vngrs.githubtutorial.ui.BaseActivity
import com.yakson.vngrs.githubtutorial.utils.NetworkState
import com.yakson.vngrs.githubtutorial.utils.REPOS
import com.yakson.vngrs.githubtutorial.utils.USER_NAME
import com.yakson.vngrs.githubtutorial.utils.USER_URL
import com.yakson.vngrs.githubtutorial.utils.extension.setCircleImage
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlin.reflect.KClass

class UserDetailActivity : BaseActivity<UserDetailViewModel, ActivityUserDetailBinding>() {

    private var userName: String? = null
    private var ownerUserDetail: Owner? = null
    private lateinit var userDetailOtherRepoRecyclerAdapter: UserDetailOtherRepoRecyclerAdapter

    private var userDetailOtherRepoItem: ArrayList<Item?> = arrayListOf()


    override fun getViewModelClass(): KClass<UserDetailViewModel> {
        return UserDetailViewModel::class
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_user_detail
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        initObservers()
        initializeAdapters()
        getIntentData()

    }

    /**
     * Get Intent Data
     */
    private fun getIntentData() {
        if (intent != null && intent.extras != null) {
            userName = intent.getStringExtra(USER_NAME)
            getViewModel().fetchUserDetail(USER_URL + "$userName")
            getViewModel().fetchUserDetailOtherRepo(USER_URL + "$userName/$REPOS")
        }
    }

    private fun initObservers() {
        getViewModel().getNetworkStatus()?.observe(this, Observer {
            when (it.status) {
                NetworkState.Status.LOADING -> {
//                    showProgressDialog()
                }
                NetworkState.Status.SUCCESS -> {
//                    hideProgressDialog()
                }
                NetworkState.Status.FAILED -> {
//                    hideProgressDialog()
                }
            }
        })

        getViewModel().getError()?.observe(this, Observer {
        })

        getViewModel().getUserDetail().observe(this, Observer {
            ownerUserDetail = it
            it?.let { it1 -> setUI(it1) }
        })

        getViewModel().getUserDetailOtherRepo().observe(this, Observer { result ->
            result?.let { userDetailOtherRepoItem ->
                userDetailOtherRepoRecyclerAdapter.setData(userDetailOtherRepoItem as ArrayList<Item?>)
            }
        })
    }

    /**
     * Fill UI with data
     */
    private fun setUI(data: Owner) {
        data.avatarUrl?.let { userAvatarImageView.setCircleImage(it) }
        userNameTextView.setText(data.name)
        companyTextView.setText(data.company)
        locationTextView.setText(data.location)
        publicReposTextView.setText(data.publicRepos.toString())
        followersTextView.setText(data.followers.toString())
        followingTextView.setText(data.following.toString())
    }

    /**
     * Initialize the adapters
     */
    private fun initializeAdapters() {
        userDetailOtherRepoRecyclerAdapter = UserDetailOtherRepoRecyclerAdapter()
        val manager = LinearLayoutManager(this@UserDetailActivity)
        userDetailOtherRepoRecyclerView.apply {
            adapter = userDetailOtherRepoRecyclerAdapter
            layoutManager = manager
        }
    }
}

