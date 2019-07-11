package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joancolmenerodev.brewdogbeers.R
import com.joancolmenerodev.brewdogbeers.base.persistence.room.dto.BrewBeer

class BrewBeerAdapter(private val brewBeerList: List<BrewBeer>): RecyclerView.Adapter<BrewBeerViewHolder>() {

    lateinit var onItemClick: (brewBeerId: Int) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BrewBeerViewHolder {
        return BrewBeerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return brewBeerList.size
    }

    override fun onBindViewHolder(holder: BrewBeerViewHolder, position: Int) {
        holder.bind(brewBeerList[position], onItemClick)
    }

    fun getList() = brewBeerList

}