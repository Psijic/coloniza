package com.psvoid.coloniza.world.city.domain

import com.psvoid.coloniza.world.city.domain.buildings.Building
import com.psvoid.coloniza.world.towns.people.Human

typealias BuildingsMap = MutableList<MutableList<Building>>

class City {
    companion object Config {
        const val START_POPULATION = 50

        const val DEFAULT_WIDTH = 6
        const val DEFAULT_HEIGHT = 6
    }

    var width = DEFAULT_WIDTH
    var height = DEFAULT_HEIGHT

    var name = "Smallton"
    val humans = mutableListOf<Human>()
    val buildings: BuildingsMap = mutableListOf()

    init {
        generateLand()
        generateBuildings()
        generatePopulation()
    }

    private fun generatePopulation() {
        for (i in 0..START_POPULATION) humans.add(Human())
    }

    private fun generateBuildings() {
        buildings.clear()

        for (h in 0 until height) {
            buildings.add(mutableListOf())
            for (w in 0 until width) {
                buildings[h].add(Building())
            }
        }
    }

    private fun generateLand() {

    }

    fun calculate() {
        calculateBuildings()
        calculatePopulation()
    }

    private fun calculatePopulation() {

    }

    private fun calculateBuildings() {

    }
}