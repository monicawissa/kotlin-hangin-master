package com.m7amdelbana.hanginkotlin.view.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
import com.m7amdelbana.hanginkotlin.R
import com.m7amdelbana.hanginkotlin.network.model.Place
import com.squareup.picasso.Picasso




class Place_detail : AppCompatActivity() {
    var token:String?=null
    var place:Place?=null
    lateinit var place_profile: ImageView
    lateinit var place_adress: TextView
    lateinit var rate_from5: TextView
    lateinit var phone: TextView
    lateinit var place_name: TextView
    lateinit var rating_bar: RatingBar
    lateinit var bottomNavigationView: BottomNavigationView
    private val mOnNavigationItemSelectedListener=
        OnNavigationItemSelectedListener{ item ->
        when(item.itemId){
            R.id.nav_review->{
                openFragment(ReviewFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_menu->{
                openFragment(MenuFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_about -> {
                openFragment(AboutFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_location -> {
                openFragment(LocationFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    private fun openFragment(fragment:Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)
        token=intent.getStringExtra("x-auth-token")
        place=intent.extras!!.get("place")as Place
        place_profile=findViewById(R.id.place_profile)
        place_adress=findViewById(R.id.place_address)
        place_name=findViewById(R.id.place_name)
        rate_from5=findViewById(R.id.rating_from5)
        rating_bar=findViewById(R.id.rate_bar)
        phone=findViewById(R.id.call_tv)
        bottomNavigationView=findViewById(R.id.bottom_navigation)

        setUi()

    }

    private fun setUi() {
        Picasso.get()
            .load(place!!.image)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_error)
            .into(place_profile)
        place_adress.setText(place!!.address)
        place_name.setText(place!!.name)
        Log.d("Tagggg","rating = "+place!!.rating)
        Log.d("Tagggg","totalrating = "+place!!.totalRating)
        rate_from5.setText("("+place!!.rating!!.toInt()+"/5)")
        rating_bar.rating=place!!.rating!!.toFloat()
        phone.setText(place!!.phone)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }
}
