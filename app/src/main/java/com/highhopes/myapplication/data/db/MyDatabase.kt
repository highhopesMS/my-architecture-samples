package com.highhopes.myapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.highhopes.myapplication.data.model.User

/**
 * The Room Database that contains the User table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
