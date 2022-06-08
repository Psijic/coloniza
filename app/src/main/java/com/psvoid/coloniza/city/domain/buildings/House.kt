package com.psvoid.coloniza.city.domain.buildings

open class House: Building() {
    var comfortRatio = 0
    var rent = 0
    var upgrades = 0 //conditioner, radio, other stuff

    //Levels (constructor)
    var kitchen = 0
    var livingRoom = 0
    var bathroom = 0
    var fence = 0

}
