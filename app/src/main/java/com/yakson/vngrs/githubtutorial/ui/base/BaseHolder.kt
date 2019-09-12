package com.yakson.vngrs.githubtutorial.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.yakson.vngrs.githubtutorial.BR

class BaseHolder<M>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    /**
     * Set data to layout
     * @param data -> Model object
     */
    fun doBindings(data: M) {
        binding.setVariable(BR._all, data)
        binding.executePendingBindings()
    }
}