package dev.mathewsmobile.picquestv2.ui.effect

import android.Manifest
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalInspectionMode
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

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
        if (status is PermissionStatus.Denied && !status.shouldShowRationale) {
            locationPermissionState.launchPermissionRequest()
        }
    }
}