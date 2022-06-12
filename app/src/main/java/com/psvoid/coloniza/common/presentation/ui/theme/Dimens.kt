package com.psvoid.coloniza.common.presentation.ui.theme

import androidx.compose.ui.unit.dp
import com.psvoid.coloniza.world.city.domain.City

object Dimens {
    val cityViewHeight = (City.DEFAULT_HEIGHT * 100).dp

    val bottomSheetPeekHeight = 512.dp
    val bottomSheetMinWidth = 100.dp
    val bottomSheetNoHeight = 0.dp

    val roundedCorners = 16.dp

    val paddingXs = 4.dp
    val paddingSmall = 8.dp
    val paddingNormal = 16.dp

    val paddingTopXs = 4.dp
    val paddingTopSmall = 8.dp
    val paddingTop = 16.dp
    val paddingTopBig = 24.dp

    val paddingStartXs = 8.dp
    val paddingStartSmall = 16.dp
    val paddingStart = 32.dp

    val paddingEndXs = paddingStartXs
    val paddingEndSmall = paddingStartSmall
    val paddingEnd = paddingStart

    val spaceNormal = 24.dp

}