package com.app.truemeds.network

import com.app.truemeds.model.TrueMedsModel
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: APIService) {

    suspend fun getTrueMedsData(): TrueMedsModel = apiService.getData()
}