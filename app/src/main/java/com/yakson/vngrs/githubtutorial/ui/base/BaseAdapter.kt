package com.yakson.vngrs.githubtutorial.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAdapter<M>(diffCallback: DiffUtil.ItemCallback<M>) :
    ListAdapter<M, BaseHolder<M>>(diffCallback) {

    override fun onBindViewHolder(holder: BaseHolder<M>, position: Int) {
        val item = getItem(position)
        holder.doBindings(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<M> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return BaseHolder(binding)
    }

    fun getRowItem(position: Int): M {
        return getItem(position)
    }

    abstract override fun getItemViewType(position: Int): Int
}