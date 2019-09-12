package com.yakson.vngrs.githubtutorial.network.diff

import androidx.recyclerview.widget.DiffUtil
import com.yakson.vngrs.githubtutorial.model.Item


class RepoDiffCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return false
    }
}