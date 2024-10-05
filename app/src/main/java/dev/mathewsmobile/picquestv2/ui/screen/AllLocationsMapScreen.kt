package dev.mathewsmobile.picquestv2.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.mathewsmobile.picquestv2.data.UiStatus.Loaded
import dev.mathewsmobile.picquestv2.data.UiStatus.Loading
import dev.mathewsmobile.picquestv2.data.UiStatus.Error
import dev.mathewsmobile.picquestv2.model.Location
import dev.mathewsmobile.picquestv2.ui.component.LoadingComponent
import dev.mathewsmobile.picquestv2.viewmodel.AllLocationsMapViewModel
import kotlinx.serialization.Serializable

@Serializable
object AllLocationsMapScreen

@Composable
fun AllLocationsMapScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AllLocationsMapViewModel
) {
    val state = viewModel.state.collectAsState().value

    when (state.status) {
        Loading -> {
            LoadingComponent()
        }
        Loaded -> {
            AllLocationsMap(modifier, state.locations)
        }
        Error -> {
            Text("Error")
        }
    }
}

@Composable
fun AllLocationsMap(modifier: Modifier = Modifier, locations: List<Location>) {

}