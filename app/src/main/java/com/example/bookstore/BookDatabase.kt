package com.example.bookstore

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers



@Database(entities = [books::class], version = 1)

abstract class BookDatabase : RoomDatabase(){
    abstract fun bookDao(): bookDAO

    private class BookDatabaseCallback(private val  scope: CoroutineScope):RoomDatabase.Callback() {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var bookDao = database.bookDao()


                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): BookDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "book_database"
                ).addCallback(BookDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}