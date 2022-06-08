package com.psvoid.coloniza.world.domain

import com.psvoid.coloniza.world.city.domain.City

class World {
    init {
        generateTowns()
    }

    var cities: Array<City> = arrayOf()

    private fun generateTowns() {
        cities[0] = City()
    }

    fun endTurn() {
        for (town in cities) town.calculate()
    }
}