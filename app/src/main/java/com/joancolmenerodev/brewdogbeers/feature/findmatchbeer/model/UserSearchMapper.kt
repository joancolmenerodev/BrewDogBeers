package com.joancolmenerodev.brewdogbeers.feature.findmatchbeer.model

import com.joancolmenerodev.brewdogbeers.base.persistence.room.dto.BrewFood

fun userSearchMapper(userSearchList: List<BrewFood>): List<String> {
    return userSearchList.map { list -> list.name.replace("_"," ") }
}