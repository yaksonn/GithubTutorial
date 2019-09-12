package com.yakson.vngrs.githubtutorial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yakson.vngrs.githubtutorial.BR
import com.yakson.vngrs.githubtutorial.R
import com.yakson.vngrs.githubtutorial.databinding.RowRepoRecyclerBinding
import com.yakson.vngrs.githubtutorial.model.Item
import com.yakson.vngrs.githubtutorial.utils.extension.setCircleImage

class SearchRepoRecyclerAdapter(
    private val itemClickListener: (Item?) -> Unit,
    private val clickListener: (Item) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: ArrayList<Item?> = arrayListOf()

    override fun getItemViewType(position: Int): Int {
        return R.layout.row_repo_recycler
    }

    fun setData(data: ArrayList<Item?>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val rowTransactionBinding =
            DataBindingUtil.inflate<RowRepoRecyclerBinding>(
                inflater,
                R.layout.row_repo_recycler,
                parent,
                false
            )
        return ViewHolder(rowTransactionBinding)
    }

    override fun getItemCount(): Int {
        return if (data.isEmpty()) {
            0
        } else {
            data.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        if (holder is ViewHolder) {
            item?.let { holder.doBindings(it) }
            item?.let { holder.bind(it) }
        }
    }

    internal inner class ViewHolder(val binding: RowRepoRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun doBindings(data: Item) {
            binding.setVariable(BR.data, data)
            binding.executePendingBindings()
        }

        fun bind(data: Item) {
            binding.userNameTextView.setText("@" + data.owner?.login)
            data.fullName?.let { binding.repoNameTextView.text = it }
            data.owner?.avatarUrl?.let { binding.userAvatarImageView.setCircleImage(it) }

            binding.linearLayout.setOnClickListener {
                clickListener(data)
            }

            binding.userAvatarImageView.setOnClickListener {
                itemClickListener(data)
            }
        }
    }
}