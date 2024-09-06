package dev.mathewsmobile.picquestv2.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.model.Location
import dev.mathewsmobile.picquestv2.ui.theme.PicQuestV2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationListComponent(
    modifier: Modifier = Modifier,
    locations: List<Location>,
    onAddClick: () -> Unit,
    onLocationClick: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PicQuest", fontWeight = FontWeight.Bold) },
            )
        },
        floatingActionButton = {
            AddLocationFab(onAddClick)
        }) {
        if (locations.isEmpty()) {
            EmptyLocations(modifier)
        } else {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                LocationList(locations) {
                    onLocationClick(it)
                }
            }
        }
    }
}

@Composable
fun EmptyLocations(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.LocationOn, modifier = modifier.size(36.dp), contentDescription = null)
            Text("Add a location below", fontSize = 16.sp)
        }
    }

}

@Preview
@Composable
fun LocationListScreenPreview() {
    PicQuestV2Theme {
        LocationListComponent(Modifier, LocationRepository.testLocations, {}, {})
    }
}

@Preview
@Composable
fun LocationListScreenEmptyPreview() {
    LocationListComponent(Modifier, emptyList(), {}, {})
}