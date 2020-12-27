package com.example.bookstore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bookstore.Database.BookDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BookViewModel(application: Application) : AndroidViewModel(application){




    val bookDao = BookDatabase.getDatabase(application).bookDao()
    val allBooks: LiveData<List<books>> = bookDao.getAllBooks()

    fun insert(book:books) = viewModelScope.launch ( Dispatchers.IO )
    {
        bookDao.insert(book)
    }
    fun update(book:books) = viewModelScope.launch ( Dispatchers.IO )
    {
        bookDao.update(book)
    }

    fun search(bookId: Int) = runBlocking {
        return@runBlocking bookDao.search(bookId)
    }

    fun delete(book:books) = viewModelScope.launch ( Dispatchers.IO )
    {
        bookDao.delete(book)
    }
}