package com.yakson.vngrs.githubtutorial.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.yakson.vngrs.githubtutorial.R
import com.yakson.vngrs.githubtutorial.utils.customview.CustomLoadingDialog
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseActivity<VM : ViewModel, DB : ViewDataBinding> : DaggerAppCompatActivity() {

    private lateinit var mCustomLoadingDialog: CustomLoadingDialog
    private lateinit var viewModel: VM
    private lateinit var binding: DB

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * Returns ViewModel class type
     */
    protected abstract fun getViewModelClass(): KClass<VM>

    /**
     * Layout resource id
     */
    abstract fun getLayoutId(): Int

    /**
     * Returns Current ViewModel Instance
     */
    fun getViewModel(): VM {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCustomLoadingDialog = CustomLoadingDialog(this, R.style.LoadingDialogStyle)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass().java)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        // Initialize UI
        prepareView(savedInstanceState)
    }

    /**
     * Prepare UI Components here
     */
    abstract fun prepareView(savedInstanceState: Bundle?)

    /**
     * Show Progress Dialog
     */
    fun showProgressDialog() {
        try {
            if (!mCustomLoadingDialog.isShowing) {
                mCustomLoadingDialog.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Hide Progress Dialog
     */
    fun hideProgressDialog() {
        try {
            if (mCustomLoadingDialog.isShowing)
                mCustomLoadingDialog.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}