package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import dev.mathewsmobile.picquestv2.model.Location

@Composable
fun LocationList(locations: List<Location>) {
    LazyColumn {
        items(locations.size) {
            val location = locations[it]
            LocationCell(location)
        }
    }
}