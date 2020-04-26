package com.highhopes.myapplication.di.di

import retrofit2.http.GET

interface UserApi {
    @GET("highhopesMS/my-architecture-samples/master/user.json")
    suspend fun loadUser(): User
}

data class User(val id: Long, val name: String, val lastName: String, val accountName: String)
