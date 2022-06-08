package com.psvoid.coloniza.map.presentation.views

/*
import android.content.Context
import android.content.res.Resources
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.model.BitmapDescriptor
import com.google.android.libraries.maps.model.BitmapDescriptorFactory
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import com.psvoid.coloniza.R
import com.psvoid.coloniza.map.domain.EventItem

*/
/**
 * Draws profile images inside markers (using IconGenerator).
 * When there are multiple images in the cluster, draw multiple images (using MultiDrawable).
 *//*

class ClusterMarkerRendererPhoto(
    private val context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<EventItem>,
    resources: Resources
) :
    DefaultClusterRenderer<EventItem>(context, map, clusterManager) {
    private val iconGenerator = IconGenerator(context)

    //    val clusterIconGenerator = IconGenerator(context)
    private var imageView: ImageView = ImageView(context)

    //    private var clusterImageView: ImageView

    init {
//        val multiProfile: View = LayoutInflater().inflate(R.layout.multi_profile, null)
//        mClusterIconGenerator.setContentView(multiProfile)
//        clusterImageView = multiProfile.findViewById(R.id.image)

        val dimension = resources.getDimension(R.dimen.custom_profile_image).toInt()
        imageView.layoutParams = ViewGroup.LayoutParams(dimension, dimension)
        val padding = resources.getDimension(R.dimen.custom_profile_padding).toInt()
        imageView.setPadding(padding, padding, padding, padding)
        iconGenerator.setContentView(imageView)

    }

    override fun onBeforeClusterItemRendered(marker: EventItem, markerOptions: MarkerOptions) {
        loadImage(marker)

        // Draw a single marker - show their profile photo and set the info window to show their name
        markerOptions
            .icon(getItemIcon(marker))
//            .icon(loadImage(marker))
            .title(marker.title)
    }

    */
/**
     * Get a descriptor for a single marker (marker outside a cluster) for a marker icon
     * @param marker ClusterMarker to return an BitmapDescriptor for
     * @return the marker's profile photo as a BitmapDescriptor
     *//*

    private fun getItemIcon(marker: EventItem): BitmapDescriptor {
        imageView.setImageResource(R.drawable.ic_cake_white_24dp) //TODO: change to image
//        mImageView.setImageResource(marker.image)
        val icon = iconGenerator.makeIcon()
        return BitmapDescriptorFactory.fromBitmap(icon)
    }

    private fun loadImage(marker: EventItem) {

        //TODO: finish
//        val imgUrl = marker.images?.thumb?.url
//        imgUrl?.let {
//            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build() // Make image Uri
//            Glide.with(context)
//                .load(imgUri)
//                .centerCrop()
//                .apply(RequestOptions().placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image))
//                .into(imageView)
//
//        }
    }


}*/
