package com.imran.jobmob.ui.job

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import com.bumptech.glide.Glide
import com.imran.jobmob.R
import com.imran.jobmob.databinding.FragmentJobInfoBinding
import com.imran.jobmob.model.Data
import com.imran.jobmob.ui.base.BaseFragment
import com.imran.jobmob.utils.DateUtil


class JobInfoFragment : BaseFragment<FragmentJobInfoBinding>() {

    private lateinit var data: Data

    private lateinit var fragmentJobInfoBinding: FragmentJobInfoBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getSerializable("data") as Data
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateView(data)
    }

    private fun populateView(data: Data) {

        Glide.with(requireContext())
            .asDrawable()
            .load(data.logo)
            .into(fragmentJobInfoBinding.ivLogo)

        fragmentJobInfoBinding.tvJobTitle.text = data.jobTitle
        fragmentJobInfoBinding.ivItemFeatured.visibility = if (data.isFeatured) View.VISIBLE else View.GONE
        fragmentJobInfoBinding.tvItemFeatured.visibility = if (data.isFeatured) View.VISIBLE else View.GONE

        var salaryRange = "Negotiable"

        if (data.minSalary.isNotBlank() && data.maxSalary.isNotBlank()){
            salaryRange = String.format("%s - %s ৳", data.minSalary, data.maxSalary)
        } else if (data.minSalary.isNotBlank() && data.maxSalary.isBlank()){
            salaryRange = String.format("%s ৳", data.minSalary)
        } else if (data.maxSalary.isNotBlank() && data.minSalary.isBlank()){
            salaryRange = String.format("%s ৳", data.maxSalary)
        }
        fragmentJobInfoBinding.tvItemJobSalaryRange.text = salaryRange

        fragmentJobInfoBinding.tvCompanyName.text =
            if (data.jobDetails.CompanyName.isBlank()) "Not found" else data.jobDetails.CompanyName

        val profile = SpannableString(data.recruitingCompanyProfile)
        profile.setSpan(UnderlineSpan(), 0, profile.length, 0)
        fragmentJobInfoBinding.tvProfileName.text =  if (profile.isBlank()) "Not found" else profile

        var experience = "Not Required"

        if (data.minExperience > 0 && data.maxExperience > 0){
            experience = String.format("%s - %s Year(s)", data.minExperience, data.maxExperience)
        } else if (data.minExperience > 0 && data.maxExperience == 0){
            experience = String.format("%s Year(s)", data.minExperience)
        } else if (data.maxExperience > 0 && data.minExperience == 0){
            experience = String.format("%s Year(s)", data.maxExperience)
        }

        val deadline = DateUtil.getFormattedDate(data.deadline)
        fragmentJobInfoBinding.tvDeadline.text = deadline

        fragmentJobInfoBinding.tvExperience.text = experience;
        fragmentJobInfoBinding.tvApplyInstruction

        val applyInstructions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String.format(
                "%s", Html.fromHtml(
                    data.jobDetails.ApplyInstruction,
                    Html.FROM_HTML_MODE_LEGACY
                )
            )
        } else {
            String.format("%s", Html.fromHtml(data.jobDetails.ApplyInstruction))
        }

        fragmentJobInfoBinding.tvApplyInstruction.text = applyInstructions
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_job_info
    }

    override fun intViewBinding(viewBinding: FragmentJobInfoBinding) {
        this.fragmentJobInfoBinding = viewBinding;
    }
}