package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.presenter

import com.joancolmenerodev.brewdogbeers.base.persistence.room.dto.BrewBeer
import com.joancolmenerodev.brewdogbeers.base.ui.BasePresenter
import com.joancolmenerodev.brewdogbeers.base.ui.BaseView

interface BeerMatchContract {

    interface View : BaseView {
        fun showBeerList(beersList: List<BrewBeer>)
        fun showNoBeersFound()
        fun hideKeyboard()
        fun showError(errorMessage: String?)
        fun updateAutoCompleteEditText(brewSearchedList: List<String>)
        fun navigateToBeerDetail(brewBeerId: Int)
    }

    interface Presenter : BasePresenter<View> {
        fun findBeerMatchers(food: String)
        fun initializeAutoCompleteEditText()
        fun onBeerClicked(brewBeerId: Int)
        fun getABVSortByUser(): Boolean
        fun storeASCSorting()
        fun storeDESCSorting()
    }
}