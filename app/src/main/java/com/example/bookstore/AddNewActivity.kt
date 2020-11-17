package com.example.bookstore

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider

class AddNewActivity : AppCompatActivity() {

    private lateinit var bookViewModel:BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)

        val editTitle = findViewById<EditText>(R.id.etBookTitle);
        val editDesc = findViewById<EditText>(R.id.etBookDesc);
        val editYearPub = findViewById<EditText>(R.id.etBookYearPub);
        val editAuthor = findViewById<EditText>(R.id.etBookAuthor);

        val submitButton = findViewById<Button>(R.id.addButton)
        submitButton.setOnClickListener{

            val title = editTitle.text.toString()
            val year = editYearPub.text.toString()
            val author = editAuthor.text.toString()
            val desc = editDesc.text.toString()

            val book = books(id = 0,name = title, yearPub = year, author = author,description = desc)
            bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
            bookViewModel.insert(book)
            finish()
        }

    }
}