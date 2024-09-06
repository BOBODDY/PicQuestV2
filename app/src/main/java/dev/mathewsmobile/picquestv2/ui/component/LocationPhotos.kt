package dev.mathewsmobile.picquestv2.ui.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LocationPhotos(modifier: Modifier = Modifier, photos: List<Uri>, addPhoto: () -> Unit) {
    Box(modifier.fillMaxSize()) {
        Column(modifier.padding(16.dp)) {
            Text("Photos", fontSize = 32.sp, fontWeight = Bold)
            Spacer(modifier.height(16.dp))
            Text("Add some photos as a reminder of what you want from this location.")
            Spacer(modifier.height(8.dp))
            PhotoPicker(photos = photos) {
                addPhoto()
            }
        }
    }
}

@Preview
@Composable
private fun LocationPhotosPreview() {
    Box(Modifier.background(Color.White)) {
        LocationPhotos(photos = listOf()) { }
    }
}