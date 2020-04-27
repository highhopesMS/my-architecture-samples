package com.highhopes.myapplication.data.api

import com.highhopes.myapplication.data.model.User
import retrofit2.http.GET

interface UserApi {
    @GET("highhopesMS/my-architecture-samples/master/user.json")
    suspend fun loadUser(): User
}

