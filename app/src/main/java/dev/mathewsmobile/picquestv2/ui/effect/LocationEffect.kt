package dev.mathewsmobile.picquestv2.ui.effect

import android.Manifest
import android.content.pm.PackageManager
import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dev.mathewsmobile.picquestv2.MapViewModel

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