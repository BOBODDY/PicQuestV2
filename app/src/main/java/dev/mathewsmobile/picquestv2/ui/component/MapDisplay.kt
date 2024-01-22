package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.plugin.gestures.generated.GesturesSettings

@OptIn(MapboxExperimental::class)
@Composable
fun MapDisplay(mapViewportState: MapViewportState) {
    MapboxMap(
        modifier = Modifier.fillMaxSize(),
        mapViewportState = mapViewportState,
        gesturesSettings = GesturesSettings {
            GesturesSettings.Builder()
                .setPitchEnabled(false)
                .build()
        },
        mapInitOptionsFactory = { context ->
            MapInitOptions(
                context = context,
                styleUri = Style.STANDARD,
            )
        },
    )
}