package dev.mathewsmobile.picquestv2.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import dev.mathewsmobile.picquestv2.ui.component.ViewLocation
import dev.mathewsmobile.picquestv2.viewmodel.ViewLocationViewModel
import kotlinx.serialization.Serializable

@Serializable
data class ViewLocationScreen(val id: Int)

@Composable
fun ViewLocationScreen(viewModel: ViewLocationViewModel, navController: NavController) {
    val location by viewModel.location.collectAsState()

    ViewLocation(
        name = location?.name ?: "",
        description = location?.notes ?: "",
        tags = location?.tags ?: emptyList(),
        location = location?.latLng,
        photos = location?.photoUris ?: emptyList()
    ) {
        navController.popBackStack()
    }
}