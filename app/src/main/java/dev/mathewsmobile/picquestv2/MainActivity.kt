package dev.mathewsmobile.picquestv2

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.core.app.ActivityCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus.Denied
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.MapboxMap
import dev.mathewsmobile.picquestv2.ui.theme.PicQuestV2Theme

class MainActivity : ComponentActivity() {
    @OptIn(MapboxExperimental::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicQuestV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mapViewModel by viewModels<MapViewModel>()
                    Map(mapViewModel)
                    LocationPermissionsEffect()
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun LocationPermissionsEffect() {
    // Permission requests should only be made from an Activity Context
    // This is not present in previews
    if (LocalInspectionMode.current) return
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return
    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION,
    )
    LaunchedEffect(locationPermissionState) {
        val status = locationPermissionState.status
        if (status is Denied && !status.shouldShowRationale) {
            locationPermissionState.launchPermissionRequest()
        }
    }
}

@OptIn(MapboxExperimental::class)
@Composable
fun Map(viewModel: MapViewModel) {
    val center by viewModel.currentLocation.collectAsState()

    LocationEffect(viewModel)
    MapboxMap(modifier = Modifier.fillMaxSize(),
        mapInitOptionsFactory = { context ->
            MapInitOptions(
                context = context,
                styleUri = Style.STANDARD,
                cameraOptions = CameraOptions.Builder()
                    .center(Point.fromLngLat(center.longitude, center.latitude))
                    .zoom(9.0)
                    .build()
            )
        })
}

@Composable
fun LocationEffect(mapViewModel: MapViewModel) {
    val context = LocalContext.current.applicationContext

    // Create a LocationRequest object.
    val locationRequest = LocationRequest.create()
    locationRequest.interval = 10000
    locationRequest.fastestInterval = 5000
    locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

    // Create a LocationCallback object.
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            // Get the location from the location result.
            val location = locationResult.lastLocation

            mapViewModel.updateUserLocation(location.latitude, location.longitude)
        }
    }

    // Request location updates from the LocationServices API.
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        LocationPermissionsEffect()
        return
    }
    LocationServices.getFusedLocationProviderClient(context).requestLocationUpdates(
        locationRequest,
        locationCallback,
        Looper.getMainLooper()
    )
}
