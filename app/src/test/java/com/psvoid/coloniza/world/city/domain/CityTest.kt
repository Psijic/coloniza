package com.psvoid.coloniza.world.city.domain

import org.junit.jupiter.api.Test

internal class CityTest {
    val city = City()

    @Test
    fun getBuildings() {
        assert(city.buildings.flatten().size > 1)
    }
}