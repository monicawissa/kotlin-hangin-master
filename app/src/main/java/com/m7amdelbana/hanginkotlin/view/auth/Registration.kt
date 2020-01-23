package com.m7amdelbana.hanginkotlin.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.JsonObject
import com.m7amdelbana.hanginkotlin.R
import com.m7amdelbana.hanginkotlin.network.model.PlacesEndPoint
import com.m7amdelbana.hanginkotlin.network.model.client
import com.m7amdelbana.hanginkotlin.network.model.token
import com.m7amdelbana.hanginkotlin.view.main.MainActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registration : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var password:TextInputLayout
    lateinit var firstName:EditText
    lateinit var lastName:EditText
    lateinit var phone:EditText
    lateinit var confirmPass:TextInputLayout

    lateinit var reg_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        email=findViewById(R.id.editText_email)
        password=findViewById(R.id.editText_password)
        firstName=findViewById(R.id.editText_first)
        lastName=findViewById(R.id.editText_last)

        phone=findViewById(R.id.editText_phone)
        confirmPass=findViewById(R.id.editText_confirm)

        reg_button=findViewById(R.id.create_account)

    }

    fun Register(view: View) {
        if(email.getText().toString().equals("")||password.editText!!.getText().toString().equals("")
            ||firstName.getText().toString().equals("")||lastName.getText().toString().equals("")||
            phone.getText().toString().equals("")  ||confirmPass.editText!!.getText().toString().equals("")
        ){
            Toast.makeText(this,"fill all data", Toast.LENGTH_LONG).show()
            return
        }
        if(password.editText!!.getText().toString()!=confirmPass.editText!!.getText().toString()){
            Toast.makeText(this,"the confirm password not the same", Toast.LENGTH_LONG).show()
            return
        }
        val service = client.retrofit!!.create(PlacesEndPoint::class.java)
        val json= JsonObject()
        json.addProperty("firstName",firstName.getText().toString())
        json.addProperty("lastName",lastName.getText().toString())
        json.addProperty("email",email.getText().toString())
        json.addProperty("phone",phone.getText().toString())
        json.addProperty("password",password.editText!!.getText().toString())

        val call=service.register(json)
        call.enqueue(object: Callback<token> {
            override fun onFailure(call: Call<token>, t: Throwable) {
                Toast.makeText(this@Registration,"something went wrong", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<token>, response: Response<token>) {
                if(response.code()==200){
                    val s:String?=response.body()!!.x_auth_token
                    Log.d("Tagggg","token"+s)

                    val intent=Intent(this@Registration,MainActivity::class.java)
                    intent.putExtra("x-auth-token",s)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this@Registration,response.body().toString(), Toast.LENGTH_SHORT).show()
                }

            }

        })

    }
}
