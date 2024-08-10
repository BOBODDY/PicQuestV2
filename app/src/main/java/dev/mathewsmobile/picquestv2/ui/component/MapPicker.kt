package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.mathewsmobile.picquestv2.viewmodel.MapViewModel

@Composable
fun MapPicker(mapViewModel: MapViewModel, onMapShrinkTapped: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(512.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        MapComponent(mapViewModel, true) { onMapShrinkTapped() }
    }
}