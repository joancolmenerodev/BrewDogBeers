package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.utils.LottieValueAnimator
import com.google.android.material.snackbar.Snackbar
import com.joancolmenerodev.brewdogbeers.BeerDetails
import com.joancolmenerodev.brewdogbeers.R
import com.joancolmenerodev.brewdogbeers.base.persistence.room.dto.BrewBeer
import com.joancolmenerodev.brewdogbeers.base.utils.Constants.BEER_ID_KEY
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.presenter.BeerMatchContract
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.ui.adapter.BrewBeerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity(), BeerMatchContract.View, KodeinAware {


    override val kodein by kodein()
    private val presenter: BeerMatchContract.Presenter by instance()

    private lateinit var adapter: BrewBeerAdapter
    private lateinit var gridLayoutManager: GridLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_icon as Toolbar)

        initViews()

        iv_search.setOnClickListener {
            presenter.findBeerMatchers(et_food.text.toString())
        }


    }

    private fun initViews() {
        gridLayoutManager = GridLayoutManager(this, 2)
    }

    private fun initRecyclerViewAdapter(beersList: List<BrewBeer>) {
        adapter = BrewBeerAdapter(beersList)
        rv_brewbeer.adapter = adapter
        rv_brewbeer.layoutManager = gridLayoutManager
        createAnimationForRecyclerView()
        adapter.let {
            it.onItemClick = { brewBeerId -> presenter.onBeerClicked(brewBeerId) }
        }
    }

    private fun createAnimationForRecyclerView() {
        val animation = ScaleAnimation(
            0F, 1F, 0F, 1F, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        animation.duration = 1000
        rv_brewbeer.animation = animation
        rv_brewbeer.animate()
    }

    override fun showBeerList(beersList: List<BrewBeer>) {
        linear_no_beer_found.visibility = View.GONE
        rv_brewbeer.visibility = View.VISIBLE
        initRecyclerViewAdapter(beersList)

    }

    override fun showNoBeersFound() {
        rv_brewbeer.visibility = View.GONE
        linear_no_beer_found.visibility = View.VISIBLE
        lav_no_beer_found.playAnimation()
        lav_no_beer_found.repeatCount = LottieValueAnimator.INFINITE
    }

    override fun showError(errorMessage: String?) {
        errorMessage?.let {
            Snackbar.make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun navigateToBeerDetail(brewBeerId: Int) {
        val intent = Intent(
            this@MainActivity,
            BeerDetails::class.java
        )
        val bundle = Bundle().also {
            it.putInt(BEER_ID_KEY, brewBeerId)
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun updateAutoCompleteEditText(brewSearchedList: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, brewSearchedList)
        et_food.setAdapter(adapter)
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
        presenter.initializeAutoCompleteEditText()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.sorting_abv, menu)
        val toggleButton =  menu?.findItem(R.id.sort_abv)?.actionView?.findViewById(R.id.abv_switch) as ToggleButton
        toggleButton.isChecked = presenter.getABVSortByUser()
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                presenter.storeDESCSorting()
                updateList(false)
            }
            else{
                presenter.storeASCSorting()
                updateList(true)

            }
        }
        return true
    }

    private fun updateList(sort: Boolean){
        if(sort){
            initRecyclerViewAdapter(adapter.getList().sortedByDescending { it.abv })
        }
        else{
            initRecyclerViewAdapter(adapter.getList().sortedBy { it.abv })
        }
    }
}
