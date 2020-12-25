package com.imran.jobmob.model

import java.io.Serializable

data class Jobs(
    val common: Common,
    val `data`: List<Data>,
    val message: String,
    val statuscode: String
)