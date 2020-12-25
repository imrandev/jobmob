package com.imran.jobmob.ui.job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imran.jobmob.R
import com.imran.jobmob.databinding.FragmentJobsBinding
import com.imran.jobmob.databinding.RvItemJobBinding
import com.imran.jobmob.model.Data
import com.imran.jobmob.ui.adapter.BaseRecyclerAdapter
import com.imran.jobmob.ui.adapter.BaseViewHolder
import com.imran.jobmob.ui.adapter.IBaseClickListener
import com.imran.jobmob.ui.base.BaseFragment
import com.imran.jobmob.ui.holder.JobRecyclerHolder

class JobsFragment : BaseFragment<FragmentJobsBinding>() {

    private lateinit var fragmentJobsBinding: FragmentJobsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val baseRecyclerAdapter = object : BaseRecyclerAdapter<Data, IBaseClickListener<Data>>(null, jobItemClickListener){
            override fun onCreateView(parent: ViewGroup?, viewType: Int): BaseViewHolder<Data, IBaseClickListener<Data>> {
                return JobRecyclerHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent?.context), R.layout.rv_item_job, parent, false))
            }

            override fun areSameItems(oldItem: Data?, newItem: Data?): Boolean {
                return oldItem?.logo == newItem?.logo
            }

            override fun areSameContents(oldItem: Data?, newItem: Data?): Boolean {
                return oldItem == newItem
            }

            override fun onCreateEmptyView(
                parent: ViewGroup?,
                viewType: Int
            ): BaseViewHolder<Data, IBaseClickListener<Data>> {
                return JobRecyclerHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent?.context), R.layout.rv_item_empty, parent, false))
            }
        }

        baseRecyclerAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        fragmentJobsBinding.rvJobs.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            adapter = baseRecyclerAdapter
        }

        jobViewModel.findJobs().observe(viewLifecycleOwner){
            it.let {
                baseRecyclerAdapter.update(it.data.toMutableList())
            }
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_jobs
    }

    override fun intViewBinding(viewBinding: FragmentJobsBinding) {
        this.fragmentJobsBinding = viewBinding;
    }

    val jobItemClickListener = object : IBaseClickListener<Data> {
        override fun onItemClicked(view: View?, item: Data, position: Int) {
            val extras = Bundle()
            extras.putSerializable("data", item)
            val navController = Navigation.findNavController(activity!!, R.id.navHostFragment)
            navController.navigate(R.id.action_jobsFragment_to_jobInfoFragment, extras)
        }
    }
}