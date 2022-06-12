package com.psvoid.coloniza.world.city.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import com.psvoid.coloniza.common.presentation.ui.theme.Dimens
import com.psvoid.coloniza.common.presentation.ui.theme.Dimens.bottomSheetMinWidth
import com.psvoid.coloniza.common.presentation.ui.theme.MainTheme
import com.psvoid.coloniza.world.city.domain.buildings.Building
import com.psvoid.coloniza.world.city.domain.buildings.BuildingCategory
import timber.log.Timber

@Preview
@Composable
private fun CityPreview() {
    MainTheme {
        BuildingDialog(Building())
    }
}

@Composable
fun BuildingDialog(building: Building) {
    Column(Modifier.padding(8.dp)) {
        if (building.category == null) // Show build dialog
            BuildDialog(building)
        else //Show building properties
            BuildingProperties(building)
    }

}

@Composable
fun BuildDialog(building: Building) {
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
            itemsIndexed(buildings) { index, item ->
                DialogBuildingView(
                    model = item,
                    onClick = {
                        Timber.i("Dialog. Building $index selected: $item")
//                        viewModel.selectedBuilding.value = item
                    })
            }
        }

    }
}

@Composable
fun DialogBuildingView(
    model: Building,
    onClick: (Building) -> Unit = {}
) {
    Image(
        modifier = Modifier
            .clickable { onClick(model) }
            .size(130.dp)
            .padding(Dimens.paddingSmall),
        painter = painterResource(id = model.image),
        contentDescription = null,
        contentScale = ContentScale.Fit,
    )
}

@Composable
fun BuildingProperties(building: Building) {

}
