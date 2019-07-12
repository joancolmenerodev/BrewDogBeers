package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.presenter

import com.joancolmenerodev.brewdogbeers.base.persistence.room.dto.BrewBeer
import com.joancolmenerodev.brewdogbeers.base.utils.Constants
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.GetMatchBeersUseCase
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.GetSortedOrderUseCase
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.GetUserSearchFromLocalUseCase
import com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.usecases.SetSortedOrderUseCase
import com.joancolmenerodev.brewdogbeers.utils.RxImmediateSchedulerRule
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class BeerMatchPresenterImplTest {

    @get:Rule
    val schedulers = RxImmediateSchedulerRule()

    @Mock
    private lateinit var view: BeerMatchContract.View
    @Mock
    private lateinit var getSortedOrderUseCase: GetSortedOrderUseCase
    @Mock
    private lateinit var setSortedOrderUseCase: SetSortedOrderUseCase
    @Mock
    private lateinit var getMatchBeersUseCase: GetMatchBeersUseCase
    @Mock
    private lateinit var getUserSearchFromLocalUseCase: GetUserSearchFromLocalUseCase

    private lateinit var presenter: BeerMatchPresenterImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = BeerMatchPresenterImpl(
            getMatchBeersUseCase,
            getUserSearchFromLocalUseCase,
            getSortedOrderUseCase,
            setSortedOrderUseCase
        )
    }

    @Test
    fun getABVSortByUser() {
        //Assign
        presenter.attachView(view)
        Mockito.`when`(getSortedOrderUseCase.execute(Constants.SORTING_ABV_KEY,false)).thenReturn(true)

        //Act
        val result = presenter.getABVSortByUser()

        //Assert
        assertEquals(result, true)


    }

    @Test
    fun storeASCSorting() {
        //Assign
        presenter.attachView(view)
        Mockito.`when`(setSortedOrderUseCase.execute(Constants.SORTING_ABV_KEY,false)).thenCallRealMethod()
        Mockito.`when`(getSortedOrderUseCase.execute(Constants.SORTING_ABV_KEY,false)).thenReturn(false)

        //Act
        presenter.storeASCSorting()
        val result = presenter.getABVSortByUser()

        //Assert
        assertEquals(result, false)
    }

    @Test
    fun storeDESCSorting() {
        //Assign
        presenter.attachView(view)
        Mockito.`when`(setSortedOrderUseCase.execute(Constants.SORTING_ABV_KEY,false)).thenCallRealMethod()
        Mockito.`when`(getSortedOrderUseCase.execute(Constants.SORTING_ABV_KEY,false)).thenReturn(true)

        //Act
        presenter.storeASCSorting()
        val result = presenter.getABVSortByUser()

        //Assert
        assertEquals(result, true)
    }

    @Test
    fun findBeerMatchers() {
        //Assign
        presenter.attachView(view)
        Mockito.`when`(getSortedOrderUseCase.execute(Constants.SORTING_ABV_KEY,false)).thenReturn(true)
        Mockito.`when`(getMatchBeersUseCase.execute(FOOD_WORD)).thenReturn(Maybe.just(brewBeerList))

        //Act
        presenter.findBeerMatchers(FOOD_WORD)

        //Assert
        verify(view).showBeerList(brewBeerList)
    }

    @Test
    fun noBeerMatched(){
        //Assign
        presenter.attachView(view)
        Mockito.`when`(getMatchBeersUseCase.execute(FOOD_WORD)).thenReturn(Maybe.just(arrayListOf()))

        //Act
        presenter.findBeerMatchers(FOOD_WORD)

        //Assert
        verify(view).showNoBeersFound()
    }

    @Test
    fun onBeerClicked() {
        //Assign
        presenter.attachView(view)

        //Act
        presenter.onBeerClicked(BEER_ID)

        //Assert
        verify(view).navigateToBeerDetail(BEER_ID)
    }

    @Test
    fun initializeAutoCompleteEditText() {
        //Assign
        presenter.attachView(view)
        Mockito.`when`(getUserSearchFromLocalUseCase.execute()).thenReturn(Flowable.just(foodList))

        //Act
        presenter.initializeAutoCompleteEditText()

        //Assert
        verify(view).updateAutoCompleteEditText(foodList)
    }
    @Test
    fun initializeAutoCompleteEditTextEmpty() {
        //Assign
        presenter.attachView(view)
        Mockito.`when`(getUserSearchFromLocalUseCase.execute()).thenReturn(Flowable.empty())

        //Act
        presenter.initializeAutoCompleteEditText()

        //Assert
        verify(view, never()).updateAutoCompleteEditText(arrayListOf())
    }

    companion object {
        const val FOOD_WORD = "Patatas"
        const val BEER_ID = 1
        private val foodList = arrayListOf("Taco", "Kebab")
        private val brewBeerList  = mutableListOf(
            BrewBeer(
            id = 1,
            name = "Perfect beer",
            tagline = "This is the tagline",
            description = "Awesome description right there",
            imageUrl = "http://www.bestImage.com/beer",
            abv = 10.3,
            pairingFood = arrayListOf("Patatas con tomate", "Natillas con jarabe", "Piña con leche condensada"),
            userSearch = "Patatas"
            ),
            BrewBeer(
                id = 2,
                name = "Bad Beer",
                tagline = "This is the bad tagline",
                description = "Horrible description right there",
                imageUrl = "http://www.worstImage.com/beer",
                abv = 11.3,
                pairingFood = arrayListOf("Patatas con tomate", "Natillas con jarabe", "Piña con leche condensada"),
                userSearch = "Patatas"
            )
        )
    }
}