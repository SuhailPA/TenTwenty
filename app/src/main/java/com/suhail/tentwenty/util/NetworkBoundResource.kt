package com.suhail.tentwenty.util

import android.util.Log
import kotlinx.coroutines.flow.*

inline fun <localDb, ApiData> networkBoundResource(
    crossinline query: () -> Flow<localDb>,
    crossinline fetch: suspend () -> ApiData,
    crossinline saveFetchResult: suspend (ApiData) -> Unit,
    crossinline shouldFetch: (localDb) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {

        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (e: Exception) {
            Log.i("Exceptions", e.toString())
            query().map { Resource.Error(it, e.toString()) }
        }

    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)

}