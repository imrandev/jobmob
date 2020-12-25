package com.imran.jobmob.ui.job

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.imran.jobmob.model.Jobs
import com.imran.jobmob.network.EnqueueResponse
import com.imran.jobmob.network.RetrofitClient
import com.imran.jobmob.utils.Constant
import kotlinx.coroutines.flow.flow


/**
 * Created by Imran Khan on 12/25/2020.
 * Email : context.imran@gmail.com
 */

class JobRepository {

    fun findJobs(context: Context) = flow<MutableLiveData<Jobs>> {
        emit(fetchJobsFromApi(context))
    }

    private fun fetchJobsFromApi(context: Context) : MutableLiveData<Jobs> {
        val data = MutableLiveData<Jobs>()
        RetrofitClient.getInstance(Constant.BASE_URL, context).findJobs().enqueue(object :
            EnqueueResponse<Jobs>() {
            override fun onReceived(body: Jobs?, message: String?) {
                body.let {
                    data.value = body
                }
                Log.d(TAG, "onReceived: $message")
            }

            override fun onError(message: String?, code: Int) {
                Log.d(TAG, "onError: $message")
            }

            override fun onFailed(message: String?) {
                Log.d(TAG, "onFailed: $message")
            }
        })
        return data
    }

    companion object {
        private const val TAG = "JobRepository"
        var instance = JobRepository()
    }
}