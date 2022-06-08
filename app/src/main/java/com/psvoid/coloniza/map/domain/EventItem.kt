package com.psvoid.coloniza.map.domain

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.*
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.IgnoreExtraProperties
//import com.google.maps.android.clustering.ClusterItem
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/** A data class that implements the [ClusterItem] interface so it can be clustered. */
@IgnoreExtraProperties
@Entity(tableName = "events_table")
@RequiresApi(Build.VERSION_CODES.O)
data class EventItem(
//    @SerialName("title")
    val name: String = "Event",
    @PrimaryKey val id: String = "",
    val url: String? = null,
    val locale: String? = null,
    val image: String? = null, // 250*250 or 4*3 ratio
//    @SerialName("start_time") @PropertyName("start_time") @ColumnInfo(name = "start_time")
    val startTime: String = "",
    val endTime: String? = null,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val description: String? = null,
    val priceFrom: Float? = null,
    val priceTo: Float? = null,
    @TypeConverters(CategoriesConverter::class)
    val categories: List<String> = listOf("other"),
    val popularity: Int? = null,
    val address: String? = null,
//    val country_name: String = "",
    val countryCode: String = "",
    val city: String = "",
    val region: String? = null,
    val place: String? = null,
    val currency: String? = null,
    val postalCode: String? = null

    // Additional stuff
//    var isSelected: Boolean = false,
//    var isFavorite: Boolean = false

) {
    private companion object {
        val formatterDt: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmXXX")
        val formatterTime: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }

    @Ignore
    private fun parseDateTime(dateTime: String): LocalDateTime = if (dateTime.length > 10)
        LocalDateTime.parse(dateTime, formatterDt) else LocalDate.parse(dateTime, formatterTime).atStartOfDay()

    @Ignore
    val startDt = lazy { parseDateTime(startTime) }

    @Ignore
    val endDt = lazy { endTime?.let { parseDateTime(it) } }

    @Ignore
    fun getPosition() = LatLng(latitude, longitude)

    @Ignore
    fun getTitle() = name

    @Ignore
    fun getSnippet() = address

    @Ignore
    fun getCategory(): String = categories.joinToString()

    @Ignore
    fun getFullAddress(): String = listOfNotNull(address, place).joinToString("; ")

    @Ignore
    fun getPopularityFloat(): Float = (popularity?.div(10))?.toFloat() ?: 0f

    @Ignore
    fun getCategoryColor() = Categories.getCategory(categories).color

    @Ignore
    fun getCategoryName() = Categories.getCategory(categories).name

//    @Ignore
//    fun getMarkerDescriptor(isSelected: Boolean = false): BitmapDescriptor {
//        val color = if (isSelected) BitmapDescriptorFactory.HUE_CYAN else getCategoryColor()
//        return BitmapDescriptorFactory.defaultMarker(color)
//    }

    @Ignore
    fun getMarkerDescriptor(isSelected: Boolean = false): BitmapDescriptor? =
        if (isSelected) BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE) else null

    /**Convert date like "2021-08-02T17:00-10:45" to "17:00 - 19:00, 04 Dec" */
    @Ignore
    fun getTimePeriod(): String {
        return if (endDt.value != null) {
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
            val dateFormatter = DateTimeFormatter.ofPattern("dd MMM")
            "${startDt.value.format(timeFormatter)} - ${endDt.value?.format(timeFormatter)}, ${
                startDt.value.format(dateFormatter)
            }"
            //TODO: Add conditions for multiple days, support for API < 26
        } else startDt.value.format(DateTimeFormatter.ofPattern("HH:mm, dd MMM"))
    }
}

class CategoriesConverter {
    private val separator = ", "

    @TypeConverter
    fun fromData(value: List<String>): String = value.joinToString(separator)

    @TypeConverter
    fun toData(data: String): List<String> = data.split(separator)
}

object Categories {
    private val categories: MutableMap<String, Category> = mutableMapOf()

    init {
        categories["family_fun_kids"] = Category("Family", BitmapDescriptorFactory.HUE_RED)
        categories["learning_education"] = Category("Education", BitmapDescriptorFactory.HUE_AZURE)
        categories["other"] = Category("Other", BitmapDescriptorFactory.HUE_ROSE)
        categories["sports"] = Category("Sports", BitmapDescriptorFactory.HUE_GREEN)
        categories["performing_arts"] =
            Category("Performing Arts", BitmapDescriptorFactory.HUE_VIOLET)
        categories["science"] = Category("Science", BitmapDescriptorFactory.HUE_CYAN)
        categories["business"] =
            Category("Business, Networking", BitmapDescriptorFactory.HUE_MAGENTA)
        categories["food"] = Category("Food", BitmapDescriptorFactory.HUE_GREEN)
        categories["singles_social"] =
            Category("Nightlife, Singles", BitmapDescriptorFactory.HUE_VIOLET)
        categories["fundraisers"] =
            Category("Fundraising, Charity", BitmapDescriptorFactory.HUE_MAGENTA)
        categories["technology"] = Category("Technology", BitmapDescriptorFactory.HUE_CYAN)
        categories["comedy"] = Category("Comedy", BitmapDescriptorFactory.HUE_VIOLET)
        categories["holiday"] = Category("Holiday", BitmapDescriptorFactory.HUE_YELLOW)
        categories["music"] = Category("Music", BitmapDescriptorFactory.HUE_VIOLET)
        categories["politics_activism"] = Category("Politics", BitmapDescriptorFactory.HUE_YELLOW)
        categories["festivals_parades"] = Category("Festivals", BitmapDescriptorFactory.HUE_YELLOW)
        categories["movies_film"] = Category("Movie", BitmapDescriptorFactory.HUE_VIOLET)
        categories["support"] = Category("Health", BitmapDescriptorFactory.HUE_GREEN)
        categories["outdoors_recreation"] =
            Category("Outdoors, Recreation", BitmapDescriptorFactory.HUE_GREEN)
        categories["attractions"] =
            Category("Museums, Attractions", BitmapDescriptorFactory.HUE_VIOLET)
        categories["conference"] =
            Category("Conferences, Tradeshows", BitmapDescriptorFactory.HUE_ORANGE)
        categories["community"] = Category("Neighborhood", BitmapDescriptorFactory.HUE_YELLOW)
        categories["religion_spirituality"] =
            Category("Religion, spirituality", BitmapDescriptorFactory.HUE_YELLOW)
    }

    fun getCategory(value: List<String>): Category {
        val category = value.first()
        val key = if (categories.containsKey(category)) category else "other"
        return categories.getValue(key)
    }
}