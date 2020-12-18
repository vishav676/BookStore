package com.example.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider

class UpdateActivity : AppCompatActivity() {
    private lateinit var bookViewModel:BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        var intent = intent
        var bookId : Int? = intent.getIntExtra("id",0)
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        val editTitle = findViewById<EditText>(R.id.etBookTitleUpdate);
        val editDesc = findViewById<EditText>(R.id.etBookDescUpdate);
        val editYearPub = findViewById<EditText>(R.id.etBookYearPubUpdate);
        val editAuthor = findViewById<EditText>(R.id.etBookAuthorUpdate);

        var book : books? = bookId?.let { bookViewModel.search(it) };

        editTitle.setText(book?.name)
        editDesc.setText(book?.description)
        editYearPub.setText(book?.yearPub)
        editAuthor.setText(book?.author)
        val submitButton = findViewById<Button>(R.id.updateButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        submitButton.setOnClickListener{

            val title = editTitle.text.toString()
            val year = editYearPub.text.toString()
            val author = editAuthor.text.toString()
            val desc = editDesc.text.toString()

            val book = bookId?.let { it1 -> books(id = it1,name = title, yearPub = year, author = author,description = desc) }
            if (book != null) {
                bookViewModel.update(book)
            }
            finish()
        }

        deleteButton.setOnClickListener {
            if (book != null) {
                bookViewModel.delete(book)
            }
        }
    }
}