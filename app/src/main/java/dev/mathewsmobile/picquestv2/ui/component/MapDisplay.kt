package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.plugin.gestures.generated.GesturesSettings

@OptIn(MapboxExperimental::class)
@Composable
fun MapDisplay(interactionEnabled: Boolean = true, mapViewportState: MapViewportState) {
    Box(modifier = Modifier.pointerInput(Unit) {}) {
        MapboxMap(
            modifier = Modifier.fillMaxSize(),
            mapViewportState = mapViewportState,
            gesturesSettings = GesturesSettings {
                GesturesSettings.Builder()
                    .setPitchEnabled(false)
                    .setPinchToZoomEnabled(interactionEnabled)
                    .setRotateEnabled(interactionEnabled)
                    .setDoubleTapToZoomInEnabled(interactionEnabled)
                    .setDoubleTouchToZoomOutEnabled(interactionEnabled)
                    .setScrollEnabled(interactionEnabled)
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
}