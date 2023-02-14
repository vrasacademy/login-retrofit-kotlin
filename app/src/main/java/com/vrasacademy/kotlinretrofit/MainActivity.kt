package com.vrasacademy.kotlinretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vrasacademy.kotlinretrofit.utils.SharedPrefManager

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPrefManager: SharedPrefManager
    var islogin: Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPrefManager = SharedPrefManager(this)
        islogin           = sharedPrefManager.isLogin()
        if (islogin==false){
            startActivity(Intent(this@MainActivity,LoginActivity::class.java));
        }
    }
}