package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.model.PicsumPhoto
import com.example.myapplication.data.remote.model.PicsumPhotoDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PicsumApiService {

    @GET("v2/list")
    suspend fun getPhotoList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<PicsumPhoto>

    @GET("id/{id}/info")
    suspend fun getPhotoDetail(
        @Path("id") id: String
    ): PicsumPhotoDetail
}

