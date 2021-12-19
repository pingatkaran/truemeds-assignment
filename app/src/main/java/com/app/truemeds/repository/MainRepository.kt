package com.app.truemeds.repository

import com.app.truemeds.model.TrueMedsModel
import com.app.truemeds.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiServiceImpl)  {

    fun getDetails(): Flow<TrueMedsModel> = flow {
        emit(apiService.getTrueMedsData())
    }.flowOn(Dispatchers.IO)

}