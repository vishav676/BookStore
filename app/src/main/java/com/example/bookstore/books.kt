package com.example.bookstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "BookTable")
data class books(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "BookId") var id:Int,
                 @ColumnInfo(name="Title") var name: String,
                 @ColumnInfo(name="YearPub") var yearPub: String,
                 @ColumnInfo(name="Author") var author: String,
                 @ColumnInfo(name="Description") var description: String) {

}