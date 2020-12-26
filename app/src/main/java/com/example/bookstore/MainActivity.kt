package com.example.bookstore

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import org.w3c.dom.Text
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btLogin : Button
    private val sharedFile = "shareFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPassword = findViewById(R.id.password)
        etUsername = findViewById(R.id.username)
        btLogin = findViewById(R.id.login)
        val sharedPreferences : SharedPreferences = this.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        if(sharedPreferences.getInt("loggedIn",0) == 1){
            val intent  = Intent(this, bookActivity::class.java)
            startActivity(intent)
            finish()
        }
        btLogin.setOnClickListener {
            if(TextUtils.isEmpty(etUsername.text)){
                etUsername.error = "Please Enter Username"
            }
            if(TextUtils.isEmpty(etPassword.text)){
                etPassword.error = "Please Enter Password"
            }
            if(etUsername.text.toString() == "admin" && etPassword.text.toString() == "admin"){
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putInt("loggedIn",1)
                editor.apply()
                editor.commit()
                val intent  = Intent(this, bookActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}