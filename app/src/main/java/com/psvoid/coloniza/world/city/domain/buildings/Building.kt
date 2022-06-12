package com.psvoid.coloniza.world.city.domain.buildings

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.psvoid.coloniza.R
import com.psvoid.coloniza.world.city.domain.buildings.education.*
import com.psvoid.coloniza.world.city.domain.buildings.entertainment.*
import com.psvoid.coloniza.world.city.domain.buildings.government.*
import com.psvoid.coloniza.world.city.domain.buildings.industry.*
import com.psvoid.coloniza.world.city.domain.buildings.infrastructure.*
import com.psvoid.coloniza.world.city.domain.buildings.publicServices.*
import com.psvoid.coloniza.world.city.domain.buildings.residental.*
import com.psvoid.coloniza.world.city.domain.buildings.resources.*
import com.psvoid.coloniza.world.towns.items.Item
import com.psvoid.coloniza.world.towns.people.Human

enum class BuildingCategory(@StringRes val stringRes: Int, val icon: ImageVector, val buildings: List<Building>) {
    INFRASTRUCTURE(
        R.string.infrastructure,
        Icons.Filled.Construction,
        listOf(
            Airport(),
            Builders(),
            Depot(),
            Dock(),
            PowerPlant(),
            SolarPowerPlant(),
            Teamsters(),
            Warehouse(),
            WindTurbine()
        )
    ),
    RESOURCES(
        R.string.resources,
        Icons.Filled.Forest,
        listOf(Farm(), FishFarm(), LoggingCamp(), Mine(), OilWell(), Pit(), Ranch())
    ),
    INDUSTRY(
        R.string.industry, Icons.Filled.Factory, listOf(
            Brickyard(),
            Cannery(),
            CementPlant(),
            ChocolateFactory(),
            Distillery(),
            ElectronicsFactory(),
            FashionCompany(),
            Foundry(),
            FurnitureFactory(),
            Jeveller(),
            Juicery(),
            LumberMill(),
            PharmaceuticalCompany(),
            PlasticsPlant(),
            Refinery(),
            Shipyard(),
            SteelMill(),
            Tannery(),
            TextileMill(),
            TobaccoFactory(),
            ToyWorkshop(),
            VehicleFactory(),
            WeaponsFactory(),
        )
    ),
    RESIDENTIAL(
        R.string.residential, Icons.Filled.House,
        listOf(Apartment(), Conventillo(), Flophouse(), House(), Mansion(), Tenement())
    ),
    ENTERTAINMENT(
        R.string.entertainment,
        Icons.Filled.Stadium,
        listOf(
            Casino(),
            Garden(),
            GolfCourse(),
            MovieTheater(),
            Museum(),
            NightClub(),
            Restaurant(),
            Stadium(),
            Tavern(),
            Theater()
        )
    ),
    EDUCATION(
        R.string.education,
        Icons.Filled.School,
        listOf(College(), Library(), Newspaper(), RadioStation(), ResearchLab(), School(), TvStation())
    ),
    PUBLIC_SERVICES(
        R.string.public_services,
        Icons.Filled.Church,
        listOf(Church(), FireStation(), GarbageDump(), Hospital(), Shop())
    ),

    //    MILITARY(R.string.military, Icons.Filled.Fort, listOf(House())),
    GOVERNMENT(
        R.string.government,
        Icons.Filled.AccountBalance,
        listOf(
            ArmyBase(),
            Bank(),
            Barracks(),
            Courthouse(),
            Embassy(),
            ImmigrationOffice(),
            Ministry(),
            PoliceStation(),
            Prison(),
            SpyCentre()
        )
    )
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
