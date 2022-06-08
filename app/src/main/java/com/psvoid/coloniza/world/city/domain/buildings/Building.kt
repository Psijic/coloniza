package com.psvoid.coloniza.world.city.domain.buildings

import androidx.annotation.DrawableRes
import com.psvoid.coloniza.world.towns.items.Item
import com.psvoid.coloniza.world.towns.people.Human

open class Building(
    @DrawableRes val image: Int,
    val category: String,
    val name: String,
    val level: Int,

    /**
     * список ресурсов, необходимых для постройки этого здания: {id: count})
     */
    val buildCost: List<Item>,

    /**
     * ресурсы, возвращаемые при разборе здания - 1/4 от постройки
     */
    val dismantlingCost: List<Item>,

    /**
     * Время постройки
     */
    val buildingTime: Int,
    /**
     * Время разборки (оставшееся)
     */
    val dismantlingTime: Int,
    /**
     * Время сноса (оставшееся)
     */
    val demolitionTime: Int,

    /**
     * id здания, которое получится при апгрейде
     */
    val upgradedTo: Int,

    /**
     * TODO: id необходимых для апгрейда зданий
     */
    val needBuildingsForUpgrade: List<Building>,

//******************
//*** industrial ***
//******************

    /**
     * Индустрия. Товары на переработку
     */
    val goodsIn: List<Item>,
    /**
     * Индустрия. Выпускаемая продукция
     */
    val goodsOut: List<Item>,

    /**
     * Глава/директор
     */
    val head: Human,

    /**
     * Максимум управляющих
     */
    val managersMax: Int,

    /**
     * Управляющие
     */
    val managers: MutableList<Human>,

    /**
     * Максимум рабочих
     */
    val workersMax: Int,

    /**
     * Рабочие
     */
    val workers: MutableList<Human>,

    /**
     * Максимум посетителей/жителей
     */
    val occupantsMax: Int,

    /**
     * Посетители/жители
     */
    val occupants: MutableList<Human>,

    /**
     * id вида рабочего
     */
    val workerType: Int,

    /**
     * Необходимый уровень образования для работы здесь
     */
    val needEducation: Int = 0,

    /**
     * TODO: specialization
     */
    val specialization: Int,

    )
