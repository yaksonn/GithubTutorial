package com.yakson.vngrs.githubtutorial.ui.repositorydetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yakson.vngrs.githubtutorial.R
import com.yakson.vngrs.githubtutorial.databinding.ActivityRepositoryDetailBinding
import com.yakson.vngrs.githubtutorial.model.Item
import com.yakson.vngrs.githubtutorial.ui.BaseActivity
import com.yakson.vngrs.githubtutorial.ui.userdetail.UserDetailActivity
import com.yakson.vngrs.githubtutorial.utils.REPO_DETAIL_DATA
import com.yakson.vngrs.githubtutorial.utils.USER_NAME
import com.yakson.vngrs.githubtutorial.utils.extension.setCircleImage
import com.yakson.vngrs.githubtutorial.utils.extension.startActivityModal
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_repository_detail.*
import org.jetbrains.anko.intentFor
import kotlin.reflect.KClass

class RepositoryDetailActivity :
    BaseActivity<RepositoryDetailViewModel, ActivityRepositoryDetailBinding>() {

    private var repositoryDetailData: Item? = null


    override fun getViewModelClass(): KClass<RepositoryDetailViewModel> {
        return RepositoryDetailViewModel::class
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_repository_detail
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        getIntentData()
    }

    /**
     * Read data from paperDB
     */
    private fun getIntentData() {
        repositoryDetailData = Paper.book().read(REPO_DETAIL_DATA, null)
        repositoryDetailData?.let { setUI(it) }
    }

    /**
     * Fill UI with data
     */
    private fun setUI(data: Item) {
        data.owner?.avatarUrl?.let { userAvatarImageView.setCircleImage(it) }
        repoNameTextView.setText(data.fullName)
        userNameTextView.setText("@" + data.owner?.login)
        defaultBranchTextView.setText(data.defaultBranch)
        languageTextView.setText(data.language)
        forkCountTextView.setText(data.forksCount.toString())
    }

    /**
     * Button Click actions
     */
    fun openUserDetailOnClickAction(view: View?) {
        when (view?.id) {
            R.id.userAvatarImageView -> {

                val intent = Intent(this, UserDetailActivity::class.java)
                intent.putExtra(USER_NAME, repositoryDetailData?.owner?.login)
                startActivity(intent)
//                startActivityModal(intentFor<UserDetailActivity>())
//                finishAffinity()
            }
        }
    }

}