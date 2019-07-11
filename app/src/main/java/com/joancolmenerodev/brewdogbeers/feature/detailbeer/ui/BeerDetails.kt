package com.joancolmenerodev.brewdogbeers.feature.detailbeer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.joancolmenerodev.brewdogbeers.R
import com.joancolmenerodev.brewdogbeers.base.utils.Constants.BEER_ID_KEY
import com.joancolmenerodev.brewdogbeers.feature.detailbeer.presenter.DetailBeerContract
import kotlinx.android.synthetic.main.activity_beer_details.*
import kotlinx.android.synthetic.main.content_beer_details.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class BeerDetails : AppCompatActivity(), KodeinAware, DetailBeerContract.View{

    override val kodein by kodein()
    private val presenter: DetailBeerContract.Presenter by instance()
    private var brewBeerId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_details)
        setSupportActionBar(toolbar_icon as Toolbar)

        intent.let {
            brewBeerId = it?.extras?.getInt(BEER_ID_KEY)!!
        }
    }


    override fun showBeerName(beerName: String) {
        tv_beer_detail_name.text = beerName
    }

    override fun showBeerAbv(beerAbv: String) {
        tv_beer_detail_abv.text = String.format(this.getString(R.string.abv_detail_text), beerAbv)
    }

    override fun showBeerTagLine(beerTagLine: String) {
        tv_beer_detail_tag_line.text = beerTagLine
    }

    override fun showBeerPairingFood(beerPairingFood: String) {
        tv_beer_detail_pairing_food.text = beerPairingFood
    }

    override fun showBeerImage(beerImage: String?) {
        val circularProgressDrawable = CircularProgressDrawable(this)
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
        Glide.with(this).load(beerImage)
            .apply(requestOptions)
            .into(iv_beer_detail_image)
    }

    override fun showBeerDescription(beerDescription: String) {
        tv_beer_detail_description.text = beerDescription
    }

    override fun showError(localizedMessage: String?) {
        localizedMessage?.let {
            Snackbar.make(findViewById(android.R.id.content), localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }


    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
        presenter.findBeerDetails(brewBeerId)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}
