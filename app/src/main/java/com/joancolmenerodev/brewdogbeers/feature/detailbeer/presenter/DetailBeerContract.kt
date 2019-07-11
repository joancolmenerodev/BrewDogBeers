package com.joancolmenerodev.brewdogbeers.feature.detailbeer.presenter

import com.joancolmenerodev.brewdogbeers.base.ui.BasePresenter
import com.joancolmenerodev.brewdogbeers.base.ui.BaseView

interface DetailBeerContract {

    interface View : BaseView {
        fun showBeerName(beerName: String)
        fun showBeerAbv(beerAbv: String)
        fun showBeerTagLine(beerTagLine: String)
        fun showBeerPairingFood(beerPairingFood: String)
        fun showBeerImage(beerImage: String?)
        fun showBeerDescription(beerDescription: String)
        fun showError(localizedMessage: String?)
    }

    interface Presenter : BasePresenter<View> {
        fun findBeerDetails(brewBeerId : Int)
    }
}