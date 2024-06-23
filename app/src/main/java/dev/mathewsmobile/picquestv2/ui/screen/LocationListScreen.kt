package dev.mathewsmobile.picquestv2.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.mathewsmobile.picquestv2.data.UiStatus.Error
import dev.mathewsmobile.picquestv2.data.UiStatus.Loaded
import dev.mathewsmobile.picquestv2.data.UiStatus.Loading
import dev.mathewsmobile.picquestv2.ui.component.LoadingComponent
import dev.mathewsmobile.picquestv2.ui.component.LocationListComponent
import dev.mathewsmobile.picquestv2.viewmodel.LocationListViewModel

object LocationListScreen {
    const val route = "LocationListScreen"
}

@Composable
fun LocationListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LocationListViewModel,
) {
    val state = viewModel.state.collectAsState().value

    when (state.status) {
        Loading -> {
            LoadingComponent()
        }
        Loaded -> {
            LocationListComponent(
                modifier,
                state.locations,
                onAddClick = {
                    navController.navigate(NewLocationScreen.route)
                },
                onLocationClick = {
                    navController.navigate("${ViewLocationScreen.route}/$it")
                }
            )
        }
        Error -> {
            Text("Error")
        }
    }
}