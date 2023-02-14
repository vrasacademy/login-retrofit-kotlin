package com.vrasacademy.kotlinretrofit


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vrasacademy.kotlinretrofit.apis.ApiClient
import com.vrasacademy.kotlinretrofit.models.requests.LoginRequest
import com.vrasacademy.kotlinretrofit.models.responses.LoginResponse
import com.vrasacademy.kotlinretrofit.utils.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPrefManager: SharedPrefManager
    private var islogin: Boolean? = null
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var login:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email    = findViewById(R.id.email)
        password = findViewById(R.id.password)
        login    = findViewById(R.id.login)
        sharedPrefManager = SharedPrefManager(this);

        islogin  = sharedPrefManager.isLogin()
        if (islogin==true){
            startActivity(Intent(this@LoginActivity,MainActivity::class.java));
        }
        login.setOnClickListener{
            loginSubmit()
        }
    }

    private fun loginSubmit() {
        val email    = email.text.toString()
        val password = password.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty()){
           val loginRequest = LoginRequest(email,password)
           val api          = ApiClient.getApiService().Login(loginRequest)
            api.enqueue(object :Callback<LoginResponse>{
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if(response.code()==200){
//                        Log.d("DATA", response.body()?.response?.user?.name.toString());
                        sharedPrefManager.setId(response.body()?.response?.user?.id)
                        sharedPrefManager.setName(response.body()?.response?.user?.name.toString())
                        sharedPrefManager.setEmail(response.body()?.response?.user?.email.toString())
                        sharedPrefManager.setToken(response.body()?.response?.authorization?.token.toString())
                        sharedPrefManager.setTokenType(response.body()?.response?.authorization?.type.toString())
                        sharedPrefManager.setLogin(true)
                        showMessage(response.body()?.message.toString())
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java));
                    }else{
                        showMessage(response.body()?.message.toString())
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    showMessage("Login Gagal.")
                }

            })
        }else{
            showMessage("Email dan Password wajib diisi.")
        }

    }

    private fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}