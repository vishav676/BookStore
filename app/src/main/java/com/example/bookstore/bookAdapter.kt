package com.example.bookstore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class bookAdapter internal constructor(context: Context): RecyclerView.Adapter<bookAdapter.bookViewHolder>(){

    private val infalter: LayoutInflater = LayoutInflater.from(context)
    private var bookList = emptyList<books>()

    inner class bookViewHolder(bookView: View):RecyclerView.ViewHolder(bookView){
        val bookItemView: TextView = bookView.findViewById(R.id.tvBooks)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bookAdapter.bookViewHolder {
        val bookView = infalter.inflate(R.layout.books_item,parent,false)
        return bookViewHolder(bookView)
    }

    override fun onBindViewHolder(holder: bookAdapter.bookViewHolder, position: Int) {
        val current = bookList[position]
        holder.bookItemView.text = current.name
    }

    internal fun setBooks(bookList : List<books>){
        this.bookList = bookList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

}