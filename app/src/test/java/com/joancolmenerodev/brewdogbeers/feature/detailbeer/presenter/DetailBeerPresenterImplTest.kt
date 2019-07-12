package com.joancolmenerodev.brewdogbeers.feature.detailbeer.presenter

import com.joancolmenerodev.brewdogbeers.base.persistence.room.dto.BrewBeer
import com.joancolmenerodev.brewdogbeers.feature.detailbeer.usecase.GetBeerDetailsUseCase
import com.joancolmenerodev.brewdogbeers.utils.RxImmediateSchedulerRule
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class DetailBeerPresenterImplTest  {

    @get:Rule
    val schedulers = RxImmediateSchedulerRule()

    @Mock
    private lateinit var view: DetailBeerContract.View
    @Mock
    private lateinit var getBeerDetailsUseCase: GetBeerDetailsUseCase

    private lateinit var presenter: DetailBeerPresenterImpl


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailBeerPresenterImpl(getBeerDetailsUseCase)
    }


    @Test
    fun findBeerDetails() {

        //Assign
        presenter.attachView(view)
        Mockito.`when`(getBeerDetailsUseCase.execute(BEER_ID)).thenReturn(Maybe.just(brewBeerDetail))

        //Act
        presenter.findBeerDetails(BEER_ID)

        //Assert
        Mockito.verify(view).showBeerAbv(brewBeerDetail.abv.toString())
        Mockito.verify(view).showBeerPairingFood(convertListToString(brewBeerDetail.pairingFood))
        Mockito.verify(view).showBeerImage(brewBeerDetail.imageUrl)
        Mockito.verify(view).showBeerTagLine(brewBeerDetail.tagline)
        Mockito.verify(view).showBeerName(brewBeerDetail.name)
        Mockito.verify(view).showBeerDescription(brewBeerDetail.description)

    }

    private fun convertListToString(beerPairingFood : List<String>): String {
        val sb = StringBuilder()

        beerPairingFood.map {
            it.replace("[", "")
            it.replace("]", "")
            it.replace(",", "")
            sb.append(it+"\n")
        }
        return sb.toString()
    }

    companion object {
        private const val BEER_ID = 1
        private val brewBeerDetail = BrewBeer(
            id = BEER_ID,
            name = "Perfect beer",
            tagline = "This is the tagline",
            description = "Awesome description right there",
            imageUrl = "http://www.bestImage.com/beer",
            abv = 10.3,
            pairingFood = arrayListOf("[Patatas con tomate", "Natillas con jarabe", "Piña con leche condensada]"),
            userSearch = "Patatas"
        )
    }

}