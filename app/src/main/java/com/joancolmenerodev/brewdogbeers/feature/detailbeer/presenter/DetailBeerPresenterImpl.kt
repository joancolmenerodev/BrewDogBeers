package com.joancolmenerodev.brewdogbeers.feature.detailbeer.presenter

import com.joancolmenerodev.brewdogbeers.base.ui.AbstractPresenter
import com.joancolmenerodev.brewdogbeers.base.utils.Constants.DEFAULT_BEER_IMAGE
import com.joancolmenerodev.brewdogbeers.feature.detailbeer.usecase.GetBeerDetailsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailBeerPresenterImpl(private val getBeerDetailsUseCase: GetBeerDetailsUseCase) :
    AbstractPresenter<DetailBeerContract.View>(), DetailBeerContract.Presenter {

    override fun findBeerDetails(brewBeerId: Int) {
        val disposable = getBeerDetailsUseCase.execute(brewBeerId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    displayBeerAbv(result.abv.toString())
                    displayBeerImage(result.imageUrl)
                    displayBeerDescription(result.description)
                    displayBeerName(result.name)
                    displayBeerTagLine(result.tagline)
                    displayPairingFood(result.pairingFood)

                },
                { error ->
                    view?.showError(error.localizedMessage)
                })
        compositeDisposable.add(disposable)

    }

    private fun displayBeerName(beerName: String) = view?.showBeerName(beerName)


    private fun displayBeerTagLine(beerTagLine: String) = view?.showBeerTagLine(beerTagLine)

    private fun displayBeerImage(beerImage: String?) {
        if (beerImage == null) {
            view?.showBeerImage(DEFAULT_BEER_IMAGE)
        } else {
            view?.showBeerImage(beerImage)
        }
    }

    private fun displayBeerDescription(beerDescription: String) = view?.showBeerDescription(beerDescription)

    private fun displayPairingFood(beerPairingFood: List<String>) {
        val sb = StringBuilder()

        beerPairingFood.map {
            it.replace("[", "")
            it.replace("]", "")
            it.replace(",", "")
            sb.append(it+"\n")
        }

        view?.showBeerPairingFood(sb.toString())
    }

    private fun displayBeerAbv(beerAbv: String) = view?.showBeerAbv(beerAbv)
}