package com.example.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Job

class UpdateActivity : AppCompatActivity() {
    private lateinit var bookViewModel:BookViewModel
    private lateinit var editTitle: EditText
    private lateinit var editDesc: EditText
    private lateinit var editYearPub: EditText
    private lateinit var editAuthor: EditText
    private lateinit var title:String
    private lateinit var year:String
    private lateinit var author:String
    private lateinit var desc:String

    companion object{
        const val TITLE_KEY = "TITLE_KEY"
        const val AUTHOR_KEY = "AUTHOR_KEY"
        const val YEAR_KEY = "YEAR_KEY"
        const val DESC_KEY = "DESC_KEY"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val intent = intent
        val bookId : Int = intent.getIntExtra("id",0)
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        editTitle = findViewById(R.id.etBookTitleUpdate)
        editDesc = findViewById(R.id.etBookDescUpdate)
        editYearPub = findViewById(R.id.etBookYearPubUpdate)
        editAuthor = findViewById(R.id.etBookAuthorUpdate)

        val book : books = bookViewModel.search(bookId)

        editTitle.setText(book.name)
        editDesc.setText(book.description)
        editYearPub.setText(book.yearPub)
        editAuthor.setText(book.author)
        val submitButton = findViewById<Button>(R.id.updateButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        submitButton.setOnClickListener{

            title = editTitle.text.toString()
            year = editYearPub.text.toString()
            author = editAuthor.text.toString()
            desc = editDesc.text.toString()

            val book = books(id = bookId,name = title, yearPub = year, author = author,description = desc)
            bookViewModel.update(book)
            finish()
        }

        deleteButton.setOnClickListener {
            bookViewModel.delete(book)
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        title = editTitle.text.toString()
        year = editYearPub.text.toString()
        author = editAuthor.text.toString()
        desc = editDesc.text.toString()

        outState.putString(TITLE_KEY, title)
        outState.putString(DESC_KEY, desc)
        outState.putString(AUTHOR_KEY, author)
        outState.putString(YEAR_KEY, year)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.let {
            title = it.get(TITLE_KEY) as String
            desc = it.get(DESC_KEY) as String
            author = it.get(AUTHOR_KEY) as String
            year = it.get(YEAR_KEY) as String
            editTitle.setText(title)
            editYearPub.setText(year)
            editAuthor.setText(author)
            editDesc.setText(desc)
        }
    }
}