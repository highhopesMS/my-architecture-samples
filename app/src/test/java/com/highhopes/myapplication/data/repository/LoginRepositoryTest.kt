package com.highhopes.myapplication.data.repository

import com.highhopes.myapplication.data.api.UserApi
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class LoginRepositoryTest {

    lateinit var loginRepository : LoginRepository
    lateinit var localUserDataSource: LocalUserDataSource
    lateinit var userApi: UserApi

    @Before
    fun setUp() {
        loginRepository = LoginRepository(localUserDataSource, userApi)
    }

    @Test
    fun login() {

    }
}