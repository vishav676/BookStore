package com.example.bookstore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application){




    val bookDao = BookDatabase.getDatabase(application, viewModelScope).bookDao()
    val allBooks: LiveData<List<books>> = bookDao.getAllBooks()

    fun insert(book:books) = viewModelScope.launch ( Dispatchers.IO )
    {
        bookDao.insert(book)
    }
}