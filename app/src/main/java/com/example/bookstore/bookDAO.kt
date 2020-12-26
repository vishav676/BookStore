package com.example.bookstore

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface bookDAO {

    @Query("SELECT * from BookTable ORDER BY Title ASC")
    fun getAllBooks():LiveData<List<books>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(book:books)

    @Query("DELETE FROM BookTable")
    fun deleteAll()

    @Delete
    fun delete(model: books)

    @Query("SELECT * from booktable WHERE booktable.BookId = :id")
    fun search(id: Int) : books

    @Update
    fun update(model: books)
}