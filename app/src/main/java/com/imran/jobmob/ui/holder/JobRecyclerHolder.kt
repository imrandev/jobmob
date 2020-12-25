package com.imran.jobmob.ui.holder

import android.graphics.Color
import android.view.View
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.imran.jobmob.R
import com.imran.jobmob.databinding.RvItemJobBinding
import com.imran.jobmob.model.Data
import com.imran.jobmob.ui.adapter.BaseViewHolder
import com.imran.jobmob.ui.adapter.IBaseClickListener

/**
 * Created by Imran Khan on 12/25/2020.
 * Email : context.imran@gmail.com
 */

class JobRecyclerHolder(viewDataBinding: ViewDataBinding) : BaseViewHolder<Data, IBaseClickListener<Data>>(viewDataBinding) {

    private var rvItemJobBinding = if(viewDataBinding is RvItemJobBinding) viewDataBinding as RvItemJobBinding else null

    override fun onBindView() {

    }

    override fun onBindView(`object`: Data) {

    }

    override fun onBindView(`object`: Data, onItemClickedListener: IBaseClickListener<Data>) {

        if (rvItemJobBinding != null){
            Glide.with(itemView)
                .asDrawable().load(`object`.logo)
                .into(rvItemJobBinding!!.ivItemLogo)

            rvItemJobBinding!!.tvItemJobTitle.text = `object`.jobTitle
            rvItemJobBinding!!.ivItemFeatured.visibility = if (`object`.isFeatured) View.VISIBLE else View.GONE
            rvItemJobBinding!!.tvItemFeatured.visibility = if (`object`.isFeatured) View.VISIBLE else View.GONE
            rvItemJobBinding!!.cardView.setCardBackgroundColor(if (`object`.isFeatured) Color.parseColor("#e8f3db") else Color.WHITE)

            var salaryRange = "Negotiable"

            if (`object`.minSalary.isNotBlank() && `object`.maxSalary.isNotBlank()){
                salaryRange = String.format("%s - %s ৳", `object`.minSalary, `object`.maxSalary)
            } else if (`object`.minSalary.isNotBlank() && `object`.maxSalary.isBlank()){
                salaryRange = String.format("%s ৳", `object`.minSalary)
            } else if (`object`.maxSalary.isNotBlank() && `object`.minSalary.isBlank()){
                salaryRange = String.format("%s ৳", `object`.maxSalary)
            }

            rvItemJobBinding!!.tvItemJobSalaryRange.text = salaryRange
            attachListenerWithCustomView(rvItemJobBinding!!.ivArrowForward, onItemClickedListener, `object`)
        }
    }

    override fun onViewRecycled() {

    }

}