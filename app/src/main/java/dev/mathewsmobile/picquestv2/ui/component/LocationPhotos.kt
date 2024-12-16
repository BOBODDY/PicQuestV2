package dev.mathewsmobile.picquestv2.ui.component

import android.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

@Composable
fun LocationPhotos(modifier: Modifier = Modifier, photos: List<Uri>, addPhoto: () -> Unit) {
    val context = LocalContext.current
    ImagesPermissionEffect()
    Box(modifier.fillMaxSize()) {
        Column(modifier.padding(16.dp)) {
            Text("Photos", fontSize = 32.sp, fontWeight = Bold)
            Spacer(modifier.height(16.dp))
            Text("Add some photos as a reminder of what you want from this location.")
            Spacer(modifier.height(8.dp))
            PhotoPicker(photos = photos) {
                if (
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                    (
                            ContextCompat.checkSelfPermission(
                                context,
                                READ_MEDIA_IMAGES
                            ) == PERMISSION_GRANTED ||
                                    ContextCompat.checkSelfPermission(
                                        context,
                                        READ_MEDIA_VIDEO
                                    ) == PERMISSION_GRANTED
                            )
                ) {
                    // Full access on Android 13 (API level 33) or higher
                } else if (
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE &&
                    ContextCompat.checkSelfPermission(
                        context,
                        READ_MEDIA_VISUAL_USER_SELECTED
                    ) == PERMISSION_GRANTED
                ) {
                    // Partial access on Android 14 (API level 34) or higher
                } else if (ContextCompat.checkSelfPermission(
                        context,
                        READ_EXTERNAL_STORAGE
                    ) == PERMISSION_GRANTED
                ) {
                    // Full access up to Android 12 (API level 32)
                } else {
                    // Access denied

                }
                addPhoto()
            }
        }
    }
}

@Composable
fun ImagesPermissionEffect() {
    val launcher = rememberLauncherForActivityResult(
        contract = RequestMultiplePermissions()
    ) { permissions ->
        // Handle permission requests results here
        permissions.entries.forEach {
            val permissionName = it.key
            val isGranted = it.value
            // Process the result for each permission
        }
    }
    val permissions = remember {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> {
                arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO, READ_MEDIA_VISUAL_USER_SELECTED)
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO)
            }

            else -> {
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    LaunchedEffect(key1 = permissions) {
        launcher.launch(permissions)
    }
}

@Preview
@Composable
private fun LocationPhotosPreview() {
    Box(Modifier.background(Color.White)) {
        LocationPhotos(photos = listOf()) { }
    }
}