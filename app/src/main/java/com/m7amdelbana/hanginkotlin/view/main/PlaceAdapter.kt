package com.m7amdelbana.hanginkotlin.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.m7amdelbana.hanginkotlin.R
import com.m7amdelbana.hanginkotlin.network.model.Place

class PlaceAdapter(var context: Context, var places: ArrayList<Place>, var onItemClicked: OnItemClicked) : RecyclerView.Adapter<PlaceHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
        val inflatedView = parent.inflate(R.layout.item_place, false)
        return PlaceHolder(inflatedView)    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun getItemCount(): Int = places.count()

    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
        holder.bindView(places[position])
        holder.itemView.setOnClickListener { onItemClicked.onClick(position) }
    }

}