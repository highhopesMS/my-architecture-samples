package com.highhopes.myapplication.di.di

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.highhopes.myapplication.data.model.User

/**
 * Data Access Object for the user table.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user: User)

    @Query("SELECT * FROM Users")
    fun observeUsers(): LiveData<List<User>>

}
