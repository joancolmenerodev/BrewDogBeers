package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.model

import com.joancolmenerodev.brewdogbeers.base.persistence.BrewSearched

fun userSearchMapper(userSearchList: List<BrewSearched>): List<String> {
    return userSearchList.map { list -> list.food }
}