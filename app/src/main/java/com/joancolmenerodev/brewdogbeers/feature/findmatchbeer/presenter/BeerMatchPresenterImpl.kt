package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.presenter

import com.joancolmenerodev.brewdogbeers.base.responses.Beer
import com.joancolmenerodev.brewdogbeers.base.ui.AbstractPresenter
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.GetMatchBeersUseCase
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.GetUserSearchFromLocalUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BeerMatchPresenterImpl(private val getMatchBeersUseCase: GetMatchBeersUseCase,
                             private val getUserSearchFromLocalUseCase: GetUserSearchFromLocalUseCase) :
    AbstractPresenter<BeerMatchContract.View>(), BeerMatchContract.Presenter {


    override fun findBeerMatchers(food: String) {
        view?.showProgressBar(true)
        view?.hideKeyboard()
        if (!food.isBlank()) {
            food.replace(" ", "_")
        }
        val disposable = getMatchBeersUseCase.execute(food)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    view?.showProgressBar(false)
                    if (result.isEmpty()) {
                        view?.showNoBeersFound()
                    } else {
                        view?.showBeerList(result)
                    }

                },
                { error ->
                    view?.showProgressBar(false)
                    view?.showError(error.localizedMessage)
                })
        compositeDisposable.add(disposable!!)
    }

    override fun onBeerClicked(beer: Beer) {
    }

    override fun initializeAutoCompleteEditText() {
        val disposable = getUserSearchFromLocalUseCase.execute().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                view?.updateAutoCompleteEditText(it)
            }
        compositeDisposable.add(disposable)
    }

}