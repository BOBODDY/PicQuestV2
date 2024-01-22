package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.plugin.gestures.generated.GesturesSettings
import dev.mathewsmobile.picquestv2.viewmodel.MapViewModel
import dev.mathewsmobile.picquestv2.ui.effect.LocationEffect

@OptIn(MapboxExperimental::class)
@Composable
fun MapComponent(
    viewModel: MapViewModel,
    onExpandMapTapped: () -> Unit,
) {

    val mapViewportState by viewModel.mapViewportState.collectAsState()

    LocationEffect(viewModel)
    Box {
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

        IconButton(modifier = Modifier.align(Alignment.BottomEnd), onClick = { onExpandMapTapped() }) {
            Icon(Icons.Default.Menu, contentDescription = "Expand map picker")
        }
    }
}