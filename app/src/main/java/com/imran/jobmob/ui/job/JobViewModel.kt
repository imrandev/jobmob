package com.imran.jobmob.ui.job

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imran.jobmob.model.Jobs
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Imran Khan on 12/25/2020.
 * Email : context.imran@gmail.com
 */

class JobViewModel (application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    fun findJobs() : MutableLiveData<Jobs> {
        var jobs = MutableLiveData<Jobs>()
        viewModelScope.launch {
            JobRepository.instance.findJobs(context).collect { items ->
                run {
                    jobs = items
                }
            }
        }
        return jobs
    }
}