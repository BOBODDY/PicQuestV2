package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mapbox.maps.MapboxExperimental
import dev.mathewsmobile.picquestv2.ui.effect.LocationEffect
import dev.mathewsmobile.picquestv2.viewmodel.MapViewModel

@OptIn(MapboxExperimental::class)
@Composable
fun MapComponent(
    viewModel: MapViewModel,
    onExpandMapTapped: () -> Unit,
) {

    val mapViewportState by viewModel.mapViewportState.collectAsState()

    LocationEffect(viewModel)
    Box {
        MapDisplay(mapViewportState = mapViewportState, pinnedLocation = null)
        IconButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = { onExpandMapTapped() }) {
            Icon(Icons.Default.Menu, contentDescription = "Expand map picker")
        }
    }
}