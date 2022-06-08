package com.psvoid.coloniza.world.city.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psvoid.coloniza.common.presentation.ui.theme.MainTheme
import com.psvoid.coloniza.world.city.domain.buildings.Building
import com.psvoid.coloniza.world.city.presentation.viewmodels.CityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Preview
@Composable
private fun CityPreview() {
    MainTheme {
        CityView(CityViewModel())
    }
}

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun CityView(
    viewModel: CityViewModel,
) {
    val buildings by viewModel.buildings.collectAsState()

    LazyHorizontalGrid(
        rows = GridCells.Fixed(viewModel.city.height)
    ) {
        itemsIndexed(buildings.flatten()) { index, item ->
            Building(item)
        }
    }
}

@Composable
fun Building(model: Building) {

    Image(
        painter = painterResource(id = model.image),
        contentDescription = null,
//            contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp)
            .padding(5.dp)
    )
}
