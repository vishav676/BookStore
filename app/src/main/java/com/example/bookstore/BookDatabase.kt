package com.example.bookstore

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers



@Database(entities = [books::class], version = 1)

abstract class BookDatabase : RoomDatabase(){
    abstract fun bookDao(): bookDAO

    private class BookDatabaseCallback(private val  scope: CoroutineScope):RoomDatabase.Callback() {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    database.bookDao()
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "book_database"
                ).addCallback(populateDb(context))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        private fun populateDb(context: Context): RoomDatabase.Callback {
            return object : Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Thread(Runnable {
                        var bookDao = getDatabase(context).bookDao()
                        for(book in dataToBeInserted()){
                            bookDao.insert(book)
                        }
                    }).start()
                }
            }
        }
        fun dataToBeInserted() = listOf<books>(
            books(id = 1, name = "Clean Code", author = "Rober Cecil", yearPub = "2008",description = "Even bad code can function. But if Code isn't clean"),
            books(id = 2,name = "The Pragmatic Programmer",author = "Andy Hunt and Dave Thomas",yearPub = "1999",description = "This is a book about computer programming and software engineering"),
            books(id = 3,name = "Design Patterns",author = "Erich Gamma",yearPub = "1994",description = "Design Patterns: Elements of Reusable Object-Oriented Software is a software engineering book describing software design patterns."),
            books(id = 4,name = "Head First Java",author = "Bert Bates",yearPub = "2003",description = " That's how your brain knows. And that's how your brain will learn Java."),
            books(id = 5,name = "Infinite Powers",author = "Steven Strogatz",yearPub = "2019",description = "Shortlisted for the Royal Society Science Book Prize 2019A magisterial history of calculus (and the people behind it) from one of the world's foremost mathematicians"),
            books(id = 6,name = "Euclid's Elements",author = "Euclid",yearPub = "300BC",description = "The Elements is a mathematical treatise consisting of 13 books attributed to the ancient Greek mathematician Euclid in Alexandria, Ptolemaic Egypt c. 300 BC. It is a collection of definitions, postulates, propositions, and mathematical proofs of the propositions."),
            books(id = 7,name = "A Man on the Moon",author = "Andrew Chaikin",yearPub = "1994",description = "A Man on the Moon: The Voyages of the Apollo Astronauts is a book by Andrew Chaikin, first published in 1994. It describes the voyages of the Apollo program astronauts in detail, from Apollo 8 to 17."),
            books(id = 8,name = "The order of Time",author = "Carlo Rovelli",yearPub = "2017",description = "The Order of Time is a book by Italian physicist Carlo Rovelli. It is about time in physics. Carlo Rovelli is the leading scientist who writes about \"warped time\" and at the forefront of physicist who are trying to unify theory of relativity and loop quantum gravity theory"),
            books(id = 9,name = "The Grand Design",author = "Stefan Hawking",yearPub = "2010",description = "The Grand Design is a popular-science book written by physicists Stephen Hawking and Leonard Mlodinow and published by Bantam Books in 2010. The book examines the history of scientific knowledge about the universe and explains eleven-dimensional M-theory."),
            books(id = 10,name = "Culinary Reactions",author = "Simon Quellen",yearPub = "2011",description = "When you're cooking, you're a chemist! Every time you follow or modify a recipe, you are experimenting with acids and bases, emulsions and suspensions, gels and foams."),
            books(id = 11,name = "Gulliver's travels",author = "Jonathan Swift",yearPub = "1726",description = "Gulliver's Travels, or Travels into Several Remote Nations of the World. In Four Parts. By Lemuel Gulliver, First a Surgeon, and then a Captain of Several Ships is a 1726 prose satire by the Irish writer and clergyman Jonathan Swift, satirising both human nature and the \"travellers' tales\" literary subgenre."),
            books(id = 12,name = "The Story of My Life",author = "Helen Keller",yearPub = "1938",description = "The Story of My Life, first published in 1903, is Helen Keller's autobiography detailing her early life, especially her experiences with Anne Sullivan."),
        )
    }



}