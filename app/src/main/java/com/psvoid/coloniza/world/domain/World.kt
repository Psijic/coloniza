package com.psvoid.coloniza.world.domain

import com.psvoid.coloniza.world.city.domain.City

class World {
    init {
        generateTowns()
    }

    private val cities = mutableListOf<City>()

    private fun generateTowns() {
        cities[0] = City()
    }

    fun endTurn() {
        for (town in cities) town.calculate()
    }
}