package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.presenter

import com.joancolmenerodev.brewdogbeers.base.responses.Beer
import com.joancolmenerodev.brewdogbeers.base.ui.BasePresenter
import com.joancolmenerodev.brewdogbeers.base.ui.BaseView

interface BeerMatchContract {

    interface View : BaseView {
        fun showBeerList(beersList: List<Beer>)
        fun showNoBeersFound()
        fun hideKeyboard()
        fun showProgressBar(b: Boolean)
        fun showError(errorMessage: String?) {

        }
    }

    interface Presenter : BasePresenter<View> {
        fun findBeerMatchers(food: String)
        fun onBeerClicked(beer: Beer)
    }
}