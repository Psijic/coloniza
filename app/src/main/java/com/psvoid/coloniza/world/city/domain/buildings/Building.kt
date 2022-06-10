package com.psvoid.coloniza.world.city.domain.buildings

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.psvoid.coloniza.R
import com.psvoid.coloniza.world.towns.items.Item
import com.psvoid.coloniza.world.towns.people.Human

enum class BuildingCategory(@StringRes val stringRes: Int, val icon: ImageVector) {
    INFRASTRUCTURE(R.string.infrastructure, Icons.Filled.Construction),
    RESOURCES(R.string.resources, Icons.Filled.Forest),
    INDUSTRY(R.string.industry, Icons.Filled.Factory),
    RESIDENTIAL(R.string.residential, Icons.Filled.House),
    ENTERTAINMENT(R.string.entertainment, Icons.Filled.Stadium),
    EDUCATION(R.string.education, Icons.Filled.School),
    PUBLIC_SERVICES(R.string.public_services, Icons.Filled.Church),
    MILITARY(R.string.military, Icons.Filled.Fort),
    GOVERNMENT(R.string.government, Icons.Filled.AccountBalance)
}

open class Building(
    @DrawableRes val image: Int = R.drawable.buildingruins,
    val category: BuildingCategory? = null,
    @StringRes val name: Int = R.string.none,
) {
    var level: Int = 0


    /**
     * список ресурсов необходимых для постройки этого здания: {id: count})
     */
    val buildCost: List<Item> = listOf()

    /**
     * ресурсы возвращаемые при разборе здания - 1/4 от постройки
     */
    val dismantlingCost: List<Item> = listOf()

    /**
     * Время постройки
     */
    val buildingTime: Int = 0

    /**
     * Время разборки (оставшееся)
     */
    val dismantlingTime: Int = 0

    /**
     * Время сноса (оставшееся)
     */
    val demolitionTime: Int = 0

    /**
     * id здания которое получится при апгрейде
     */
    val upgradedTo: Int = 0

    /**
     * TODO: id необходимых для апгрейда зданий
     */
    val needBuildingsForUpgrade: List<Building> = listOf()

//******************
//*** industrial ***
//******************

    /**
     * Индустрия. Товары на переработку
     */
    val goodsIn: List<Item> = listOf()

    /**
     * Индустрия. Выпускаемая продукция
     */
    val goodsOut: List<Item> = listOf()

    /**
     * Глава/директор
     */
    val head: Human? = null

    /**
     * Максимум управляющих
     */
    val managersMax: Int = 0

    /**
     * Управляющие
     */
    val managers: MutableList<Human> = mutableListOf()

    /**
     * Максимум рабочих
     */
    val workersMax: Int = 0

    /**
     * Рабочие
     */
    val workers: MutableList<Human> = mutableListOf()

    /**
     * Максимум посетителей/жителей
     */
    val occupantsMax: Int = 0

    /**
     * Посетители/жители
     */
    val occupants: MutableList<Human> = mutableListOf()

    /**
     * id вида рабочего
     */
    val workerType: Int = 0

    /**
     * Необходимый уровень образования для работы здесь
     */
    val needEducation: Int = 0

    /**
     * TODO: specialization
     */
    val specialization: Int = 0

}
