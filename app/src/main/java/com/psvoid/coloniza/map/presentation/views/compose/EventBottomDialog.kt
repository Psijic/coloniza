package com.psvoid.coloniza.map.presentation.views.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gowtham.ratingbar.ComposeStars
import com.gowtham.ratingbar.RatingBarStyle
import com.psvoid.coloniza.R
import com.psvoid.coloniza.common.presentation.ui.theme.Dimens
import com.psvoid.coloniza.common.presentation.ui.theme.MainTheme
import com.psvoid.coloniza.map.domain.EventItem

@Composable
fun EventView(event: EventItem) {
    Column(Modifier.padding(8.dp)) {
        Row {
            // Image with date, title, type and buttons
            AsyncImage(
                modifier = Modifier.size(147.dp, 147.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(event.image)
                    .crossfade(true)
                    .fallback(R.drawable.discoveries)
                    .build(),
                placeholder = painterResource(R.drawable.discoveries),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(id = R.string.event_image)
            )
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Date and rating
                    Text(
                        text = event.getTimePeriod(),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    // RatingBar
                    ComposeStars(
                        value = event.getPopularityFloat(),
                        numStars = 3,
                        size = 16.dp,
                        padding = 1.dp,
                        activeColor = MaterialTheme.colorScheme.secondaryContainer,
                        inactiveColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        ratingBarStyle = RatingBarStyle.Normal,
                    )
                }
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.height(52.dp),
                    text = event.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2

                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(top = Dimens.paddingTopXs)
                ) {
                    Icon(Icons.Default.Domain, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface)
                    Text(
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = Dimens.paddingStartXs),
                        text = event.getCategory(),
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = Dimens.paddingTopSmall)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val btnColors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Button(
                        colors = btnColors,
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(R.string.action_favorite)
                        )
                    }
                    Spacer(modifier = Modifier.width(Dimens.paddingXs))
                    Button(
                        colors = btnColors,
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(Icons.Default.Today, contentDescription = stringResource(R.string.schedule))
                    }
                    Spacer(modifier = Modifier.width(Dimens.paddingXs))
                    Button(
                        colors = btnColors,
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(Icons.Default.StarBorder, contentDescription = stringResource(R.string.rate))
                    }
                }
            }
        }

        // Address and description
        Row(modifier = Modifier.padding(top = Dimens.paddingTopSmall), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.LocationCity, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface)
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                text = event.getFullAddress().ifEmpty { stringResource(R.string.no_address) },
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = Dimens.paddingStartXs)
            )
        }
//            LazyColumn(content = )
        event.description?.let {
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = Dimens.paddingStartXs),
                text = it,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview(showBackground = true)
fun EventBottomDialogPreview() {
    val event = EventItem(
        name = "Shakespeare's Globe Theatre Tours",
        url = "https://s1.ticketm.net/dam/a/f45/be34c7bc-f33b-4c9e-9669-e8423daedf45_692631_ARTIST_PAGE_3_2.jpg",
        locale = null,
        image = "https://s1.ticketm.net/dam/a/f45/be34c7bc-f33b-4c9e-9669-e8423daedf45_692631_ARTIST_PAGE_3_2.jpg",
        startTime = "2021-07-29T09:00+01:00",
        endTime = "2021-07-30T19:00+01:00",
        latitude = 51.508111,
        longitude = -0.096597,
        description = stringResource(id = R.string.mock_description),
        priceFrom = 5f,
        priceTo = 10f,
        categories = listOf("Miscellaneous"),
        popularity = 2,
        address = "21 New Globe Walk",
        countryCode = "GB",
        city = "London",
        region = null,
        place = "Shakespeare's Globe",
        currency = "GBP",
        postalCode = "SE1 9DT"
    )

    MainTheme {
        EventView(event)
    }
}