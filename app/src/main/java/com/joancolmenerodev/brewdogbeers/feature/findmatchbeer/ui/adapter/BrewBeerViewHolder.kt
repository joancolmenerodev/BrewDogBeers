package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.joancolmenerodev.brewdogbeers.R
import com.joancolmenerodev.brewdogbeers.base.persistence.room.dto.BrewBeer
import com.joancolmenerodev.brewdogbeers.base.utils.Constants.DEFAULT_BEER_IMAGE

class BrewBeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val brewBeerName: TextView = itemView.findViewById(R.id.tv_beer_item_name)
    private val brewBeerTagLine: TextView = itemView.findViewById(R.id.tv_beer_item_tagline)
    private val brewBeerImage: ImageView = itemView.findViewById(R.id.iv_beer_item_image)
    private val brewBeerCard: LinearLayout = itemView.findViewById(R.id.linear_main_beer_item)
    private val brewBeerAdv: TextView = itemView.findViewById(R.id.tv_beer_item_abv)


    fun bind(
        brewBeer: BrewBeer,
        onItemClick: (brewBeerId: Int) -> Unit
    ) {
        brewBeerName.text = brewBeer.name
        brewBeerTagLine.text = brewBeer.tagline
        brewBeerAdv.text = String.format(itemView.context.getString(R.string.item_beer_abv_percentage), brewBeer.abv.toString())
        if (brewBeer.imageUrl != null) {
            displayLogo(brewBeer.imageUrl)
        } else {
            displayLogo(DEFAULT_BEER_IMAGE)
        }

        brewBeerCard.setOnClickListener { onItemClick.invoke(brewBeer.id) }

    }

    private fun displayLogo(url: String) {
        val circularProgressDrawable = CircularProgressDrawable(itemView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        val requestOptions = RequestOptions()
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .error(android.R.drawable.stat_notify_error)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()
        Glide.with(itemView.context).load(url)
            .apply(requestOptions)
            .into(brewBeerImage)
    }
}