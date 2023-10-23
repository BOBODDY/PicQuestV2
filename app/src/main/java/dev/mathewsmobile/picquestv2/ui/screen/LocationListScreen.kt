package dev.mathewsmobile.picquestv2.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.data.UiStatus.Error
import dev.mathewsmobile.picquestv2.data.UiStatus.Initial
import dev.mathewsmobile.picquestv2.data.UiStatus.Loaded
import dev.mathewsmobile.picquestv2.data.UiStatus.Loading
import dev.mathewsmobile.picquestv2.model.Location
import dev.mathewsmobile.picquestv2.ui.component.AddLocationFab
import dev.mathewsmobile.picquestv2.ui.component.LoadingComponent
import dev.mathewsmobile.picquestv2.ui.component.LocationList
import dev.mathewsmobile.picquestv2.viewmodel.LocationListViewModel

object LocationListScreen {
    const val route = "LocationListScreen"
}

@Composable
fun LocationListScreen(
    navController: NavController,
    viewModel: LocationListViewModel
) {
    val state = viewModel.uiStateFlow.collectAsState().value

    when (state.status) {
        Initial -> {
            viewModel.fetchLocations()
        }
        Loading -> {
            LoadingComponent()
        }
        Loaded -> {
            LocationListComponent(state.locations) {
                navController.navigate(NewLocationScreen.route)
            }
        }
        Error -> {
            Text("Error")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationListComponent(locations: List<Location>, onAddClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("PicQuest", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary))
        },
        floatingActionButton = {
            AddLocationFab(onAddClick)
        }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LocationList(locations)
        }
    }
}

@Preview
@Composable
fun LocationListScreenPreview() {
    LocationListComponent(LocationRepository.testLocations, {})
}