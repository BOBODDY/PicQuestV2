package dev.mathewsmobile.picquestv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import dev.mathewsmobile.picquestv2.data.LocationRepository
import dev.mathewsmobile.picquestv2.ui.NewLocationFlow
import dev.mathewsmobile.picquestv2.ui.screen.LocationListScreen
import dev.mathewsmobile.picquestv2.ui.screen.NewLocationScreen
import dev.mathewsmobile.picquestv2.ui.screen.ViewLocationScreen
import dev.mathewsmobile.picquestv2.ui.theme.PicQuestV2Theme
import dev.mathewsmobile.picquestv2.viewmodel.LocationListViewModel
import dev.mathewsmobile.picquestv2.viewmodel.MapViewModel
import dev.mathewsmobile.picquestv2.viewmodel.NewLocationViewModel
import dev.mathewsmobile.picquestv2.viewmodel.ViewLocationViewModel
import dev.mathewsmobile.picquestv2.viewmodel.ViewLocationViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var locationRepository: LocationRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            PicQuestV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = LocationListScreen
                    ) {
                        composable<LocationListScreen> {
                            val viewModel by viewModels<LocationListViewModel>()
                            LocationListScreen(Modifier, navController, viewModel = viewModel)
                        }
                        composable<NewLocationScreen> {
                            val viewModel by viewModels<NewLocationViewModel>()
                            val mapViewModel by viewModels<MapViewModel>()
                            viewModel.clear()
                            NewLocationFlow(viewModel = viewModel, navController = navController, mapViewModel = mapViewModel)
                        }
                        composable<ViewLocationScreen> { backstackEntry ->
                            val locationId: Int = backstackEntry.toRoute<ViewLocationScreen>().id
                            val viewModel by viewModels<ViewLocationViewModel> {
                                ViewLocationViewModelFactory(locationRepository, locationId)
                            }
                            ViewLocationScreen(viewModel = viewModel, navController = navController)
                        }
                    }
                }
            }
        }
    }
}
