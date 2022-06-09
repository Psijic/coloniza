package com.psvoid.coloniza.world.city.domain.buildings.houses

import com.psvoid.coloniza.R
import com.psvoid.coloniza.world.city.domain.buildings.Building

open class House : Building(
    image = R.drawable.house,
    category = "Rental",
    name = "House",
    level = 0
) {
    var comfortRatio = 0
    var rent = 0
    var upgrades = 0 //conditioner, radio, other stuff

    //Levels (constructor)
    var kitchen = 0
    var livingRoom = 0
    var bathroom = 0
    var fence = 0

}
