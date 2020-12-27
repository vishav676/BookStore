package com.example.bookstore

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.bookstore.Database.BookDatabase
import com.example.bookstore.Database.bookDAO
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class BookDbTest {
    private lateinit var bookDAO: bookDAO
    private lateinit var db: BookDatabase
    private lateinit var myBook:books

    @Before
    fun insertData(){

        var context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        bookDAO = db.bookDao()
        myBook = books(author = "Williams", description = "Novel", yearPub = "1985", name = "Friends", id = 2)
        bookDAO.insert(myBook)
    }

    @After
    @Throws(IOException::class)
    fun removeConnection(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun getAllBooks(){
        val allBooks = bookDAO.getAllBooks()

        assertNotNull(allBooks)
    }

    @Test
    @Throws(Exception::class)
    fun testInsert(){

        val book  = books(author = "Robert Froset", description = "Peom", yearPub = "1999", name = "Road Not Taken", id = 100)
        bookDAO.insert(book)
        val getBook = bookDAO.search(100)
        Assert.assertNotNull(getBook)

        Assert.assertEquals(book, getBook)
        bookDAO.delete(book)
    }

    @Test
    @Throws(Exception:: class)
    fun testDelete(){
        bookDAO.delete(myBook)
        val getBook = bookDAO.search(100)
        Assert.assertEquals(null,getBook)
    }
    @Test
    @Throws(Exception::class)
    fun testUpdate(){
        val book = books(author = "Taylor", description = "Novel", yearPub = "1970", name = "Friends", id = 2)
        bookDAO.update(book)
        val getBook = bookDAO.search(2)
        Assert.assertEquals(book, getBook)
        Assert.assertNotEquals(myBook, getBook)
    }
}