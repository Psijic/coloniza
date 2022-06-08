package com.psvoid.coloniza.city.presentation.views

import android.text.Layout
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.psvoid.coloniza.R
import com.psvoid.coloniza.city.presentation.viewmodels.CityViewModel
import com.psvoid.coloniza.common.presentation.ui.theme.MainTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Preview
@Composable
private fun MapTopBarPreview() {
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
//    val buildings by viewModel.buildings.collectAsState()
    val buildings = listOf(BuildingDto(),BuildingDto(),BuildingDto(),BuildingDto(),BuildingDto(),BuildingDto())

    Building(0)

    LazyHorizontalGrid(
        rows = GridCells.Fixed(3)
    ) {
        items(buildings.size) {
            Building(it)
        }
    }

//    LazyRow(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
//        items(buildings) {
//            Building(it)
//        }
//    }
}

@Composable
//fun Building(model: BuildingDto) {
fun Building(index: Int) {
//    Row(
//        verticalAlignment = CenterVertically,
//        modifier = Modifier
//            .wrapContentHeight()
//            .fillMaxWidth()
//    ) {
        Image(
//            painter = painterResource(id = model.image),
            painter = painterResource(id = R.drawable.discoveries),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .padding(5.dp)
        )
//    }
}

data class BuildingDto(
    @DrawableRes val image: Int = R.drawable.discoveries
)

data class TerrainDto(
    @DrawableRes val image: Int = 0
)
