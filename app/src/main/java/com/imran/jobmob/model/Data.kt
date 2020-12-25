package com.imran.jobmob.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Data(
    @SerializedName("IsFeatured")
    val isFeatured: Boolean,
    val deadline: String,
    val jobDetails: JobDetails,
    val jobTitle: String,
    val logo: String,
    val maxExperience: Int,
    val maxSalary: String,
    val minExperience: Int,
    val minSalary: String,
    @SerializedName("recruitingCompany'sProfile")
    val recruitingCompanyProfile: String
) : Serializable