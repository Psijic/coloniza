package com.psvoid.coloniza.world.towns.people

import androidx.annotation.DrawableRes
import com.psvoid.coloniza.world.city.domain.buildings.Building
import com.psvoid.coloniza.world.city.domain.buildings.housing.House
import com.psvoid.coloniza.world.city.domain.people.Attributes
import com.psvoid.coloniza.world.towns.items.Item

class Human {

    @DrawableRes var portrait: Int? = null

    var skills: Skills = Skills()
    var needs: Needs = Needs()
    var attributes = Attributes()
    var priorities = Priorities()
    var happiness = Happiness()
    var perks = Perks()

    var name: String = "Adam"
    var surname: String = "Smith"
    var nationality: String = "Canadian"
    var faith = Faith.Christianity.Catholic

    //TODO: family ---
    var spouse: Human? = null
    var parents: Array<Human> = arrayOf()
    var children: Array<Human> = arrayOf()

    //--- current work, resting-place, home ---
    lateinit var house: House
    lateinit var workplace: Building
    lateinit var restingPlace: Building

    //--- items ---
    var items: Array<Item> = arrayOf()

    var salary: Int = 0
    var money: Int = 0

    /**
     * TODO: relations (отношения с другими людьми)
     */
    var relations: Array<Human>? = null

    fun generateHuman() {
        attributes.generateAverage()
        skills.generateAverage()
        priorities.generate()
        needs.calculate()
        happiness.calculate()
    }

    fun generateChild() {
        generateHuman()
    }

    fun generateAdult() {
        generateHuman()
    }
}