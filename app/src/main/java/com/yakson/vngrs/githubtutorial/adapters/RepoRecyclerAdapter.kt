package com.yakson.vngrs.githubtutorial.adapters



import com.yakson.vngrs.githubtutorial.R
import com.yakson.vngrs.githubtutorial.model.Item
import com.yakson.vngrs.githubtutorial.network.diff.RepoDiffCallback
import com.yakson.vngrs.githubtutorial.ui.base.BaseAdapter
import com.yakson.vngrs.githubtutorial.ui.base.BaseHolder
import com.yakson.vngrs.githubtutorial.utils.extension.setCircleImage
import kotlinx.android.synthetic.main.row_repo_recycler.view.*

class RepoRecyclerAdapter : BaseAdapter<Item>(RepoDiffCallback()) {


    override fun getItemViewType(position: Int): Int {
        return R.layout.row_repo_recycler
    }


    override fun onBindViewHolder(holder: BaseHolder<Item>, position: Int) {
        val item = getItem(position)

        holder.itemView.repoNameTextView.setText(item.fullName)
        holder.itemView.userNameTextView.setText(item.owner?.login)
        item.owner?.avatarUrl?.let { holder.itemView.userAvatarImageView.setCircleImage(it) }

    }

}