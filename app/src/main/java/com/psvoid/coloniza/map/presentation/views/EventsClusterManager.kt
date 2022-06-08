package com.psvoid.coloniza.map.presentation.views

//import com.google.android.libraries.maps.GoogleMap
//import com.google.android.libraries.maps.model.Marker
//import com.google.maps.android.clustering.ClusterManager

//class EventsClusterManager(
//        context: Context,
//        map: GoogleMap,
//        viewModel: MapViewModel,
//        private val onMarkerClick: (id: String) -> Unit,
//        private val onCameraIdle: () -> Unit
//) : ClusterManager<EventItem>(context, map) {
//
//    init {
//        renderer = ClusterMarkerRenderer(context, map, this, viewModel)
//    }
//
//    override fun onCameraIdle() {
//        super.onCameraIdle()
//        onCameraIdle.invoke()
//    }
//
//    override fun onMarkerClick(marker: Marker): Boolean {
//        val tag = marker.tag
//        if (tag is String) onMarkerClick.invoke(tag)
//        return super.onMarkerClick(marker)
//    }
//
//}
