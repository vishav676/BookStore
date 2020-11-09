package com.example.bookstore.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var bookViewModel:BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_books, container, false)
        val lvBooks: RecyclerView = root.findViewById(R.id.lvBooks)
        val adapter = activity?.applicationContext?.let { bookAdapter(it) }
        lvBooks.adapter = adapter

        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        lvBooks.layoutManager = LinearLayoutManager(activity?.applicationContext)
        bookViewModel.insert(books(1,"Vishav","vishav","Vishav","vishav"))


        bookViewModel.allBooks.observe(viewLifecycleOwner, Observer { books ->
            books?.let { adapter?.setBooks(it) }
        })

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }
}