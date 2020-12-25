package com.imran.jobmob.network

import com.imran.jobmob.model.Jobs
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Imran Khan on 12/25/2020.
 * Email : context.imran@gmail.com
 */

interface ApiRepository {

    @GET("/interviewtest/InterviewJson.json")
    fun findJobs(): Call<Jobs>
}