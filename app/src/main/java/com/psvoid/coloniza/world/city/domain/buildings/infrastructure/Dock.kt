package com.psvoid.coloniza.world.city.domain.buildings.infrastructure

import com.psvoid.coloniza.R
import com.psvoid.coloniza.world.city.domain.buildings.Building

data class Dock( override val image: Int = R.drawable.port ): Building() {
}