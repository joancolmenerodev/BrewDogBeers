package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.model

import com.joancolmenerodev.brewdogbeers.base.persistence.room.dto.BrewFood
import org.junit.Assert.assertEquals
import org.junit.Test

class UserSearchMapperKtTest {


    @Test
    fun userSearchMapperTest() {

        val listMapped = userSearchMapper(userSearchList)
        assertEquals(listMapped,userSearchListMapped)

    }

    companion object {
        private val userSearchList = arrayListOf(
            BrewFood(
                "Patata_comfitada"
            ),
            BrewFood(
                "Kebab_de_pollo"
            )
        )
        private val userSearchListMapped = arrayListOf(
                "Patata comfitada",
                "Kebab de pollo"
        )
    }
}