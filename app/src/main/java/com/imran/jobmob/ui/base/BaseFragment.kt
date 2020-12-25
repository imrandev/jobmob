package com.imran.jobmob.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.imran.jobmob.ui.job.JobViewModel

/**
 * Created by Imran Khan on 12/25/2020.
 * Email : context.imran@gmail.com
 */

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    protected lateinit var fragmentContext : Context

    protected lateinit var jobViewModel: JobViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.fragmentContext = context
    }

    @LayoutRes
    protected abstract fun getLayoutRes() : Int
    protected abstract fun intViewBinding(viewBinding: V)
    protected open fun isFullWindow(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        jobViewModel = ViewModelProvider(this).get(JobViewModel::class.java)

        val actionBar: ActionBar? = (activity as AppCompatActivity).supportActionBar
        if (isFullWindow()) {
            (activity as AppCompatActivity).window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            actionBar?.hide()
        } else {
            (activity as AppCompatActivity).window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            actionBar?.show()
        }

        val viewDataBinding = DataBindingUtil.inflate<V>(inflater, getLayoutRes(), container, false)
        intViewBinding(viewDataBinding)
        return viewDataBinding.root
    }
}