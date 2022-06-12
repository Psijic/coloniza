package com.psvoid.coloniza.world.city.domain.buildings.residental

import com.psvoid.coloniza.R
import com.psvoid.coloniza.world.city.domain.buildings.Building
import com.psvoid.coloniza.world.city.domain.buildings.BuildingCategory

open class Conventillo : Building(
    image = R.drawable.house,
    category = BuildingCategory.RESIDENTIAL,
    name = R.string.house,

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
