package com.psvoid.coloniza.world.city.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.psvoid.coloniza.common.presentation.ui.theme.Dimens
import com.psvoid.coloniza.common.presentation.ui.theme.Dimens.bottomSheetMinWidth
import com.psvoid.coloniza.common.presentation.ui.theme.MainTheme
import com.psvoid.coloniza.world.city.domain.buildings.Building
import com.psvoid.coloniza.world.city.domain.buildings.BuildingCategory
import com.psvoid.coloniza.world.city.presentation.viewmodels.CityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@Preview
@Composable
private fun CityPreview() {
    MainTheme {
        BuildingDialog(Building(), CityViewModel())
    }
}

@ExperimentalCoroutinesApi
@Composable
fun BuildingDialog(building: Building, viewModel: CityViewModel) {
    Column {
        if (building.category == null) // Show build dialog
            BuildDialog(viewModel)
        else //Show building properties
            BuildingProperties(building, viewModel)
    }

}

@ExperimentalCoroutinesApi
@Composable
fun BuildDialog(viewModel: CityViewModel) {
    var state by remember { mutableStateOf(0) }
    Column {
        TabRow(selectedTabIndex = state) {
            BuildingCategory.values().forEachIndexed { index, it ->
                Tab(
                    icon = { Icon(imageVector = it.icon, contentDescription = null) },
                    selected = state == index,
                    onClick = { state = index }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(BuildingCategory.values()[state].stringRes),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        LazyVerticalGrid(columns = GridCells.Adaptive(bottomSheetMinWidth)) {
            val buildings = BuildingCategory.values()[state].buildings
            itemsIndexed(buildings) { index, building ->
                DialogBuildingView(
                    building = building,
                    onClick = {
                        Timber.i("Dialog. Building $index selected: $building")
                        viewModel.build(building)
                    })
            }
        }

    }
}

@Composable
fun DialogBuildingView(
    building: Building,
    onClick: (Building) -> Unit = {}
) {
    Column(modifier = Modifier.padding(Dimens.paddingSmall)) {
        Image(
            modifier = Modifier
                .clickable { onClick(building) }
                .size(Dimens.imageSize),
            painter = painterResource(id = building.image),
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )

        Row {
            IconText(modifier = Modifier.weight(1f), Building.buildResIcons[0], building.buildCost[0].toString())
            IconText(modifier = Modifier.weight(1f), Building.buildResIcons[1], building.buildCost[1].toString())
        }
        Row {
            IconText(modifier = Modifier.weight(1f), Building.buildResIcons[2], building.buildCost[2].toString())
            IconText(modifier = Modifier.weight(1f), Building.buildResIcons[3], building.buildCost[3].toString())
        }
    }
}

@Composable
fun IconText(modifier: Modifier, icon: Int, text: String) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(icon), contentDescription = null,
            modifier = Modifier.size(Dimens.iconMedium)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@ExperimentalCoroutinesApi
@Composable
fun BuildingProperties(building: Building, viewModel: CityViewModel) {

}
