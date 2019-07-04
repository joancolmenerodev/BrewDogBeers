package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.inputmethod.InputMethodManager
import com.joancolmenerodev.brewdogbeers.R
import com.joancolmenerodev.brewdogbeers.base.responses.Beer
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.presenter.BeerMatchContract
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity(), BeerMatchContract.View , KodeinAware{
   override val kodein by kodein()
    private val presenter: BeerMatchContract.Presenter by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_icon as Toolbar)

    }

    override fun showBeerList(beersList: List<Beer>) {
        System.out.println("Beers size is ${beersList.size}")
    }

    override fun showNoBeersFound() {
        System.out.println("No beers found")
    }

    override fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun showProgressBar(b: Boolean) {
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
        presenter.findBeerMatchers("Spicy")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
