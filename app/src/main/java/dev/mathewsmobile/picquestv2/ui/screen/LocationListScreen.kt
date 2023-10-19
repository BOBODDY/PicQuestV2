package dev.mathewsmobile.picquestv2.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.ui.component.AddLocationFab
import dev.mathewsmobile.picquestv2.ui.component.LocationList

object LocationListScreen {
    const val route = "LocationListScreen"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationListScreen(navController: NavController) {
    val locationRepo = LocationRepository()
    Scaffold(floatingActionButton = { AddLocationFab { navController.navigate(NewLocationScreen.route) } }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LocationList(locationRepo.getLocations())
        }
    }
}

@Preview
@Composable
fun LocationListScreenPreview() {
    LocationListScreen(rememberNavController())
}