package com.psvoid.coloniza.world.city.domain.buildings.infrastructure

import com.psvoid.coloniza.R
import com.psvoid.coloniza.world.city.domain.buildings.Building

data class Depot( override val image: Int = R.drawable.smallgarage ): Building() {
}