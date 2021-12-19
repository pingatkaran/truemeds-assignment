package com.app.truemeds.network

import com.app.truemeds.model.TrueMedsModel
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @POST("getArticleListing")
    suspend fun getData(): TrueMedsModel
}