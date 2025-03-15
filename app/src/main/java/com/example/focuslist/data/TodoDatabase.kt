package com.example.focuslist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [TodoEntity::class],
    version = 1,
)
abstract class TodoDatabase: RoomDatabase() {
    abstract val todoDao: TodoDao
}

object TodoDataProvider {
    @Volatile
    private var INSTANCE: TodoDatabase? = null

    fun provide(context: Context): TodoDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java,
                "focuList-app"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}