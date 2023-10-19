package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.MapboxMap
import dev.mathewsmobile.picquestv2.MapViewModel
import dev.mathewsmobile.picquestv2.ui.effect.LocationEffect

@OptIn(MapboxExperimental::class)
@Composable
fun MapComponent(viewModel: MapViewModel) {
    val center by viewModel.currentLocation.collectAsState()

    LocationEffect(viewModel)
    MapboxMap(modifier = Modifier.fillMaxSize(),
        mapInitOptionsFactory = { context ->
            MapInitOptions(
                context = context,
                styleUri = Style.STANDARD,
                cameraOptions = CameraOptions.Builder()
                    .center(Point.fromLngLat(center.longitude, center.latitude))
                    .zoom(9.0)
                    .build()
            )
        })
}