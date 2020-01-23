package com.m7amdelbana.hanginkotlin.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.m7amdelbana.hanginkotlin.R
import com.m7amdelbana.hanginkotlin.network.model.Account
import com.m7amdelbana.hanginkotlin.network.model.Place
import com.m7amdelbana.hanginkotlin.network.model.PlacesEndPoint
import com.m7amdelbana.hanginkotlin.network.model.client
import com.m7amdelbana.hanginkotlin.view.details.Place_detail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import com.m7amdelbana.hanginkotlin.view.main.OnItemClicked as MainOnItemClicked

class MainActivity : AppCompatActivity(), MainOnItemClicked {



    lateinit var recyclerView: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var places: ArrayList<Place>
    var token:String?=null
    var placeAdapter: PlaceAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        token=intent.getStringExtra("x-auth-token")
        say_hello()
        recyclerView = findViewById(R.id.recyclerView)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        places = ArrayList()
        getPlaces()
    }

    private fun say_hello() {
        val service =client.retrofit!!.create(PlacesEndPoint::class.java)
        val call=service.getAccount("application/json",token!!)
        call.enqueue(object:Callback<Account>{
            override fun onFailure(call: Call<Account>, t: Throwable) {
                Toast.makeText(this@MainActivity,"something went wrong",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                if(response.code()==200){
                    Toast.makeText(this@MainActivity,"Hello "+ response.body()!!.firstName+" ;)",Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@MainActivity,response.body().toString(),Toast.LENGTH_LONG).show()

                }
            }
        })
    }

    private fun getPlaces() {
        val service =client.retrofit!!.create(PlacesEndPoint::class.java)
        val call=service.getplaces()
        call.enqueue(object:Callback<List<Place>>{
            override fun onFailure(call: Call<List<Place>>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"no data",Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>) {
                if(response.code()==200){
                    places=response.body()!! as ArrayList<Place>
                    placeAdapter= PlaceAdapter(this@MainActivity, places ,this@MainActivity)
                    recyclerView.adapter = placeAdapter
                }
                else{
                    Toast.makeText(this@MainActivity,response.body().toString(),Toast.LENGTH_LONG).show()
                }
            }
        })
    }
    override fun onClick(position: Int) {
        val intent = Intent(this, Place_detail::class.java)
        intent.putExtra("x-auth-token",token)
        val place:Place=places.get(position)
        intent.putExtra("place",place as Serializable)
        startActivity(intent)
       // Toast.makeText(this@MainActivity,"clicked"+position,Toast.LENGTH_LONG).show()
    }
}
