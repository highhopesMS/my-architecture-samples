package com.highhopes.myapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Immutable model class for a User. In order to compile with Room, we can't use @JvmOverloads to
 * generate multiple constructors.
 *
 * @param id          id of the user
 * @param name        name of the user
 * @param lastName    last name of the user
 * @param accountName account name of the user
 */
@Entity(tableName = "Users")
data class User @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = "entryid") var id: Int,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "lastName") var lastName: String = "",
    @ColumnInfo(name = "accountName") var accountName: String = ""

)