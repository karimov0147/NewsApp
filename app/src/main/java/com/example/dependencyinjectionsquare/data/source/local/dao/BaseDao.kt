package com.example.dependencyinjectionsquare.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface BaseDao<T>{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t : T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(t : List<T>)

    @Delete
    fun delete(t : T)

}