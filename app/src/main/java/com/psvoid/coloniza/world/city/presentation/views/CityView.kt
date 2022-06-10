package com.psvoid.coloniza.world.city.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psvoid.coloniza.common.presentation.ui.theme.Dimens
import com.psvoid.coloniza.common.presentation.ui.theme.MainTheme
import com.psvoid.coloniza.world.city.domain.buildings.Building
import com.psvoid.coloniza.world.city.presentation.viewmodels.CityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@Preview
@Composable
private fun CityPreview() {
    MainTheme {
        CityView(CityViewModel())
    }
}

@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@Composable
fun CityView(
    viewModel: CityViewModel,
) {
    val buildings by viewModel.buildings.collectAsState()

    LazyHorizontalGrid(
//        rows = GridCells.Adaptive(100.dp)
        rows = GridCells.Fixed(viewModel.city.height),
        modifier = Modifier.height(Dimens.cityViewHeight)
    ) {
        itemsIndexed(buildings.flatten()) { index, item ->
            Building(
                model = item,
                onClick = {
                    Timber.i("Building $index selected: $item")
                    viewModel.selectedBuilding.value = item
                })
        }
    }
}

@Composable
fun Building(
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
        contentScale = ContentScale.FillWidth,
    )
}
