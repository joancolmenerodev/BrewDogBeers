package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.presenter

import com.joancolmenerodev.brewdogbeers.base.persistence.BrewBeer
import com.joancolmenerodev.brewdogbeers.base.ui.BasePresenter
import com.joancolmenerodev.brewdogbeers.base.ui.BaseView

interface BeerMatchContract {

    interface View : BaseView {
        fun showBeerList(beersList: List<BrewBeer>)
        fun showNoBeersFound()
        fun hideKeyboard()
        fun showProgressBar(b: Boolean)
        fun showError(errorMessage: String?)
        fun updateAutoCompleteEditText(brewSearchedList: List<String>)
    }

    interface Presenter : BasePresenter<View> {
        fun findBeerMatchers(food: String)
        fun initializeAutoCompleteEditText()
        fun onBeerClicked(beer: Int)
    }
}