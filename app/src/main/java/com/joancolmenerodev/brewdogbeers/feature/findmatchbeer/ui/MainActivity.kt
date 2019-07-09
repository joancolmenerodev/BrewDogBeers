package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.ui

import android.content.Context
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.joancolmenerodev.brewdogbeers.R
import com.joancolmenerodev.brewdogbeers.base.persistence.BrewBeer
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

    override fun showBeerList(beersList: List<BrewBeer>) {
        adapter = BrewBeerAdapter(beersList)
        rv_brewbeer.adapter = adapter
        rv_brewbeer.layoutManager = gridLayoutManager
        adapter.let {
            it.onItemClick = { brewBeerId -> presenter.onBeerClicked(brewBeerId) }
        }

        val animation = ScaleAnimation(
            0F, 1F, 0F, 1F, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        animation.duration = 1000
        rv_brewbeer.animation = animation
        rv_brewbeer.animate()
    }

    override fun showNoBeersFound() {
        System.out.println("No beers found")
    }

    override fun showError(errorMessage: String?) {
        System.out.println(errorMessage)
    }

    override fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun updateAutoCompleteEditText(brewSearchedList: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, brewSearchedList)
        et_food.setAdapter(adapter)
    }

    override fun showProgressBar(b: Boolean) {
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
}
