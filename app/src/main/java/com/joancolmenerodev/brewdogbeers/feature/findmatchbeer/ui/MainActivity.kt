package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import com.joancolmenerodev.brewdogbeers.R
import com.joancolmenerodev.brewdogbeers.base.persistence.BrewBeer
import com.joancolmenerodev.brewdogbeers.base.responses.Beer
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.presenter.BeerMatchContract
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
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

        iv_search.setOnClickListener {
            presenter.findBeerMatchers(et_food.text.toString())
        }



    }

    override fun showBeerList(beersList: List<BrewBeer>) {
        System.out.println("Beers size is ${beersList.size}")
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
