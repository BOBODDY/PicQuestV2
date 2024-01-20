package dev.mathewsmobile.picquestv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mapbox.maps.MapboxExperimental
import dagger.hilt.android.AndroidEntryPoint
import dev.mathewsmobile.picquestv2.ui.screen.LocationListScreen
import dev.mathewsmobile.picquestv2.ui.screen.NewLocationScreen
import dev.mathewsmobile.picquestv2.ui.theme.PicQuestV2Theme
import dev.mathewsmobile.picquestv2.viewmodel.LocationListViewModel
import dev.mathewsmobile.picquestv2.viewmodel.MapViewModel
import dev.mathewsmobile.picquestv2.viewmodel.NewLocationViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
                        startDestination = LocationListScreen.route
                    ) {
                        composable(LocationListScreen.route) {
                            val viewModel by viewModels<LocationListViewModel>()
                            LocationListScreen(navController, viewModel = viewModel)
                        }
                        composable(NewLocationScreen.route) {
                            val viewModel by viewModels<NewLocationViewModel>()
                            val mapViewModel by viewModels<MapViewModel>()
                            NewLocationScreen(navController, viewModel, mapViewModel)
                        }
                    }
                }
            }
        }
    }
}
