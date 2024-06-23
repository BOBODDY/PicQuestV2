package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.mathewsmobile.picquestv2.model.Location

@Composable
fun LocationList(locations: List<Location>, onItemTapped: (Int) -> Unit) {
    LazyColumn {
        items(locations.size) {
            val location = locations[it]
            Box(modifier = Modifier.clickable {
                onItemTapped(location.id)
            }) {
                LocationCell(location)
            }
        }
    }
}