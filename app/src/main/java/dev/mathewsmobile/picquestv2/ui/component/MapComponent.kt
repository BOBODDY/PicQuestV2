package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import dev.mathewsmobile.picquestv2.ui.effect.LocationEffect
import dev.mathewsmobile.picquestv2.viewmodel.MapViewModel

@Composable
fun MapComponent(
    viewModel: MapViewModel,
    expandable: Boolean,
    onExpandMapTapped: () -> Unit,
) {
    val singapore = LatLng(1.35, 103.87)
    val singaporeMarkerState = rememberMarkerState(position = singapore)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = singaporeMarkerState,
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
    }

    LocationEffect(viewModel)
    Box {
        GoogleMap()
        if (expandable) {
            IconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = { onExpandMapTapped() }) {
                Icon(Icons.Default.Menu, contentDescription = "Expand map picker")
            }
        }
    }
}