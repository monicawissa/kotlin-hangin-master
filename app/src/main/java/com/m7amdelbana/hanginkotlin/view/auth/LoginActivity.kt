package com.m7amdelbana.hanginkotlin.view.auth

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.JsonObject
import com.m7amdelbana.hanginkotlin.R
import com.m7amdelbana.hanginkotlin.network.model.Place
import com.m7amdelbana.hanginkotlin.network.model.PlacesEndPoint
import com.m7amdelbana.hanginkotlin.network.model.client
import com.m7amdelbana.hanginkotlin.network.model.token
import com.m7amdelbana.hanginkotlin.view.main.MainActivity
import com.m7amdelbana.hanginkotlin.view.main.PlaceAdapter
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.absoluteValue
import kotlin.math.floor
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    lateinit var email:EditText
    lateinit var password:TextInputLayout
    lateinit var Signup_tv:TextView
    lateinit var forget_tv:TextView

    lateinit var login_button:Button
    lateinit var google_button:Button
    lateinit var face_button:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email=findViewById(R.id.login_email_editText)
        password=findViewById(R.id.login_password_editText)

        Signup_tv=findViewById(R.id.login_new_account_text_view)
        forget_tv=findViewById(R.id.login_forget_password_text_view)

        login_button=findViewById(R.id.login_button)
        google_button=findViewById(R.id.login_google_button)
        face_button=findViewById(R.id.login_facebook_button)

    }

    fun sign_up(view: View) {
        val intent=Intent(this,Registration::class.java)
        startActivity(intent)
        finish()

    }

    fun login(view: View) {
        if(email.getText().toString().equals("")||password.editText!!.getText().toString().equals("")){
            Toast.makeText(this,"enter your email and password",Toast.LENGTH_LONG).show()
            return
        }
        val service =client.retrofit!!.create(PlacesEndPoint::class.java)
        val json=JsonObject()
        json.addProperty("email",email.getText().toString())
        json.addProperty("password",password.editText!!.getText().toString())
        val call=service.login(json)
        call.enqueue(object:Callback<token>{
            override fun onFailure(call: Call<token>, t: Throwable) {
                Toast.makeText(this@LoginActivity,"something went wrong",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<token>, response: Response<token>) {
                if(response.code()==200){
                    val s:String?=response.body()!!.x_auth_token
                    Log.d("Tagggg","token"+s)

                    val intent=Intent(this@LoginActivity,MainActivity::class.java)
                    intent.putExtra("x-auth-token",s)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this@LoginActivity,"invalid email or password",Toast.LENGTH_LONG).show()
                }

            }

        })
    }

    fun forget_your_password(view: View) {

    }
    fun login_facebook(view: View) {

    }
    fun login_google(view: View) {

    }
}
