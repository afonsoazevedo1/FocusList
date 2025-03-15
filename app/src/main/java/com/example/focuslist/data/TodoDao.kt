package com.example.focuslist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TodoEntity)

    @Delete
    suspend fun delete(entity: TodoEntity)

    @Query("Select * FROM todos")
    fun getAll(): Flow<List<TodoEntity>>

    @Query("Select * FROM todos WHERE id = :id")
    suspend fun getBy(id: Long): TodoEntity?
}