package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.presenter

import android.util.Log
import com.joancolmenerodev.brewdogbeers.base.persistence.room.dto.BrewBeer
import com.joancolmenerodev.brewdogbeers.base.ui.AbstractPresenter
import com.joancolmenerodev.brewdogbeers.base.utils.Constants.SORTING_ABV_KEY
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.GetMatchBeersUseCase
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.GetSortedOrderUseCase
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.GetUserSearchFromLocalUseCase
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.SetSortedOrderUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BeerMatchPresenterImpl(
    private val getMatchBeersUseCase: GetMatchBeersUseCase,
    private val getUserSearchFromLocalUseCase: GetUserSearchFromLocalUseCase,
    private val getSortedOrderUseCase: GetSortedOrderUseCase,
    private val setSortedOrderUseCase: SetSortedOrderUseCase
) :
    AbstractPresenter<BeerMatchContract.View>(), BeerMatchContract.Presenter {
    override fun getABVSortByUser(): Boolean {
        return getSortedOrderUseCase.execute(SORTING_ABV_KEY, false) as Boolean
    }

    override fun storeASCSorting() {
        setSortedOrderUseCase.execute(SORTING_ABV_KEY, true)
        Log.d("EXAMPLE","El poso a true")
    }

    override fun storeDESCSorting() {
        setSortedOrderUseCase.execute(SORTING_ABV_KEY, false)
        Log.d("EXAMPLE","El poso a false")
    }


    override fun findBeerMatchers(food: String) {
        view?.hideKeyboard()
        if (!food.isBlank()) {
            food.replace(" ", "_")
        }
        val disposable = getMatchBeersUseCase.execute(food)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result.isEmpty()) {
                        view?.showNoBeersFound()
                    } else {
                        view?.showBeerList(sortList(result))
                    }

                },
                { error ->
                    view?.showError(error.localizedMessage)
                })
        compositeDisposable.add(disposable!!)
    }

    override fun onBeerClicked(brewBeerId: Int) {
        view?.navigateToBeerDetail(brewBeerId)
    }

    override fun initializeAutoCompleteEditText() {
        val disposable = getUserSearchFromLocalUseCase.execute().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                view?.updateAutoCompleteEditText(it)
            }
        compositeDisposable.add(disposable)
    }

    private fun sortList(result: List<BrewBeer>): List<BrewBeer> {
        val sort = getSortedOrderUseCase.execute(SORTING_ABV_KEY, false) as Boolean
        return if (sort) {
            result.sortedBy { it.abv }
        } else {
            result.sortedByDescending { it.abv }
        }
    }


}