package com.example.bookstore.ui.books

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.*

class HomeFragment : Fragment() {

    private lateinit var bookViewModel:BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_books, container, false)
        val lvBooks: RecyclerView = root.findViewById(R.id.lvBooks)
        val adapter = activity?.applicationContext?.let { bookAdapter(it) { item ->
                var intent = Intent(requireActivity(), UpdateActivity::class.java)
                intent.putExtra("id", item.id)
                startActivity(intent)
            }
        }
        lvBooks.adapter = adapter

        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        lvBooks.layoutManager = LinearLayoutManager(activity?.applicationContext)


        bookViewModel.allBooks.observe(viewLifecycleOwner, Observer { books ->
            books?.let { adapter?.setBooks(it) }
        })

        return root
    }
}